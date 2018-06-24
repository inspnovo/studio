package com.inspnovo.studio.algo.graph;

import java.util.Iterator;

public interface Graph {

    int getNumOfVertex();

    int getNumOfEdge();

    boolean hasEdge(int v, int w);

    boolean isDirected();

    void addEdge(int v, int w, Comparable weight);

    default void addEdge(Edge edge){
        if(null == edge){
            throw new IllegalArgumentException("invalid edge");
        }
        addEdge(edge.getFrom(), edge.getTo(), edge.getWeight());
    }

    /**
     * 遍历顶点v的临边
     * @param v vertex v
     * @return edge list of from the vertex v
     */
    Iterator<Edge> iterator(int v);

    void show();
}
