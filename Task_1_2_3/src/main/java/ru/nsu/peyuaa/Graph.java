/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Graph class.
 *
 * @param <T> type of the value.
 */
public class Graph<T> {
    private static class Vertex<T> {
        T value;
        List<Edge<T>> edges;

        public Vertex(T value) {
            this.value = value;
        }
    }

    /**
     * Edge class.
     *
     * @param <T> type of the vertex value.
     */
    public static class Edge<T> {
        int weight;
        Vertex<T> from;
        Vertex<T> to;

        /**
         * Edge constructor.
         *
         * @param weight of the edge.
         * @param from which vertex edge come out.
         * @param to which vertex edge come in.
         */
        public Edge(int weight, Vertex<T> from, Vertex<T> to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    private Map<Vertex<T>, Map<Vertex<T>, Integer>> adjacencyMatrix;
    private Map<Vertex<T>, Map<Edge<T>, Integer>> incidenceMatrix;
    private Map<Vertex<T>, Map<Vertex<T>, Integer>> adjacencyList;

    private List<Vertex<T>> vertices;
    private List<Edge<T>> edges;

    /**
     * Graph constructor. Initialize all graph's fields.
     */
    public Graph() {
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        adjacencyMatrix = new HashMap<>();
        incidenceMatrix = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    private void addVertexToVertices(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    private void addVertexToAdjacencyMatrix(Vertex<T> vertex) {
        adjacencyMatrix.put(vertex, new HashMap<>());
    }

    private void addVertexToIncidenceMatrix(Vertex<T> vertex) {
        incidenceMatrix.put(vertex, new HashMap<>());
    }

    private void addVertexToAdjacencyList(Vertex<T> vertex) {
        adjacencyList.put(vertex, new HashMap<>());
    }

    /**
     * Add vertex with the value.
     *
     * @param value of the vertex.
     */
    public void addVertex(T value) {
        Vertex<T> vertex = new Vertex<>(value);
        vertex.edges = new LinkedList<>();
        addVertexToVertices(vertex);
        addVertexToAdjacencyMatrix(vertex);
        addVertexToIncidenceMatrix(vertex);
        addVertexToAdjacencyList(vertex);
    }

    /**
     * Add vertices with values.
     *
     * @param values of the vertices.
     */
    public void addVertices(T[] values) {
        for (T value : values) {
            addVertex(value);
        }
    }

    /**
     * Return vertex with the value.
     *
     * @param value of the vertex.
     * @return vertex with the value.
     */
    public Vertex<T> getVertex(T value) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.value.equals(value)) {
                return vertex;
            }
        }
        return null;
    }

    /**
     * Return incident edges.
     *
     * @param value of the vertex.
     * @return list of the edges incident to vertex with the value.
     */
    public List<Edge<T>> getVertexEdges(T value) {
        Vertex<T> vertex = getVertex(value);
        if (vertex != null) {
            return vertex.edges;
        }
        return null;
    }

    /**
     * Delete vertex with the value in adjacency matrix.
     *
     * @param value delete vertex with the value.
     */
    private void deleteVertexFromAdjacencyMatrix(T value) {
        Vertex<T> vertex = getVertex(value);
        adjacencyMatrix.remove(vertex);

        for (Vertex<T> currentVertex : vertices) {
            if (!currentVertex.equals(vertex)) {
                adjacencyMatrix.get(currentVertex).remove(vertex);
            }
        }
    }

    /**
     * Delete vertex with the value in vertices list.
     *
     * @param value delete vertex with the value.
     */
    private void deleteVertexFromVerticesList(T value) {
        for (Iterator<Vertex<T>> iterator = vertices.listIterator(); iterator.hasNext();) {
            Vertex<T> vertex = iterator.next();
            if (vertex.value.equals(value)) {
                for (Edge<T> edge : vertex.edges) {
                    deleteEdge(edge);
                }
                iterator.remove();
            }
        }
    }

    /**
     * Delete vertex with the value in incidence matrix.
     *
     * @param value delete vertex with the value.
     */
    private void deleteVertexFromIncidenceMatrix(T value) {
        Vertex<T> vertex = getVertex(value);
        incidenceMatrix.remove(vertex);
    }

    /**
     * Delete vertex with the value in adjacency list.
     *
     * @param value delete vertex with the value.
     */
    private void deleteVertexFromAdjacencyList(T value) {
        Vertex<T> vertex = getVertex(value);
        for (Vertex<T> currentVertex : vertices) {
            if (!currentVertex.equals(vertex)) {
                adjacencyList.get(currentVertex).remove(vertex);
            }
        }
        adjacencyList.remove(vertex);
    }

    /**
     * Delete vertex with the value.
     *
     * @param value delete vertex with the value.
     */
    public void deleteVertex(T value) {
        deleteVertexFromAdjacencyMatrix(value);
        deleteVertexFromIncidenceMatrix(value);
        deleteVertexFromAdjacencyList(value);
        deleteVertexFromVerticesList(value);
    }

    /**
     * Add edge in the list of edges.
     *
     * @param edge to add.
     */
    private void addEdgeToEdges(Edge<T> edge) {
        edges.add(edge);
        edge.from.edges.add(edge);
        edge.to.edges.add(edge);
    }

    /**
     * Add edge in adjacency matrix.
     *
     * @param edge to add.
     */
    private void addEdgeInAdjacencyMatrix(Edge<T> edge) {
        adjacencyMatrix.get(edge.to).put(edge.from, edge.weight);
    }

    /**
     * Add edge in incidence matrix.
     *
     * @param edge to add.
     */
    private void addEdgeInIncidenceMatrix(Edge<T> edge) {
        incidenceMatrix.get(edge.from).put(edge, -edge.weight);
        incidenceMatrix.get(edge.to).put(edge, edge.weight);
    }

    /**
     * Add edge in adjacency list.
     *
     * @param edge to add.
     */
    private void addEdgeInAdjacencyList(Edge<T> edge) {
        adjacencyList.get(edge.from).put(edge.to, edge.weight);
    }

    /**
     * Add new edge.
     *
     * @param weight of edge.
     * @param from which vertex
     * @param to which vertex
     */
    public void addEdge(int weight, Vertex<T> from, Vertex<T> to) {
        Edge<T> edge = new Edge<>(weight, from, to);
        addEdgeToEdges(edge);
        addEdgeInAdjacencyMatrix(edge);
        addEdgeInIncidenceMatrix(edge);
        addEdgeInAdjacencyList(edge);
    }

    /**
     * Delete edge from edges list.
     *
     * @param edge we want to delete.
     */
    private void deleteEdgeFromEdges(Edge<T> edge) {
        edge.from.edges.remove(edge);
        edge.to.edges.remove(edge);
        edges.remove(edge);
    }

    /**
     * Delete edge from adjacency matrix.
     *
     * @param edge we want to delete.
     */
    private void deleteEdgeFromAdjacencyMatrix(Edge<T> edge) {
        adjacencyMatrix.get(edge.from).put(edge.to, 0);
    }

    /**
     * Delete edge from incidence matrix.
     *
     * @param edge we want to delete.
     */
    private void deleteEdgeFromIncidenceMatrix(Edge<T> edge) {
        for (Vertex<T> vertex : vertices) {
            if (incidenceMatrix.get(vertex).containsKey(edge)) {
                incidenceMatrix.get(vertex).remove(edge);
            }
        }
    }

    /**
     * Delete edge from adjacency list.
     *
     * @param edge we want to delete.
     */
    private void deleteEdgeFromAdjacencyList(Edge<T> edge) {
        adjacencyList.get(edge.from).remove(edge.to);
    }

    /**
     * Delete edge.
     *
     * @param edge we want to delete.
     */
    public void deleteEdge(Edge<T> edge) {
        deleteEdgeFromAdjacencyMatrix(edge);
        deleteEdgeFromIncidenceMatrix(edge);
        deleteEdgeFromAdjacencyList(edge);
        deleteEdgeFromEdges(edge);
    }

    /**
     * Return vertex value.
     *
     * @param vertex which value we want to get.
     * @return vertex value.
     */
    public T getValue(Vertex<T> vertex) {
        return vertex.value;
    }

    /**
     * Change vertex value.
     *
     * @param vertex which value we want to change.
     * @param value is new vertex value.
     */
    public void changeValue(Vertex<T> vertex, T value) {
        vertex.value = value;
    }

    /**
     * Change edge weight.
     *
     * @param edge which weight we want to change.
     * @param weight is a new weight value.
     */
    private void changeWeightInEdges(Edge<T> edge, int weight) {
        edge.weight = weight;
    }

    /**
     * Change edge weight in adjacency matrix.
     *
     * @param edge which weight we want to change.
     * @param weight is a new weight value.
     */
    private void changeWeightInAdjacencyMatrix(Edge<T> edge, int weight) {
        adjacencyMatrix.get(edge.to).put(edge.from, weight);
    }

    /**
     * Change edge weight in incidence matrix.
     *
     * @param edge which weight we want to change.
     * @param weight is a new weight value.
     */
    private void changeWeightInIncidenceMatrix(Edge<T> edge, int weight) {
        incidenceMatrix.get(edge.from).put(edge, -weight);
        incidenceMatrix.get(edge.to).put(edge, weight);
    }

    /**
     * Change edge weight in adjacency list.
     *
     * @param edge which weight we want to change.
     * @param weight is a new weight value.
     */
    private void changeWeightInAdjacencyList(Edge<T> edge, int weight) {
        adjacencyList.get(edge.from).put(edge.to, weight);
    }

    /**
     * Change edge weight.
     *
     * @param edge which weight we want to change.
     * @param weight is a new weight value.
     */
    public void changeWeight(Edge<T> edge, int weight) {
        changeWeightInEdges(edge, weight);
        changeWeightInAdjacencyMatrix(edge, weight);
        changeWeightInIncidenceMatrix(edge, weight);
        changeWeightInAdjacencyList(edge, weight);
    }

    /**
     * Return edge weight.
     *
     * @param edge which weight we want to get.
     * @return edge weight.
     */
    public int getWeight(Edge<T> edge) {
        return edge.weight;
    }

    /**
     * Print adjacency matrix.
     */
    public void printAdjacencyMatrix() {
        System.out.println("Adjacency matrix:");
        for (Vertex<T> vertex : vertices) {
            System.out.print(vertex.value + " ");
        }
        System.out.println();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                int weight = adjacencyMatrix.get(vertices.get(i)).get(vertices.get(j)) != null
                        ? adjacencyMatrix.get(vertices.get(i)).get(vertices.get(j)) : 0;
                System.out.print(weight + " ");
            }
            System.out.println();
        }
    }

    /**
     * Print incidence matrix.
     */
    public void printIncidenceMatrix() {
        System.out.println("Incidence matrix:");
        for (Vertex<T> vertex : vertices) {
            System.out.print(vertex.value + " ");
        }
        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                int weight = incidenceMatrix.get(vertices.get(i)).get(edges.get(j)) != null
                        ? incidenceMatrix.get(vertices.get(i)).get(edges.get(j)) : 0;
                System.out.print(weight + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints adjacency list.
     */
    public void printAdjacencyList() {
        System.out.println("Adjacency list:");
        for (Vertex<T> vertex : vertices) {
            System.out.print(vertex.value + " ");
        }
        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencyList.containsKey(vertices.get(i))) {
                System.out.print(vertices.get(i).value + " ");
                for (int j = 0; j < vertices.size(); j++) {
                    if (adjacencyList.get(vertices.get(i)).containsKey(vertices.get(j))) {
                        System.out.print(vertices.get(j).value + " "
                                + adjacencyList.get(vertices.get(i)).get(vertices.get(j)) + " ");
                    }
                }
                System.out.println();
            }

        }
    }

    /**
     * Loads graph's initial state from adjacency matrix.
     * First line of the file - values of nodes.
     * The order of the vertices in the matrix corresponds
     * to the order of the vertex values in the first line of the file.
     * A_ij = N if there is an edge from j to i with weight N.
     * A_ij = 0 otherwise.
     *
     * @param file with initial data.
     * @throws IOException if there is any problems with file reading.
     */
    public void loadAdjacencyMatrix(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        for (int i = 0; i < verticesValues.length; i++) {
            currentLine = reader.readLine();
            int[] weights = Arrays.stream(currentLine.split(" "))
                            .mapToInt(Integer::parseInt).toArray();

            Vertex<T> toVertex = getVertex((T) verticesValues[i]);

            for (int j = 0; j < weights.length; j++) {
                if (weights[j] != 0) {
                    addEdge(weights[j], getVertex((T) verticesValues[j]), toVertex);
                }
            }
        }
    }

    /**
     * Loads graph's initial state from incidence matrix.
     * First line of the file - values of vertices.
     * The order of the vertices in the matrix corresponds
     * to the order of the vertex values in the first line of the file.
     * A_ij more than 0 if there is an edge into vertex i.
     * A_ij less than 0 if there is and edge from vertex i.
     * A_ij = 0 otherwise.
     *
     * @param file with initial data.
     * @throws IOException if there is any problems with file reading.
     */
    public void loadIncidenceMatrix(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        int[][] weights = new int[verticesValues.length][];

        for (int i = 0; i < verticesValues.length; i++) {
            currentLine = reader.readLine();
            weights[i] = Arrays.stream(currentLine.split(" "))
                            .mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < weights[0].length; i++) {
            int fromVertexIndex = -1;
            int toVertexIndex = -1;

            for (int j = 0; j < weights.length; j++) {
                if (weights[j][i] > 0) {
                    toVertexIndex = j;
                } else if (weights[j][i] < 0) {
                    fromVertexIndex = j;
                }
            }

            addEdge(weights[toVertexIndex][i], getVertex((T) verticesValues[fromVertexIndex]),
                    getVertex((T) verticesValues[toVertexIndex]));
        }
    }

    /**
     * Loads graph's initial state from adjacency list.
     * First line of the file - values of nodes.
     * Other lines - first value in the line is a node from which edges go to another vertex.
     * After first values there is a pairs
     * [node_value] [weight of edge from first vertex in the line to the vertex with [node_balue]]
     *
     * @param file with initial data.
     * @throws IOException if there is any problems with file reading.
     */
    public void loadAdjacencyList(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        while ((currentLine = reader.readLine()) != null) {
            verticesValues = currentLine.split(" ");
            for (int i = 1; i < verticesValues.length; i = i + 2) {
                addEdge(Integer.parseInt(verticesValues[i + 1]),
                        getVertex((T) verticesValues[0]), getVertex((T) verticesValues[i]));
            }
        }
    }
}
