package org.test.vyakovlev.operations.statistics;

import java.time.LocalDateTime;

public class OperationRecord {

    private LocalDateTime time;
    private int officeId;
    private double sum;

    public OperationRecord() {
    }

    public OperationRecord(LocalDateTime time, int officeId, double sum) {
        this.time = time;
        this.officeId = officeId;
        this.sum = sum;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
