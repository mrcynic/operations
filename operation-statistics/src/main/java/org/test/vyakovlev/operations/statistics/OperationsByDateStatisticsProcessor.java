package org.test.vyakovlev.operations.statistics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperationsByDateStatisticsProcessor {

    public List<String> getStatistics(List<OperationRecord> operationRecords) {
        return operationRecords.stream()
                .collect(Collectors.groupingBy(op -> op.getTime().toLocalDate(),
                        Collectors.summingDouble(OperationRecord::getSum)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " " + e.getValue().toString())
                .collect(Collectors.toList());
    }

}
