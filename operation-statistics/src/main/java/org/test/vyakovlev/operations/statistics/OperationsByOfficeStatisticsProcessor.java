package org.test.vyakovlev.operations.statistics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperationsByOfficeStatisticsProcessor {

    public List<String> getStatistics(List<OperationRecord> operationRecords) {
        return operationRecords.stream()
                .collect(Collectors.groupingBy(OperationRecord::getOfficeId,
                        Collectors.summingDouble(OperationRecord::getSum)))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(e -> e.getKey() + " " + e.getValue().toString())
                .collect(Collectors.toList());
    }

}
