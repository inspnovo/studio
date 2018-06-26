package com.inspnovo.studio.algo.graph;

import com.inspnovo.studio.algo.heap.IndexMinHeap;

import java.util.Iterator;

/**
 * 基准的Dijkstra实现，认为graph中不存在负权边
 * 对于无权图，广度优先访问产生的既是最短路径
 */
public class DijkstraPathFinder {

    private Graph graph;
    // distTo[v] 累计到目前，到顶点v的距离
    private Double[] distTo;
    private boolean[] marked;
    // from[v], 从哪个顶点到达顶点v
    private Edge[] from;
    private int source;
    private IndexMinHeap heap;

    public DijkstraPathFinder(Graph graph, int source){
        this.graph = graph;
        int numOfVertex = graph.getNumOfVertex();
        distTo = new Double[numOfVertex];
        marked = new boolean[numOfVertex];
        from   = new Edge[numOfVertex];
        for(int v=0; v<numOfVertex; v++){
            distTo[v] = 0d;
            marked[v] = false;
            from[v] = null;
        }
        this.source = source;
        heap = new IndexMinHeap(numOfVertex);

        // 遍历
        marked[source] = true;
        heap.offer(source, distTo[source]);

        while(!heap.isEmpty()){
            int v = heap.pullIndex();
            marked[v] = true;
            Iterator<Edge> iter = graph.iterator(v);
            while(iter.hasNext()){
                Edge edge = iter.next();
                if(null == edge || marked[edge.getTo()]) continue;
                int w = edge.getTo();

                if(null == from[w]/*顶点的初始状态*/ || distTo[v] + edge.getWeight() < distTo[w]/*try relax*/){
                    from[w] = edge;
                    distTo[w] = distTo[v] + edge.getWeight();
                    if(heap.contains(w)){
                        // update the dist
                        heap.change(w, distTo[w]);
                    }else{
                        heap.offer(w, distTo[w]);
                    }
                }
            }
        }

    }

    /**
     * show the path from source to w
     * @param w vertex w
     */
    public void show(int w){
        if(0 > w || w >= this.graph.getNumOfVertex()){
            return;
        }
        Edge edge = from[w];
        if(null == edge) return;
        while(null != edge && from[edge.getFrom()] != edge){
            System.out.println(edge);
            edge = from[edge.getFrom()];
        }
    }
}
