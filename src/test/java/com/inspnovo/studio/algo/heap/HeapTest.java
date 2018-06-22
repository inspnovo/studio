package com.inspnovo.studio.algo.heap;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class HeapTest {
    @Test
    public void testIndexMinHeap(){
        IndexMinHeap heap = new IndexMinHeap(6);
        int idx = heap.offer(7);
        assertEquals(0, idx);
        idx = heap.offer(6);
        assertEquals(1, idx);
        idx = heap.offer(3);
        assertEquals(2, idx);
        idx = heap.offer(1);
        assertEquals(3, idx);
        idx = heap.offer(9);
        assertEquals(4, idx);
        idx = heap.offer(8);
        assertEquals(5, idx);
        try {
            heap.offer(10);
            Assert.fail("should fail for exceed capacity.");
        }catch (IllegalArgumentException ex){

        }
        Comparable v = heap.pull();
        assertEquals(1, v);
        v = heap.pull();
        assertEquals(3, v);
        v = heap.pull();
        assertEquals(6, v);
        v = heap.pull();
        assertEquals(7, v);
        v = heap.pull();
        assertEquals(8, v);
        v = heap.pull();
        assertEquals(9, v);
        v = heap.pull();
        assertNull(v);

    }

    @Test
    public void testChange(){
        IndexMinHeap heap = new IndexMinHeap(6);
        int idx = heap.offer(7);
        assertEquals(0, idx);

        idx = heap.offer(6);
        assertEquals(1, idx);

        idx = heap.offer(3);
        assertEquals(2, idx);

        idx = heap.offer(1);
        assertEquals(3, idx);

        idx = heap.offer(9);
        assertEquals(4, idx);

        idx = heap.offer(8);
        assertEquals(5, idx);

        Comparable v = heap.pull();
        assertEquals(1, v);

        heap.change(idx, 2);
        assertEquals(2, heap.get(5));
        v = heap.pull();
        assertEquals(2, v);
        v = heap.pull();
        assertEquals(3, v);

        try {
            heap.change(5, 10);
        }catch(IllegalStateException ex){

        }

        heap.change(1, 100);
        v = heap.pull();
        assertEquals(7, v);

    }

    @Test
    public void testPullIndex(){
        IndexMinHeap heap = new IndexMinHeap(6);
        int idx = heap.offer(7); // seq 4
        assertEquals(0, idx);

        idx = heap.offer(6); // seq 3
        assertEquals(1, idx);

        idx = heap.offer(3); // seq 2
        assertEquals(2, idx);

        idx = heap.offer(1); // seq 1
        assertEquals(3, idx);

        idx = heap.offer(9); // seq 6
        assertEquals(4, idx);

        idx = heap.offer(8); // seq 5
        assertEquals(5, idx);

        idx = heap.pullIndex();
        assertEquals(3, idx);

        idx = heap.pullIndex();
        assertEquals(2, idx);

        idx = heap.pullIndex();
        assertEquals(1, idx);

        idx = heap.pullIndex();
        assertEquals(0, idx);

        idx = heap.pullIndex();
        assertEquals(5, idx);

        idx = heap.pullIndex();
        assertEquals(4, idx);
    }

    @Test
    public void testOfferWithIndex(){
        IndexMinHeap heap = new IndexMinHeap(6);
        int idx = heap.offer(0, 7);
        assertEquals(0, idx);

        idx = heap.offer(1,6); // seq 3
        assertEquals(1, idx);

        idx = heap.offer(2,3); // seq 2
        assertEquals(2, idx);

        idx = heap.offer(3,1); // seq 1
        assertEquals(3, idx);

        idx = heap.offer(4,9); // seq 6
        assertEquals(4, idx);

        idx = heap.offer(5,8); // seq 5
        assertEquals(5, idx);

        idx = heap.pullIndex();
        assertEquals(3, idx);

        idx = heap.pullIndex();
        assertEquals(2, idx);

        idx = heap.pullIndex();
        assertEquals(1, idx);

        idx = heap.pullIndex();
        assertEquals(0, idx);

        idx = heap.pullIndex();
        assertEquals(5, idx);

        idx = heap.pullIndex();
        assertEquals(4, idx);
    }
}
