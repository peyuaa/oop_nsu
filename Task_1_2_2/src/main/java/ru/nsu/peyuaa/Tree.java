/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.util.*;

public class Tree<T> implements Iterable<Tree.Node<T>> {
    static class Node<T> {
        T value;
        Node<T> parent;
        List<Node<T>> children;

        public Node(T value) {
            this.value = value;
            this.children = new LinkedList<>();
        }

        private void addChild(Node<T> child) {
            child.parent = this;
            children.add(child);
        }

        private void deleteChild(Node<T> child) {
            children.remove(child);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) && children.equals(node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, children);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tree<?> tree = (Tree<?>) o;
        return Objects.equals(root, tree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new BreadthFirstSearchIterator();
    }

    class BreadthFirstSearchIterator implements Iterator<Node<T>> {
        LinkedList<Node<T>> queue = new LinkedList<>();
        boolean isRootAdded = false;

        @Override
        public boolean hasNext() {
            if (!isRootAdded) {
                queue.add(root);
                isRootAdded = true;
            }
            return queue.size() != 0;
        }

        @Override
        public Node<T> next() {
            Node<T> currentNode = queue.poll();
            queue.addAll(currentNode.children);
            return currentNode;
        }
    }

    class DepthFirstSearchIterator implements Iterator<Node<T>> {
        Stack<Node<T>> stack = new Stack<>();
        boolean isRootAdded = false;

        @Override
        public boolean hasNext() {
            if (!isRootAdded) {
                stack.push(root);
                isRootAdded = true;
            }
            return !stack.isEmpty();
        }

        @Override
        public Node<T> next() {
            Node<T> currentNode = stack.pop();
            for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                stack.push(currentNode.children.get(i));
            }
            return currentNode;
        }
    }

    private Node<T> root;

    private Node<T> breadthFirstSearch(T value) {
        Iterator<Node<T>> bfsIterator = new BreadthFirstSearchIterator();

        while (bfsIterator.hasNext()) {
            Node<T> currentNode = bfsIterator.next();
            if (currentNode.value.equals(value)) {
                return currentNode;
            }
        }

        return null;
    }

    public Node<T> add(T value) {
        Node<T> node = new Node<>(value);
        if (root == null) {
            root = node;
        } else {
            root.addChild(node);
        }
        return node;
    }

    public Node<T> add(Node<T> parent, T value) {
        Node<T> node = new Node<>(value);
        parent.addChild(node);
        return node;
    }

    public void delete(T value) {
        Node<T> node = breadthFirstSearch(value);
        if (node != null) {
            delete(node);
        }
    }

    public void delete(Node<T> node) {
        if (node == root) {
            root = null;
        } else {
            node.parent.deleteChild(node);
        }
    }

    public List<T> breadthFirstTraversal() {
        List<T> traverseResult = new ArrayList<>();

        Iterator<Node<T>> bfsIterator = new BreadthFirstSearchIterator();
        while (bfsIterator.hasNext()) {
            Node<T> currentNode = bfsIterator.next();
            traverseResult.add(currentNode.value);
        }
        return traverseResult;
    }

    public List<T> depthFirstTraversal() {
        List<T> traverseResult = new ArrayList<>();

        Iterator<Node<T>> dfsIterator = new DepthFirstSearchIterator();
        while (dfsIterator.hasNext()) {
            Node<T> currentNode = dfsIterator.next();
            traverseResult.add(currentNode.value);
        }
        return traverseResult;
    }
}
