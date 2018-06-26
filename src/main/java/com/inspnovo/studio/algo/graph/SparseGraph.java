package com.inspnovo.studio.algo.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 稀疏图的基准实现
 */
public class SparseGraph implements Graph{
    private int numOfVertex;
    private int numOfEdge;
    private List<Edge>[] graph;
    private boolean directed = false;
    // 记录每个顶点的入度
    private int[] indegree;

    public SparseGraph(int numOfVertex){
        this(numOfVertex, false);
    }

    public SparseGraph(int numOfVertex, boolean isDirected){
        this.numOfVertex = numOfVertex;
        this.graph = new List[numOfVertex];
        this.directed = false;
        for (int i = 0; i < this.numOfVertex; i++) {
            this.graph[i] =  new ArrayList<>();
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
        if(v < 0 || v >= numOfVertex || w < 0 || w > numOfVertex){
            throw new IllegalArgumentException("invalid vertext index");
        }
        // 此处不做是否已有边的判断，考虑遍历的性能损耗
        graph[v].add(new Edge(v, w, weight));
        indegree[w]++;
        if(!directed){
            //如果是无向图，则反向也需要链接
            graph[w].add(new Edge(w, v, weight));
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
        if(v < 0 || v >= numOfVertex || w < 0 || w >= numOfVertex){
            throw new IllegalArgumentException("invalid vertex index");
        }

        Iterator<Edge> iterator = iterator(v);
        while (iterator.hasNext()){
            Edge e = iterator.next();
            if(w == e.getTo()){
                return true;
            }
        }
        return false;
    }

    public int getNumOfVertex() {
        return numOfVertex;
    }

    public int getNumOfEdge() {
        return numOfEdge;
    }

    @Override
    public boolean isDirected() {
        return this.directed;
    }

    @Override
    public Iterator<Edge> iterator(int v) {
        return new Iter(v);
    }

    @Override
    public void show() {
        for(int i=0; i<this.numOfVertex; i++){
            Iterator<Edge> iter = this.iterator(i);
            System.out.print("vertex " + i +" : ");
            while(iter.hasNext()){
                Edge edge = iter.next();
                System.out.print(edge.getTo());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    class Iter implements Iterator<Edge>{
        private int vertex;
        private int index;

        Iter(int v){
            this.vertex = v;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < graph[this.vertex].size();
        }

        @Override
        public Edge next() {
            Edge v = graph[this.vertex].get(this.index);
            index++;
            return v;
        }
    }
}
