/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setOurOutAndErr() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void setOriginalOutAndErr() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void adjacencyMatrixLoadTest() throws IOException {
        Graph<String> graph = new Graph<>();

        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("adjacencyMatrixTest.txt"));
        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(Files.readString(Paths.get(
                "./src/test/resources/expected/adjacencyMatrix.txt")),
                outContent.toString());
    }

    @Test
    void incidenceMatrixLoadTest() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadIncidenceMatrix(getClass().getClassLoader().getResourceAsStream("incidenceMatrixTest.txt"));
        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(Files.readString(Paths.get(
                "./src/test/resources/expected/incidenceMatrix.txt")),
                outContent.toString());
    }

    @Test
    void adjacencyListLoadTest() throws  IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyList("./src/test/resources/adjacencyListTest.txt");
        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(Files.readString(Paths.get(
                "./src/test/resources/expected/adjacencyList.txt")),
                outContent.toString());
    }

    @Test
    void changeWeight() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("adjacencyMatrixTest.txt"));

        List<Graph.Edge<String>> edges = graph.getVertexEdges("A");

        for (Graph.Edge<String> edge : edges) {
            graph.changeWeight(edge, 7);
        }

        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(Files.readString(Paths.get(
                "./src/test/resources/expected/changeWeight.txt")),
                outContent.toString());
    }

    @Test
    void changeVertexValue() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("adjacencyMatrixTest.txt"));

        graph.changeValue(graph.getVertex("A"), "Z");

        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(Files.readString(Paths.get(
                "./src/test/resources/expected/changeVertexValue.txt")),
                outContent.toString());
    }

    @Test
    void changeVertexValue2() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("adjacencyMatrixTest.txt"));

        graph.changeValue(graph.getVertex("B"), "N");

        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(
                Files.readString(Paths.get("./src/test/resources/expected/changeVertexValue2.txt")),
                outContent.toString());
    }

    @Test
    void deleteEdge() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("adjacencyMatrixTest.txt"));

        List<Graph.Edge<String>> edges = graph.getVertexEdges("A");

        for (Graph.Edge<String> edge : edges) {
            if (edge.weight == 5) {
                graph.deleteEdge(edge);
                break;
            }
        }

        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(
                Files.readString(Paths.get("./src/test/resources/expected/deleteEdge.txt")),
                outContent.toString());
    }

    @Test
    void deleteVertex() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("adjacencyMatrixTest.txt"));

        graph.deleteVertex("A");

        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(
                Files.readString(Paths.get("./src/test/resources/expected/deleteVertex.txt")),
                outContent.toString());
    }

    @Test
    void sort() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix(getClass().getClassLoader().getResourceAsStream("DAGadjacencyMatrix.txt"));

        graph.sortVertices("A");

        Assertions.assertEquals(
                Files.readString(Paths.get("./src/test/resources/expected/sortTest.txt")),
                outContent.toString());
    }
}
