package com.inspnovo.studio.algo.graph;

import com.inspnovo.studio.algo.heap.IndexMinHeap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Prim实现，设定graph是个连通图
 */
public class Prim {
    private Graph graph;
    private boolean[] marked;
    private List<Edge> edgeList;
    // 使用最小索引堆进行最小边的跟踪
    private IndexMinHeap heap;

    public Prim(Graph graph){
        this.graph = graph;
        this.marked = new boolean[graph.getNumOfVertex()];
        for(int i=0; i<graph.getNumOfVertex(); i++){
            this.marked[i]=false;
        }
        this.edgeList = new ArrayList<>();
        this.heap = new IndexMinHeap(graph.getNumOfVertex());
        // 从顶点0开始访问
        visit(0);

        while (!heap.isEmpty()){
            int v = heap.pullIndex(); // 到顶点v存在最小横切边
            Edge cur = (Edge) heap.get(v);
            if(null == cur){
                throw new IllegalStateException("invalid data state");
            }
            edgeList.add(cur);
            visit(v);
        }
    }

    private void visit(int i) {
        if(marked[i]) return;

        marked[i] = true;
        // 遍历这个顶点的临边
        Iterator<Edge> iterator = graph.iterator(i);
        while (iterator.hasNext()){
            Edge edge = iterator.next();
            if(null == edge){
                continue;
            }
            int w = edge.other(i);
            // 该顶点未被访问
            if(!marked[w]) {
                Edge e = (Edge) heap.get(w);
                if(null == e){ // 该顶点不在横切边集合中
                    heap.offer(w, edge);
                }else {
                    // 横切边的权重有更加小的
                    if(edge.compareTo(e) < 0){
                        heap.change(w, edge);
                    }
                }
            }
        }
    }

    public void show(){
        for(Edge anEdge : this.edgeList){
            System.out.println(anEdge);
        }
    }
}
