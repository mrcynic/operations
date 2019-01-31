package org.test.vyakovlev.operations.generator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OperationRecordGenerator {

    private final List<String> officeIds;
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    public OperationRecordGenerator(List<String> officeIds) {
        this.officeIds = officeIds;
        toTime = LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).atStartOfDay();
        fromTime = toTime.minusYears(1);
    }

    public String generateOperationRecord() {
        return String.format("%s %s %.2f",
                generateDateTime(fromTime, toTime), getOffice(), generateSum(10_000d, 100_000d));
    }

    private String generateDateTime(LocalDateTime from, LocalDateTime to) {
        long fromEpoch = from.toEpochSecond(ZoneOffset.UTC);
        long toEpoch = to.toEpochSecond(ZoneOffset.UTC);
        long randomEpoch = fromEpoch + ThreadLocalRandom.current().nextLong(toEpoch - fromEpoch);
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(randomEpoch), ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }

    private double generateSum(double from, double to) {
        return ThreadLocalRandom.current().nextDouble(from, to + 1);
    }

    private String getOffice() {
        return officeIds.get(ThreadLocalRandom.current().nextInt(officeIds.size()));
    }
}
