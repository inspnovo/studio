package com.inspnovo.studio.algo.graph;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 基准的Bellman Ford实现，graph中可以存在负权边，但不能有环
 * 对每个顶点进行numOfVertex-1轮松弛
 * refer: [Bellman–Ford algorithm](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm)
 */
public class BellmanFordPathFinder {

    private Graph graph;
    // distTo[v] 累计到目前，到顶点v的距离
    private Double[] distTo;
    // from[v], 从哪个顶点到达顶点v
    private Edge[] from;
    private boolean hasNegativeCycle = false;
    private int source;

    public BellmanFordPathFinder(Graph graph, int source){
        this.graph = graph;
        int numOfVertex = graph.getNumOfVertex();
        distTo = new Double[numOfVertex];
        from   = new Edge[numOfVertex];
        for(int v=0; v<numOfVertex; v++){
            distTo[v] = 0d;
            from[v] = null;
        }
        this.source = source;

        // v-1 round
        for(int round=1; round<graph.getNumOfVertex(); round++){
            // for each vertex
            for(int v=0; v<graph.getNumOfVertex(); v++){
                Iterator<Edge> iterator = graph.iterator(v);
                while(iterator.hasNext()){
                    Edge edge = iterator.next();
                    if(null == edge) continue;
                    int w = edge.getTo();
                    if(null == from[w] || distTo[v] + edge.getWeight() < distTo[w]){
                        from[w] = edge;
                        distTo[w] = distTo[v] + edge.getWeight();
                    }
                }
            }
        }

        detectNegativeCycle();

    }

    /**
     * 如果在v-1轮松弛之后还能进行松弛，则说明存在环
     */
    private void detectNegativeCycle() {

        for(int v=0; v<graph.getNumOfVertex(); v++){
            Iterator<Edge> iterator = graph.iterator(v);
            while(iterator.hasNext()){
                Edge edge = iterator.next();
                if(null == edge) continue;
                int w = edge.getTo();
                if(null == from[w] || distTo[v] + edge.getWeight() < distTo[w]){
                    this.hasNegativeCycle = true;
                    return;
                }
            }
        }

        this.hasNegativeCycle = false;
    }

    /**
     * show the path from source to w
     * @param w vertex w
     */
    public void show(int w){
        if(0 > w || w >= this.graph.getNumOfVertex()){
            return;
        }
        if(hasNegativeCycle){
            System.out.println("exists negative cycle");
            return;
        }
        Stack<Edge> path = new Stack<>();
        Edge edge = from[w];
        if(null == edge) return;
        double weight = 0;
        while(null != edge && from[edge.getFrom()] != edge){
            path.push(edge);
            weight += edge.getWeight();
            //System.out.println(edge);
            edge = from[edge.getFrom()];
        }
        Joiner joiner = Joiner.on(" -> ");
        List<String> pathSet = new ArrayList<>();
        Edge cur = null;
        while (!path.isEmpty()){
            cur = path.pop();
            pathSet.add(cur.getFrom()+"["+cur.getWeight()+"]");
        }
        pathSet.add(cur.getTo()+"[totla:"+ weight +"]");
        System.out.println(joiner.join(pathSet));


    }

    public boolean hasPath(int w){
        if(0 > w || w >= this.graph.getNumOfVertex()){
            throw new IllegalArgumentException("invalid vertex index");
        }
        return null != from[w];
    }
}
