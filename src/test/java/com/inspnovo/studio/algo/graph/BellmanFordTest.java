package com.inspnovo.studio.algo.graph;

import org.junit.Test;

public class BellmanFordTest {

    @Test
    public void testBellmanFord(){
        Graph graph = new SparseGraph(5, true);
        graph.addEdge(0, 1, 5d);
        graph.addEdge(0, 2, 2d);
        graph.addEdge(0, 3, 6d);
        graph.addEdge(1, 2, -4d);
        graph.addEdge(1, 4, 2d);
        graph.addEdge(2, 4, 5d);
        graph.addEdge(2, 3, 3d);
        graph.addEdge(4, 3, -3d);

        BellmanFordPathFinder finder = new BellmanFordPathFinder(graph, 0);
        finder.show(2);
        finder.show(4);
        finder.show(3);
    }
}
