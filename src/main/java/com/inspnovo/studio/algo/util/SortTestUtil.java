package com.inspnovo.studio.algo.util;

import java.util.Random;

import static com.inspnovo.studio.algo.util.SortUtil.greater;
import static com.inspnovo.studio.algo.util.SortUtil.show;


public class SortTestUtil {
    public static Comparable[] randomIntegers(int n, int max){
        Comparable[] vals = new Integer[n];
        Random rand = new Random();
        // [0...max+1)

        for(int i=0; i<n; i++){
            vals[i] = rand.nextInt(max+1);
        }

        return vals;
    }

    public static Comparable[] nearlyOrderedIntegers(int n, int degree){
        Comparable[] vals = new Integer[n];

        for(int i=0; i<n; i++){
            vals[i] = i;
        }
        Random rand = new Random();
        for(int i=0; i<degree; i++){
            SortUtil.exch(vals, rand.nextInt(n), rand.nextInt(n));
        }

        return vals;
    }

    public static Comparable[] copy(Comparable[] vals){
        Comparable[] copy = new Comparable[vals.length];

        for(int i=0; i<vals.length; i++){
            copy[i]=vals[i];
        }

        return copy;
    }

    public static boolean sorted(Comparable[] vals){
        for(int i=1; i<vals.length; i++){
            if(greater(vals[i-1], vals[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Comparable[] vals = randomIntegers(100, 100);
        Comparable[] copy = copy(vals);
        show(vals);
        Comparable[] nearlyOrderedVals = nearlyOrderedIntegers(100,10);
        show(nearlyOrderedVals);
        Comparable[] repeated = randomIntegers(1000,10);
        show(repeated);
    }
}
