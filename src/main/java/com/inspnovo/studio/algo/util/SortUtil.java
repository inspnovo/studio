package com.inspnovo.studio.algo.util;

import edu.princeton.introcs.utils.StdOut;

public class SortUtil {
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static boolean greater(Comparable a, Comparable b){
        return a.compareTo(b) > 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void exch(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static boolean isSorted(Comparable[] a){
        for(int i=1; i<a.length; i++){
            if(less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void show(Comparable[] a){
        int n=a.length;
        for (int i=0; i<n; i++){
            StdOut.print(a[i]);
            StdOut.print(" ");
        }
        StdOut.println();
    }

    public static void show(int[] a){
        int n=a.length;
        for (int i=0; i<n; i++){
            StdOut.print(a[i]);
            StdOut.print(" ");
        }
        StdOut.println();
    }

    public static void show(Comparable[] a, int lo, int hi){
        for (int i=lo; i<=hi; i++){
            StdOut.print(a[i]);
            StdOut.print();
        }
        StdOut.println();
    }

    public static String loop(String content, int times){
        StringBuilder buf = new StringBuilder();
        for(int i=0; i< times; i++){
            buf.append(content);
        }
        return buf.toString();
    }
}
