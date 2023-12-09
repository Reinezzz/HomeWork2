package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        DataBaseHandler databaseHandler = new DataBaseHandler(new PostgreSQLConnection()); // Используем java.main.java.H2Connection или другой подходящий класс

        List<Tree> trees = databaseHandler.readTrees();

        int totalLeaves = databaseHandler.calculateTotalLeaves(trees);

        writeResultToFile("output.csv", totalLeaves);
    }

    private static void writeResultToFile(String filename, int totalLeaves) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(String.valueOf(totalLeaves));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}