package com.inspnovo.studio.algo.graph;

import java.util.Iterator;

/**
 * 稠密图的基准实现
 */
public class DenseGraph implements Graph {
    private int numOfVertex;
    private int numOfEdge;
    private Edge[][] graph;
    private boolean directed = false;
    // 记录每个顶点的入度
    private int[] indegree;

    public DenseGraph(int numOfVertex){
        this(numOfVertex, false);
    }

    public DenseGraph(int numOfVertex, boolean isDirected){
        this.numOfVertex = numOfVertex;
        this.graph = new Edge[numOfVertex][];
        this.indegree = new int[numOfVertex];
        this.directed = false;
        for (int i = 0; i < this.numOfVertex; i++) {
            this.graph[i] =  new Edge[numOfVertex];
            this.indegree[i] = 0;
        }
        this.directed = isDirected;
    }

    /**
     * v, w 是vertex的索引
     * @param v vertex v
     * @param w vertex w
     */
    public void addEdge(int v, int w, Double weight){
        if(v < 0 || v >= numOfVertex || w < 0 || w >= numOfVertex){
            throw new IllegalArgumentException("invalid vertext index");
        }
        if(null != graph[v][w]){
            return;
        }
        graph[v][w] = new Edge(v, w, weight);
        indegree[w]++;
        if(!directed){
            //如果是无向图，则反向也需要链接
            graph[w][v] = new Edge(w, v, weight);
            indegree[v]++;
        }
        numOfEdge++;

    }

    /**
     * v,w 是否有边连接
     * @param v vertex v
     * @param w vertex w
     * @return wether edge(v,w) exists
     */
    public boolean hasEdge(int v, int w){
        if(v < 0 || v >= numOfVertex || w < 0 || w > numOfVertex){
            throw new IllegalArgumentException("invalid vertex index");
        }
        return null != graph[v][w];
    }

    public int getNumOfVertex() {
        return numOfVertex;
    }

    public int getNumOfEdge() {
        return numOfEdge;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    public Iterator<Edge> iterator(int v){
        return new Iter(v);
    }

    @Override
    public void show() {
        for(int i=0; i<this.numOfVertex; i++){
            Iterator<Edge> iter = this.iterator(i);
            System.out.print("vertex " + i +" : ");
            while(iter.hasNext()){
                Edge n = iter.next();
                if(null == n){
                    continue;
                }
                System.out.print(n);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private class Iter implements Iterator<Edge>{

        public int vertex;
        public int index;

        public Iter(int v){
            this.vertex = v;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < getNumOfVertex();
        }

        @Override
        public Edge next() {
            int targetIdx = -1;
            if(null != graph[vertex][index]){
                targetIdx = index;
            }
            index++;
            return -1 == targetIdx? null : graph[vertex][targetIdx];
        }
    }

    public static void main(String[] args) {
        DenseGraph graph = new DenseGraph(10);
        graph.addEdge(0, 1, 0.5);
        Iterator iter = graph.iterator(0);
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
}
