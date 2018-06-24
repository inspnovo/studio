package com.inspnovo.studio.algo.graph;

import com.inspnovo.studio.algo.heap.MinHeap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 基准的LazyPrim实现，设定graph是个连通图
 */
public class LazyPrim {
    private Graph graph;
    private boolean[] marked;
    private List<Edge> edgeList;
    private MinHeap heap;

    public LazyPrim(Graph graph){
        this.graph = graph;
        this.marked = new boolean[graph.getNumOfVertex()];
        for(int i=0; i<graph.getNumOfVertex(); i++){
            this.marked[i]=false;
        }
        this.edgeList = new ArrayList<>();
        this.heap = new MinHeap();
        // 从顶点0开始访问
        visit(0);

        Edge cur;
        // 不使用泛型，就要依靠类型强转，后面看看怎么处理
        while (null != (cur = (Edge)heap.pull())){
            // 取到的当前边还不是横切边，这个判断满巧妙
            if(marked[cur.getFrom()] == marked[cur.getTo()])
                continue;
            edgeList.add(cur);
            if(!marked[cur.getFrom()]){
                visit(cur.getFrom());
            }
            if(!marked[cur.getTo()]){
                visit(cur.getTo());
            }
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
            if(!marked[edge.other(i)])
                heap.offer(edge);
        }
    }

    public void show(){
        for (Edge anEdge : this.edgeList) {
            System.out.println(anEdge);
        }
    }
}
