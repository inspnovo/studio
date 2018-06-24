package com.inspnovo.studio.algo.graph;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class GraphTest {
    @Test
    public void testLazyPrimForDenseGraph(){
        Graph g = new DenseGraph(8);
        g.addEdge(0, 2, 0.26);
        g.addEdge(0, 4, 0.38);
        g.addEdge(0, 6, 0.58);
        g.addEdge(0, 7, 0.16);
        g.addEdge(1, 2, 0.36);
        g.addEdge(1, 3, 0.29);
        g.addEdge(1, 5, 0.32);
        g.addEdge(1, 7, 0.19);
        g.addEdge(2, 3, 0.17);
        g.addEdge(2, 6, 0.40);
        g.addEdge(2, 7, 0.34);
        g.addEdge(3, 6, 0.52);
        g.addEdge(4, 5, 0.35);
        g.addEdge(4, 6, 0.93);
        g.addEdge(4, 7, 0.37);
        g.addEdge(5, 7, 0.28);
        // g.show();
        LazyPrim lazyPrim = new LazyPrim(g);
        lazyPrim.show();
    }

    @Test
    public void testLazyPrimForSparseGraph(){
        Graph g = new SparseGraph(8);
        g.addEdge(0, 2, 0.26);
        g.addEdge(0, 4, 0.38);
        g.addEdge(0, 6, 0.58);
        g.addEdge(0, 7, 0.16);
        g.addEdge(1, 2, 0.36);
        g.addEdge(1, 3, 0.29);
        g.addEdge(1, 5, 0.32);
        g.addEdge(1, 7, 0.19);
        g.addEdge(2, 3, 0.17);
        g.addEdge(2, 6, 0.40);
        g.addEdge(2, 7, 0.34);
        g.addEdge(3, 6, 0.52);
        g.addEdge(4, 5, 0.35);
        g.addEdge(4, 6, 0.93);
        g.addEdge(4, 7, 0.37);
        g.addEdge(5, 7, 0.28);
        // g.show();
        LazyPrim lazyPrim = new LazyPrim(g);
        lazyPrim.show();
    }

    @Test
    public void testPrimForDenseGraph(){
        Graph g = new DenseGraph(8);
        g.addEdge(0, 2, 0.26);
        g.addEdge(0, 4, 0.38);
        g.addEdge(0, 6, 0.58);
        g.addEdge(0, 7, 0.16);
        g.addEdge(1, 2, 0.36);
        g.addEdge(1, 3, 0.29);
        g.addEdge(1, 5, 0.32);
        g.addEdge(1, 7, 0.19);
        g.addEdge(2, 3, 0.17);
        g.addEdge(2, 6, 0.40);
        g.addEdge(2, 7, 0.34);
        g.addEdge(3, 6, 0.52);
        g.addEdge(4, 5, 0.35);
        g.addEdge(4, 6, 0.93);
        g.addEdge(4, 7, 0.37);
        g.addEdge(5, 7, 0.28);

        Prim prim = new Prim(g);
        prim.show();
    }

    @Test
    public void testPrimForSparseGraph(){
        Graph g = new SparseGraph(8);
        g.addEdge(0, 2, 0.26);
        g.addEdge(0, 4, 0.38);
        g.addEdge(0, 6, 0.58);
        g.addEdge(0, 7, 0.16);
        g.addEdge(1, 2, 0.36);
        g.addEdge(1, 3, 0.29);
        g.addEdge(1, 5, 0.32);
        g.addEdge(1, 7, 0.19);
        g.addEdge(2, 3, 0.17);
        g.addEdge(2, 6, 0.40);
        g.addEdge(2, 7, 0.34);
        g.addEdge(3, 6, 0.52);
        g.addEdge(4, 5, 0.35);
        g.addEdge(4, 6, 0.93);
        g.addEdge(4, 7, 0.37);
        g.addEdge(5, 7, 0.28);

        Prim prim = new Prim(g);
        prim.show();
    }

    @Test
    public void testLoadFromFile(){

        String filename = Resources.getResource("graph1.dat").getFile();
        File file = new File(filename);

        Assert.assertTrue(file.exists());
        try {

            List<String> lines = Files.readLines(file, Charset.forName("UTF-8"));
            int numOfVertex = Integer.parseInt(lines.get(0));
            Graph graph = new SparseGraph(numOfVertex);
            for(int line = 1; line < lines.size(); line++){
                Edge edge = Edge.parse(lines.get(line));
                graph.addEdge(edge);
            }
            graph.show();
        } catch (IOException e) {
            //e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
