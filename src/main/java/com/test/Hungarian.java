package com.test;

import java.util.Arrays;
/* Copyright (c) 2012 Kevin L. Stern
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
 
public class Hungarian
{
    private final int[][] costMatrix;
    private final int groups, projects, dim; //rows = groups , projects = cols
    int[] labelByGroup;
	private final int[]   labelByProject;  //labelByWorker= labelByGroup  , labelByJob = labelByProject
    private final int[]      minSlackGroupByProject;
    private final int[]   minSlackValueByProject;
    private final int[]      matchProjectByGroup, matchGroupByProject;
    private final int[]      parentGroupByCommittedProject;
    private final boolean[]  committedGroups;
 
    public Hungarian(int[][] cos)
    {
        this.dim = Math.max(cos.length, cos[0].length);
        this.groups = cos.length;
        this.projects = cos[0].length;
        this.costMatrix = new int[this.dim][this.dim];
        for (int w = 0; w < this.dim; w++)
        {
            if (w < cos.length)
            {
                if (cos[w].length != this.projects)
                {
                    throw new IllegalArgumentException("Irregular cost matrix");
                }
                this.costMatrix[w] = Arrays.copyOf(cos[w], this.dim);
            }
            else
            {
                this.costMatrix[w] = new int[this.dim];
            }
        }
        labelByGroup = new int[this.dim];
        labelByProject = new int[this.dim];
        minSlackGroupByProject = new int[this.dim];
        minSlackValueByProject = new int[this.dim];
        committedGroups = new boolean[this.dim];
        parentGroupByCommittedProject = new int[this.dim];
        matchProjectByGroup = new int[this.dim];
        Arrays.fill(matchProjectByGroup, -1);
        matchGroupByProject = new int[this.dim];
        Arrays.fill(matchGroupByProject, -1);
    }
 
    protected void computeInitialFeasibleSolution()
    {
        for (int j = 0; j < dim; j++)
        {
            labelByProject[j] = (int) Double.POSITIVE_INFINITY;
        }
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < labelByProject[j])
                {
                    labelByProject[j] = costMatrix[w][j];
                }
            }
        }
    }
 
    public int[] execute()
    {
        /*
         * Heuristics to improve performance: Reduce rows and columns by their
         * smallest element, compute an initial non-zero dual feasible solution
         * and
         * create a greedy matching from workers to jobs of the cost matrix.
         */
        reduce();
        computeInitialFeasibleSolution();
        greedyMatch();
        int w = fetchUnmatchedWorker();
        while (w < dim)
        {
            initializePhase(w);
            executePhase();
            w = fetchUnmatchedWorker();
        }
        int[] result = Arrays.copyOf(matchProjectByGroup, groups);
        for (w = 0; w < result.length; w++)
        {
            if (result[w] >= projects)
            {
                result[w] = -1;
            }
        }
        return result;
    }
 
    protected void executePhase()
    {
        while (true)
        {
            int minSlackGroup = -1, minSlackJob = -1;
            double minSlackValue = Double.POSITIVE_INFINITY;
            for (int j = 0; j < dim; j++)
            {
                if (parentGroupByCommittedProject[j] == -1)
                {
                    if (minSlackValueByProject[j] < minSlackValue)
                    {
                        minSlackValue = minSlackValueByProject[j];
                        minSlackGroup = minSlackGroupByProject[j];
                        minSlackJob = j;
                    }
                }
            }
            if (minSlackValue > 0)
            {
                updateLabeling(minSlackValue);
            }
            parentGroupByCommittedProject[minSlackJob] = minSlackGroup;
            if (matchGroupByProject[minSlackJob] == -1)
            {
                /*
                 * An augmenting path has been found.
                 */
                int committedJob = minSlackJob;
                int parentWorker = parentGroupByCommittedProject[committedJob];
                while (true)
                {
                    int temp = matchProjectByGroup[parentWorker];
                    match(parentWorker, committedJob);
                    committedJob = temp;
                    if (committedJob == -1)
                    {
                        break;
                    }
                    parentWorker = parentGroupByCommittedProject[committedJob];
                }
                return;
            }
            else
            {
                /*
                 * Update slack values since we increased the size of the
                 * committed
                 * workers set.
                 */
                int worker = matchGroupByProject[minSlackJob];
                committedGroups[worker] = true;
                for (int j = 0; j < dim; j++)
                {
                    if (parentGroupByCommittedProject[j] == -1)
                    {
                        int slack = costMatrix[worker][j]
                                - labelByGroup[worker] - labelByProject[j];
                        if (minSlackValueByProject[j] > slack)
                        {
                            minSlackValueByProject[j] = slack;
                            minSlackGroupByProject[j] = worker;
                        }
                    }
                }
            }
        }
    }
 
    protected int fetchUnmatchedWorker()
    {
        int w;
        for (w = 0; w < dim; w++)
        {
            if (matchProjectByGroup[w] == -1)
            {
                break;
            }
        }
        return w;
    }
 
    protected void greedyMatch()
    {
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (matchProjectByGroup[w] == -1
                        && matchGroupByProject[j] == -1
                        && costMatrix[w][j] - labelByGroup[w] - labelByProject[j] == 0)
                {
                    match(w, j);
                }
            }
        }
    }
 
    protected void initializePhase(int w)
    {
        Arrays.fill(committedGroups, false);
        Arrays.fill(parentGroupByCommittedProject, -1);
        committedGroups[w] = true;
        for (int j = 0; j < dim; j++)
        {
            minSlackValueByProject[j] = costMatrix[w][j] - labelByGroup[w]
                    - labelByProject[j];
            minSlackGroupByProject[j] = w;
        }
    }
 
    

	protected void match(int w, int j)
    {
        matchProjectByGroup[w] = j;
        matchGroupByProject[j] = w;
    }
 
    protected void reduce()
    {
        for (int w = 0; w < dim; w++)
        {
            double min = Double.POSITIVE_INFINITY;
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < min)
                {
                    min = costMatrix[w][j];
                }
            }
            for (int j = 0; j < dim; j++)
            {
                costMatrix[w][j] -= min;
            }
        }
        double[] min = new double[dim];
        for (int j = 0; j < dim; j++)
        {
            min[j] = Double.POSITIVE_INFINITY;
        }
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < min[j])
                {
                    min[j] = costMatrix[w][j];
                }
            }
        }
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                costMatrix[w][j] -= min[j];
            }
        }
    }
 
    protected void updateLabeling(double slack)
    {
        for (int w = 0; w < dim; w++)
        {
            if (committedGroups[w])
            {
                labelByGroup[w] += slack;
            }
        }
        for (int j = 0; j < dim; j++)
        {
            if (parentGroupByCommittedProject[j] != -1)
            {
                labelByProject[j] -= slack;
            }
            else
            {
                minSlackValueByProject[j] -= slack;
            }
        }
    }
       
}

