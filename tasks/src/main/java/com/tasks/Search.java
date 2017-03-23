package com.tasks;

public class Search {

    public int[] getTwoMin(int[] args) {
        int[] mins = (args[0] < args[1] ?
                new int[]{args[0], args[1]} : new int[]{args[1], args[0]});
        for (int i = 2; i < args.length; i++) {
            if (mins[0] > args[i]) {
                mins[1] = mins[0];
                mins[0] = args[i];
            }
            if (mins[0] < args[i] && args[i] < mins[1]) {
                mins[1] = args[i];
            }
        }
        return mins;
    }


    public int[] getTwoMax(int[] args) {
        int[] maxs = (args[0] < args[1] ?
                new int[]{args[0], args[1]} : new int[]{args[1], args[0]});
        for (int i = 2; i < args.length; i++) {
            if (args[i] > maxs[1]) {
                maxs[0] = maxs[1];
                maxs[1] = args[i];
            }
            if (maxs[0] < args[i] && args[i] < maxs[1]) {
                maxs[0] = args[i];
            }
        }
        return maxs;
    }

}
