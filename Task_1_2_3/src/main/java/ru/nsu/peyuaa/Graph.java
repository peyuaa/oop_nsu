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

    private List<Vertex<T>> vertices;
    private List<Edge<T>> edges;

    public Graph() {
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        adjacencyMatrix = new HashMap<>();
    }

    private void addVertexToVertices(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    private void addVertexToAdjacencyMatrix(Vertex<T> vertex) {
        adjacencyMatrix.put(vertex, new HashMap<Vertex<T>, Integer>());
    }

    public void addVertex(T value) {
        Vertex<T> vertex = new Vertex<>(value);
        addVertexToVertices(vertex);
        addVertexToAdjacencyMatrix(vertex);
    }

    public void addVertices(T[] values) {
        for (T value : values) {
            vertices.add(new Vertex<>(value));
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

    public void deleteVertex(T value) {
        deleteVertexFromAdjacencyMatrix(value);
        deleteVertexFromVerticesList(value);
    }

    private void addEdgeToEdges(int weight, Vertex<T> from, Vertex<T> to) {
        Edge<T> edge = new Edge<>(weight, from, to);
        edges.add(edge);
        from.edges.add(edge);
        to.edges.add(edge);
    }

    private void addEdgeInAdjacencyMatrix(int weight, Vertex<T> from, Vertex<T> to) {
        adjacencyMatrix.get(from).put(to, weight);
    }

    public void addEdge(int weight, Vertex<T> from, Vertex<T> to) {
        addEdgeToEdges(weight, from, to);
        addEdgeInAdjacencyMatrix(weight, from, to);
    }

    private void deleteEdgeFromEdges(Edge<T> edge) {
        edge.from.edges.remove(edge);
        edge.to.edges.remove(edge);
        edges.remove(edge);
    }

    private void deleteEdgeFromAdjacencyMatrix(Edge<T> edge) {
        adjacencyMatrix.get(edge.from).put(edge.to, 0);
    }
    public void deleteEdge(Edge<T> edge) {
        deleteEdgeFromEdges(edge);
        deleteEdgeFromAdjacencyMatrix(edge);
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

    public void changeWeight(Edge<T> edge, int weight) {
        changeWeightInEdges(edge, weight);
        changeWeightInAdjacencyMatrix(edge, weight);
    }

    public int getWeight(Edge<T> edge) {
        return edge.weight;
    }

    public void loadAdjacencyMatrix(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        String[] verticesValues = currentLine.split(" ");
        addVertices((T[]) verticesValues);

        for (int i = 0 ; i < verticesValues.length; i++) {
            currentLine = reader.readLine();
            int[] weights = Arrays.stream(currentLine.split(" ")).mapToInt(Integer::parseInt).toArray();

            Vertex<T> firstVertex = getVertex((T) verticesValues[i]);

            for (int j = 0; j < weights.length; j++) {
                if (weights[j] != 0) {
                    addEdge(weights[j], firstVertex, getVertex((T) verticesValues[j]));
                }
            }
        }
    }
}
