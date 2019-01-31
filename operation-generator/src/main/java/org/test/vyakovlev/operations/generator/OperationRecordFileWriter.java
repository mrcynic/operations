package org.test.vyakovlev.operations.generator;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OperationRecordFileWriter implements Closeable {

    private final OperationRecordGenerator generator;
    private final int operationsCount;
    private final BufferedWriter writer;

    public OperationRecordFileWriter(OperationRecordGenerator generator, int operationsCount, Path outputFilePath) throws IOException {
        this.generator = generator;
        this.operationsCount = operationsCount;
        this.writer = Files.newBufferedWriter(outputFilePath);
    }

    public void write() throws IOException {
        for (int i = 0; i < operationsCount; i++) {
            writer.write(generator.generateOperationRecord());
            writer.newLine();
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
