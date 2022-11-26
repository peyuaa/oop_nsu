/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.io.*;
import java.util.*;

public class Graph<T> {
    private static class Vertex<T> {
        T value;
        List<Edge<T>> edges;

        public Vertex(T value) {
            this.value = value;
        }
    }

    private static class Edge<T> {
        int weight;
        Vertex<T> from;
        Vertex<T> to;

        public Edge(int weight, Vertex<T> from, Vertex<T> to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    private Map<Vertex<T>, Map<Vertex<T>, Integer>> adjacencyMatrix;
    private Map<Vertex<T>, Map<Edge<T>, Integer>> incidenceMatrix;
    private Map<Vertex<T>, List<Vertex<T>>> adjacencyList;

    private List<Vertex<T>> vertices;
    private List<Edge<T>> edges;

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
        adjacencyList.put(vertex, new LinkedList<>());
    }

    public void addVertex(T value) {
        Vertex<T> vertex = new Vertex<>(value);
        vertex.edges = new LinkedList<>();
        addVertexToVertices(vertex);
        addVertexToAdjacencyMatrix(vertex);
        addVertexToIncidenceMatrix(vertex);
        addVertexToAdjacencyList(vertex);
    }

    public void addVertices(T[] values) {
        for (T value : values) {
            addVertex(value);
        }
    }

    public Vertex<T> getVertex(T value) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.value.equals(value)) {
                return vertex;
            }
        }
        return null;
    }

    private void deleteVertexFromAdjacencyMatrix(T value) {
        Vertex<T> vertex = getVertex(value);
        adjacencyMatrix.remove(vertex);

        for (Vertex<T> currentVertex : vertices) {
            if (!currentVertex.equals(vertex)) {
                adjacencyMatrix.get(currentVertex).remove(vertex);
            }
        }
    }

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

    private void deleteVertexFromIncidenceMatrix(T value) {
        Vertex<T> vertex = getVertex(value);
        incidenceMatrix.remove(vertex);
    }

    private void deleteVertexFromAdjacencyList(T value) {
        Vertex<T> vertex = getVertex(value);
        for (Vertex<T> currentVertex : vertices) {
            if (!currentVertex.equals(vertex)) {
                adjacencyList.get(currentVertex).remove(vertex);
            }
        }
        adjacencyList.remove(vertex);
    }

    public void deleteVertex(T value) {
        deleteVertexFromAdjacencyMatrix(value);
        deleteVertexFromIncidenceMatrix(value);
        deleteVertexFromAdjacencyList(value);
        deleteVertexFromVerticesList(value);
    }

    private void addEdgeToEdges(Vertex<T> from, Vertex<T> to, Edge<T> edge) {
        edges.add(edge);
        from.edges.add(edge);
        to.edges.add(edge);
    }

    private void addEdgeInAdjacencyMatrix(int weight, Vertex<T> from, Vertex<T> to) {
        adjacencyMatrix.get(from).put(to, weight);
    }

    private void addEdgeInIncidenceMatrix(Vertex<T> from, Edge<T> edge) {
        incidenceMatrix.get(from).put(edge, edge.weight);
    }

    private void addEdgeInAdjacencyList(Vertex<T> from, Vertex<T> to) {
        adjacencyList.get(from).add(to);
    }

    public void addEdge(int weight, Vertex<T> from, Vertex<T> to) {
        Edge<T> edge = new Edge<>(weight, from, to);
        addEdgeToEdges(from, to, edge);
        addEdgeInAdjacencyMatrix(weight, from, to);
        addEdgeInIncidenceMatrix(from, edge);
        addEdgeInAdjacencyList(from, to);
    }

    private void deleteEdgeFromEdges(Edge<T> edge) {
        edge.from.edges.remove(edge);
        edge.to.edges.remove(edge);
        edges.remove(edge);
    }

    private void deleteEdgeFromAdjacencyMatrix(Edge<T> edge) {
        adjacencyMatrix.get(edge.from).put(edge.to, 0);
    }

    private void deleteEdgeFromIncidenceMatrix(Edge<T> edge) {
        for (Vertex<T> vertex : vertices) {
            if (incidenceMatrix.get(vertex).containsKey(edge)) {
                incidenceMatrix.get(vertex).remove(edge);
            }
        }
    }

    private void deleteEdgeFromAdjacencyList(Edge<T> edge) {
        adjacencyList.get(edge.from).remove(edge.to);
    }

    public void deleteEdge(Edge<T> edge) {
        deleteEdgeFromAdjacencyMatrix(edge);
        deleteEdgeFromIncidenceMatrix(edge);
        deleteEdgeFromAdjacencyList(edge);
        deleteEdgeFromEdges(edge);
    }

    public T getValue(Vertex<T> vertex) {
        return vertex.value;
    }

    public void changeValue(Vertex<T> vertex, T value) {
        vertex.value = value;
    }


    private void changeWeightInEdges(Edge<T> edge, int weight) {
        edge.weight = weight;
    }
    private void changeWeightInAdjacencyMatrix(Edge<T> edge, int weight) {
        adjacencyMatrix.get(edge.from).put(edge.to, weight);
    }

    private void changeWeightInIncidenceMatrix(Edge<T> edge, int weight) {
        incidenceMatrix.get(edge.from).put(edge, weight);
    }

    public void changeWeight(Edge<T> edge, int weight) {
        changeWeightInEdges(edge, weight);
        changeWeightInAdjacencyMatrix(edge, weight);
        changeWeightInIncidenceMatrix(edge, weight);
    }

    public int getWeight(Edge<T> edge) {
        return edge.weight;
    }

    public void printAdjacencyMatrix() {
        for (Vertex<T> vertex : vertices) {
            System.out.print(vertex.value + " ");
        }
        System.out.println();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                int weight = adjacencyMatrix.get(vertices.get(i)).get(vertices.get(j)) != null ?
                        adjacencyMatrix.get(vertices.get(i)).get(vertices.get(j)) : 0;
                System.out.print(weight + " ");
            }
            System.out.println();
        }
    }

    public void printIncidenceMatrix() {
        for (Vertex<T> vertex : vertices) {
            System.out.print(vertex.value + " ");
        }
        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                int weight = incidenceMatrix.get(vertices.get(i)).get(edges.get(j)) != null ?
                        incidenceMatrix.get(vertices.get(i)).get(edges.get(j)) : 0;
               System.out.print(weight + " ");
            }
            System.out.println();
        }
    }

    /**
     * Loads graph's initital state from adjacency matrix.
     *
     * First line of the file - values of nodes.
     *
     * The order of the vertices in the matrix corresponds
     * to the order of the vertex values in the first line of the file.
     *
     * A_ij = N if there is an edge from j to i with weight N.
     * A_ij = 0 otherwise.
     *
     * @param file with initial data.
     * @throws IOException
     */
    public void loadAdjacencyMatrix(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        for (int i = 0 ; i < verticesValues.length; i++) {
            currentLine = reader.readLine();
            int[] weights = Arrays.stream(currentLine.split(" ")).mapToInt(Integer::parseInt).toArray();

            Vertex<T> toVertex = getVertex((T) verticesValues[i]);

            for (int j = 0; j < weights.length; j++) {
                if (weights[j] != 0) {
                    addEdge(weights[j], getVertex((T) verticesValues[j]), toVertex);
                }
            }
        }
    }

    public void loadIncidenceMatrix(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        int[][] weights = new int[verticesValues.length][verticesValues.length];

        for (int i = 0; i < verticesValues.length; i++) {
            currentLine = reader.readLine();
            weights[i] = Arrays.stream(currentLine.split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < verticesValues.length; i++) {
            int firstVertexIndex = -1;
            int secondVertexIndex = -1;

            for (int j = 0; j < verticesValues.length; j++) {
                if (weights[j][i] != 0) {
                    if (firstVertexIndex == -1) {
                        firstVertexIndex = j;
                    } else if (secondVertexIndex == -1) {
                        secondVertexIndex = j;
                        break;
                    }
                }
            }

            addEdge(weights[firstVertexIndex][i],
                    getVertex((T) verticesValues[firstVertexIndex]), getVertex((T) verticesValues[secondVertexIndex]));

            addEdge(weights[firstVertexIndex][i],
                    getVertex((T) verticesValues[secondVertexIndex]), getVertex((T) verticesValues[firstVertexIndex]));

        }
    }

    public void loadAdjacencyList(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        while ((currentLine = reader.readLine()) != null) {
            verticesValues = currentLine.split(" ");
            for (int i = 1; i < verticesValues.length; i++) {
                addEdge(1, getVertex((T) verticesValues[0]), getVertex((T) verticesValues[i]));
            }
        }
    }
}
