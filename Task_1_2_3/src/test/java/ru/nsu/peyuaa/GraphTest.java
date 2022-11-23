/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.peyuaa;

import org.junit.jupiter.api.Test;
import java.io.IOException;

class GraphTest {
    @Test
    void adjacencyMatrixLoadTest() throws IOException {
        Graph graph = new Graph();
        graph.loadAdjacencyMatrix("./src/test/resources/adjacencyTest.txt");
    }

    @Test
    void incidenceMatrixLoadTest() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadIncidenceMatrix("./src/test/resources/incidenceTest.txt");
    }
}
