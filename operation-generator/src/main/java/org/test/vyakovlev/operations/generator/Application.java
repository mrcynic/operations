package org.test.vyakovlev.operations.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("3 args required: \n" +
                    "offices list file \n" +
                    "number of operations \n" +
                    "output file");
            return;
        }

        String officesListFile = args[0];
        if (Files.notExists(Paths.get(officesListFile))) {
            System.out.println(officesListFile + "doesn't exist");
            return;
        }

        int operationsCount;
        try {
            operationsCount = Integer.parseInt(args[1]);
            if (operationsCount <= 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("operations number argument must be positive integer");
            return;
        }

        List<String> offices;
        try (Stream<String> lines = Files.lines(Paths.get(officesListFile))) {
            offices = lines.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("error occurred while reading file " + officesListFile + e.getMessage());
            return;
        }

        OperationRecordGenerator generator = new OperationRecordGenerator(offices);
        try (OperationRecordFileWriter writer = new OperationRecordFileWriter(generator, operationsCount,
                Paths.get(args[2]))) {
            writer.write();
        } catch (IOException e) {
            System.out.println("error occurred while writing file " + e.getMessage());
        }
    }
}
