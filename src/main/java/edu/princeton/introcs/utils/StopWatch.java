package edu.princeton.introcs.utils;

public class StopWatch {
    private long start = 0;

    public StopWatch(){
        reset();
    }

    public void reset(){
        start = System.currentTimeMillis();
    }

    public double elaspedTime(){
        // 秒数
        long now = System.currentTimeMillis();
        return ( now - start) / 1000.0;
    }
}
