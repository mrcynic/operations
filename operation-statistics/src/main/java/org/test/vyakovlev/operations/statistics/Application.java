package org.test.vyakovlev.operations.statistics;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("3 args required");
            return;
        }

        String operationsFile = args[0];
        if (Files.notExists(Paths.get(operationsFile))) {
            System.out.println(operationsFile + " doesn't exist");
            return;
        }

        OperationRecordFileParser parser = new OperationRecordFileParser();

        List<OperationRecord> operations = parser.parse(Paths.get(operationsFile));
        OperationsByDateStatisticsProcessor byDateStatisticsProcessor = new OperationsByDateStatisticsProcessor();
        List<String> byDateStatistics = byDateStatisticsProcessor.getStatistics(operations);

        OperationsByOfficeStatisticsProcessor byOfficeStatisticsProcessor = new OperationsByOfficeStatisticsProcessor();
        List<String> byOfficeStatistics = byOfficeStatisticsProcessor.getStatistics(operations);

        Map<Path, List<String>> map = new HashMap<>();
        map.put(Paths.get(args[1]), byDateStatistics);
        map.put(Paths.get(args[2]), byOfficeStatistics);
        StatisticsFileExporter exporter = new StatisticsFileExporter();
        exporter.export(map);
    }
}
