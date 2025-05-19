package com.stefanosgersch.traineeship.domain.position_search;

import org.springframework.stereotype.Component;

@Component
public class PositionSearchFactory {

    private final SearchBasedOnInterest searchBasedOnInterest;
    private final SearchBasedOnLocation searchBasedOnLocation;

    public PositionSearchFactory(SearchBasedOnInterest searchBasedOnInterest,
                                 SearchBasedOnLocation searchBasedOnLocation) {
        this.searchBasedOnInterest = searchBasedOnInterest;
        this.searchBasedOnLocation = searchBasedOnLocation;
    }

    public PositionSearchStrategy create(String strategy) {
        switch (strategy) {
            case "interests":
                return searchBasedOnInterest;
            case "location":
                return searchBasedOnLocation;
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);
        }
    }
}
