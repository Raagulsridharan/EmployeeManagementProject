package com.employee.demoproject.entity.Enum;

public enum TaxCalculation {
    slb3TO5(300000, 500000, 0.03),
    slb5TO8(500001, 800000, 0.05),
    slb8TO10(800001, 1000000, 0.08);

    private final double lowerLimit;
    private final double upperLimit;
    private final double percentage;

    TaxCalculation(double lowerLimit, double upperLimit, double percentage) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.percentage = percentage;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public double getPercentage() {
        return percentage;
    }
}
