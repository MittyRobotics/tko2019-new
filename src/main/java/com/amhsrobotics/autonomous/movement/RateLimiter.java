package com.amhsrobotics.autonomous.movement;

public class RateLimiter {
    private static RateLimiter ourInstance = new RateLimiter();

    public static RateLimiter getInstance() {
        return ourInstance;
    }

    private RateLimiter() {
    }

    private double maxVelocityChange = 0;
    private double maxVelocity = 0;

    private double maxPercentChange = 0;
    private double maxPercent = 0;

    public void setVelocityParameters(double maxChange, double maxVelocity){
        this.maxVelocityChange = maxChange;
        this.maxVelocity = maxVelocity;
    }

    public double limitVelocityRate(double currentVelocity, double desiredVelocity){
        double sign = Math.signum(desiredVelocity-currentVelocity);

        if(Math.abs(desiredVelocity-currentVelocity) < maxVelocityChange){
            return desiredVelocity;
        }
        else {
            System.out.println(currentVelocity + " " + desiredVelocity + " " + currentVelocity + (70 * sign));
            return currentVelocity + (70 * sign);
        }
    }

    public void setPercentOutputParameters(double maxChange, double maxPercent){
        this.maxPercentChange = maxChange;
        this.maxPercent = maxPercent;
    }

    public double limitPercentRate(double currentPercent, double desiredPercent){
        double sign = Math.signum(desiredPercent-currentPercent);

        if(Math.abs(desiredPercent-currentPercent) < maxPercentChange){
            System.out.println(currentPercent + " " + desiredPercent);
            return desiredPercent;
        }
        else {
            System.out.println(currentPercent + " " + desiredPercent + " " + maxPercent + " " +  Math.min(currentPercent + (maxPercentChange * sign), maxPercent) + " " + maxPercentChange);
            return Math.min(currentPercent + (maxPercentChange * sign), maxPercent);
        }
    }
}
