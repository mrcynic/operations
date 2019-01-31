package org.test.vyakovlev.operations.statistics;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OperationRecordFileParser {

    private static final int INDEX_TIME = 0;
    private static final int INDEX_OFFICE_ID = 1;
    private static final int INDEX_SUM = 2;

    public List<OperationRecord> parse(Path inputFilePath) {
        List<OperationRecord> operationRecords;
        try (Stream<String> lines = Files.lines(inputFilePath);) {
            operationRecords = lines.map(this::parseLine).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("error occurred while reading file " + inputFilePath.toString(), e);
        }
        return operationRecords;
    }

    private OperationRecord parseLine(String recordLine) {
        String[] chunks = recordLine.split(" ");
        if (chunks.length != 3) {
            throw new IllegalArgumentException("must be 3 values per line");
        }
        LocalDateTime time = LocalDateTime.parse(chunks[INDEX_TIME]);
        int officeId = Integer.parseInt(chunks[INDEX_OFFICE_ID]);
        double sum = Double.parseDouble(chunks[INDEX_SUM]);
        return new OperationRecord(time, officeId, sum);
    }
}
