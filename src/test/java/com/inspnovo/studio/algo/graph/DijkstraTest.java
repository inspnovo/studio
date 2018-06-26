package com.inspnovo.studio.algo.graph;

import org.junit.Test;

public class DijkstraTest {

    @Test
    public void testDijkstra(){
        Graph graph = new SparseGraph(5, true);
        graph.addEdge(0, 1, 5d);
        graph.addEdge(0, 2, 2d);
        graph.addEdge(0, 3, 6d);
        graph.addEdge(1, 4, 1d);
        graph.addEdge(2, 1, 1d);
        graph.addEdge(2, 3, 3d);
        graph.addEdge(2, 4, 5d);
        graph.addEdge(3, 4, 2d);

        DijkstraPathFinder finder = new DijkstraPathFinder(graph, 0);
        finder.show(2);
        finder.show(4);
    }
}
