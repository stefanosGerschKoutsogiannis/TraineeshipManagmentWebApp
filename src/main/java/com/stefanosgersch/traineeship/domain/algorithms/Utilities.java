package com.stefanosgersch.traineeship.domain.algorithms;

import java.util.Set;

public class Utilities {

    private Utilities() {}  // so users can not instantiate

    public static double calculateJaccardSimilarity(Set<String> interests, Set<String> topics) {
        int intersect = findCommon(interests, topics);
        int union = interests.size() + topics.size();
        return intersect / (double) union;
    }

    public static int findCommon(Set<String> interests, Set<String> topics) {
        int counter = 0;

        for (String interest : interests) {
            if (topics.contains(interest)) {
                counter++;
            }
        }
        return counter;
    }
}
