/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeTest {

    @Test
    void bfs() {
        Tree<String> tree = new Tree<>();
        tree.add("A");
        tree.add("B");
        tree.add("C");

        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "A", "B", "C");
        List<String> got = tree.breadthFirstTraversal();

        Assertions.assertEquals(expected, got);
    }

    @Test
    void dfs() {
        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> node1 = tree.add(1);
        Tree.Node<Integer> node2 = tree.add(2);
        Tree.Node<Integer> node3 = tree.add(3);
        Tree.Node<Integer> node4 = tree.add(node2, 4);
        tree.add(node1, 5);
        tree.add(node2, 6);
        tree.add(node3, 7);
        tree.add(node4, 8);

        List<Integer> got = tree.depthFirstTraversal();
        List<Integer> expected = new ArrayList<>();
        Collections.addAll(expected, 1, 2, 4, 8, 6, 3, 7, 5);

        Assertions.assertEquals(expected, got);
    }

    @Test
    void addElement() {
        Tree<String> tree = new Tree<>();
        Tree.Node<String> nodeA = tree.add("A");
        Tree.Node<String> nodeB = tree.add("B");
        Tree.Node<String> nodeC = tree.add("C");
        Tree.Node<String> nodeK = tree.add(nodeB, "K");
        tree.add(nodeA, "D");
        tree.add(nodeB, "M");
        tree.add(nodeC, "F");
        tree.add(nodeK, "Z");

        List<String> got = tree.breadthFirstTraversal();
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "A", "B", "C", "D", "K", "M", "F", "Z");

        Assertions.assertEquals(expected, got);
    }

    @Test
    void deleteElementByValue() {
        Tree<String> got = new Tree<>();
        Tree.Node<String> nodeA = got.add("A");
        Tree.Node<String> nodeB = got.add("B");
        Tree.Node<String> nodeC = got.add("C");
        Tree.Node<String> nodeK = got.add(nodeB, "K");
        got.add(nodeA, "D");
        got.add(nodeB, "M");
        got.add(nodeC, "F");
        got.add(nodeK, "Z");
        got.delete("B");

        Tree<String> expected = new Tree<>();
        expected.add("A");
        Tree.Node<String> expectedNodeC = expected.add("C");
        expected.add("D");
        expected.add(expectedNodeC, "F");

        Assertions.assertEquals(expected, got);
    }

    @Test
    void deleteElementByNode() {
        Tree<String> got = new Tree<>();
        Tree.Node<String> nodeA = got.add("A");
        Tree.Node<String> nodeB = got.add("B");
        Tree.Node<String> nodeC = got.add("C");
        Tree.Node<String> nodeK = got.add(nodeB, "K");
        got.add(nodeA, "D");
        got.add(nodeB, "M");
        got.add(nodeC, "F");
        got.add(nodeK, "Z");
        got.delete(nodeC);

        Tree<String> expected = new Tree<>();
        expected.add("A");
        Tree.Node<String> expectedNodeB = expected.add("B");
        expected.add("D");
        Tree.Node<String> expectedNodeK = expected.add(expectedNodeB, "K");
        expected.add(expectedNodeK, "Z");
        expected.add(expectedNodeB, "M");

        Assertions.assertEquals(expected, got);
    }

    @Test
    void modificationDuringIterationBfs() {
        Tree<String> tree = new Tree<>();
        Tree.Node<String> nodeA = tree.add("A");
        Tree.Node<String> nodeB = tree.add("B");
        Tree.Node<String> nodeC = tree.add("C");
        Tree.Node<String> nodeK = tree.add(nodeB, "K");
        tree.add(nodeA, "D");
        tree.add(nodeB, "M");
        tree.add(nodeC, "F");
        tree.add(nodeK, "Z");
        Iterator<Tree.Node<String>> iterator = tree.iterator();

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    while (iterator.hasNext()) {
                        tree.delete(nodeK);
                        iterator.next();
                    }
                }
        );
    }
}
