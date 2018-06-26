package com.inspnovo.studio.algo.graph;

public class Edge implements Comparable<Edge> {
    private static final Double WEIGHT_ZERO = 0d;
    private int from;
    private int to;
    private Double weight;

    public Edge(int from, int to){
        this.from = from;
        this.to = to;
        this.weight = WEIGHT_ZERO;
    }
    public Edge(int from, int to, Double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Double getWeight() {
        return weight;
    }

    public int other(int v){
        if(v != from && v != to) {
            throw new IllegalArgumentException(String.format("invalid vertex index:%d -> (%d, %d)", v, from, to));
        }
        return from == v ? to : from;
    }

    @Override
    public String toString() {
        return String.format("edge(path:%d -> %d, weight:%s)", from, to, weight.toString());
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight.compareTo(o.weight);
    }

    public static Edge parse(String content){
        String[] vals = content.split(" ");
        int from = Integer.parseInt(vals[0]);
        int to = Integer.parseInt(vals[1]);
        Double weight = Double.parseDouble(vals[2]);
        return new Edge(from, to, weight);
    }
}
