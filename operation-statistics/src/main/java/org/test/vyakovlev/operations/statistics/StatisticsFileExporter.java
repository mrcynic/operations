package org.test.vyakovlev.operations.statistics;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class StatisticsFileExporter {

    public void export(Map<Path, List<String>> exportMap) {
        for (Map.Entry<Path, List<String>> entry : exportMap.entrySet()) {
            try (BufferedWriter writer = Files.newBufferedWriter(entry.getKey());) {
                for (java.lang.String s : entry.getValue()) {
                    writer.write(s);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("error occurred while writing file ");
            }
        }
    }
}
