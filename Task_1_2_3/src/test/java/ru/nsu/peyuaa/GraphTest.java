/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.peyuaa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class GraphTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Test
    void adjacencyMatrixLoadTest() throws IOException {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyMatrix("./src/test/resources/adjacencyMatrixTest.txt");
        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();

        Assertions.assertEquals(
                new String(Files.readAllBytes(Paths.get("./src/test/resources/expected/adjacencyMatrix.txt")),
                        StandardCharsets.UTF_8), outContent.toString());

        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void incidenceMatrixLoadTest() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.loadIncidenceMatrix("./src/test/resources/incidenceMatrixTest.txt");
        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();
    }

    @Test
    void adjacencyListLoadTest() throws  IOException {
        Graph<String> graph = new Graph<>();
        graph.loadAdjacencyList("./src/test/resources/adjacencyListTest.txt");
        graph.printAdjacencyMatrix();
        graph.printIncidenceMatrix();
        graph.printAdjacencyList();
    }
}
