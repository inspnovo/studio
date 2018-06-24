package com.inspnovo.studio.algo.graph;

import com.inspnovo.studio.algo.heap.MinHeap;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {
    @Test
    public void testEdge(){
        Edge edge = new Edge(0,1, 3.5);
        Assert.assertEquals("edge(path:0 -> 1, weight:3.5)", edge.toString());

        int other = edge.other(0);
        Assert.assertEquals(1, other);

        other = edge.other(1);
        Assert.assertEquals(0, other);

        try{
            edge.other(2);
            fail("the other method should not take the vertex index out of the edge");
        }catch (IllegalArgumentException ex){

        }
    }

    @Test
    public void testEdgeInHeap(){
        MinHeap heap=new MinHeap();
        heap.offer(new Edge(0, 1, 1.8));
        heap.offer(new Edge(0, 2, 2.3));
        heap.offer(new Edge(1, 3, 1.5));
        heap.offer(new Edge(1, 4, 1.2));
        heap.offer(new Edge(2, 5, 1.9));
        heap.offer(new Edge(3, 6, 2.7));
        Edge min = (Edge)heap.pull();
        assertEquals(1.2, min.getWeight());
        min = (Edge)heap.pull();
        assertEquals(1.5, min.getWeight());
        min = (Edge)heap.pull();
        assertEquals(1.8, min.getWeight());
        min = (Edge)heap.pull();
        assertEquals(1.9, min.getWeight());
        min = (Edge)heap.pull();
        assertEquals(2.3, min.getWeight());
        min = (Edge)heap.pull();
        assertEquals(2.7, min.getWeight());
        min = (Edge)heap.pull();
        assertNull(min);
    }
}
