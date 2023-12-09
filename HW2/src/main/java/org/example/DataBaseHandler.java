package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class DataBaseHandler {
    private final DataBaseConnection connection;

    public DataBaseHandler(DataBaseConnection connection) {
        this.connection = connection;
    }

    public List<Tree> readTrees() {
        List<Tree> trees = new ArrayList<>();

        try (Connection conn = connection.connect();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM public.\"TREES\"");
             ResultSet resultSet = statement.executeQuery()) {

            Map<Integer, Node> nodeMap = new HashMap<>();

            while (resultSet.next()) {
                int nodeId = resultSet.getInt("id");
                int parentId = resultSet.getInt("parent_id");

                Node node = nodeMap.computeIfAbsent(nodeId, k -> new Node(nodeId));
                Node parent = nodeMap.computeIfAbsent(parentId, k -> new Node(parentId));

                node.setParent(parent);
                parent.addChild(node);

                if (parentId == nodeId) {
                    Tree tree = new Tree(node);
                    trees.add(tree);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trees;
    }

    public int calculateTotalLeaves(List<Tree> trees) {
        int totalLeaves = 0;

        for (Tree tree : trees) {
            int leavesCount = tree.countLeaves();
            totalLeaves += leavesCount;
        }

        return totalLeaves;
    }
}
