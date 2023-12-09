package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public List<Node> getAllNodes() {
        List<Node> allNodes = new ArrayList<>();
        traverse(root, allNodes);
        return allNodes;
    }

    public List<Node> getAllLeaves() {
        List<Node> leaves = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        findLeaves(root, visited, leaves);
        return leaves;
    }

    private void traverse(Node node, List<Node> nodeList) {
        nodeList.add(node);
        for (Node child : node.getChildren()) {
            traverse(child, nodeList);
        }
    }

    private void findLeaves(Node node, Set<Node> visited, List<Node> leaves) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);

        if (node.isLeaf()) {
            leaves.add(node);
        } else {
            for (Node child : node.getChildren()) {
                findLeaves(child, visited, leaves);
            }
        }
    }
    public int countLeaves() {
        return getAllLeaves().size();
    }
}
