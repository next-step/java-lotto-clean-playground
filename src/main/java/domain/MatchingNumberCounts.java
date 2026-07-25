package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchingNumberCounts {
    private final List<Integer> matchingNumberCounts;

    public MatchingNumberCounts(List<Integer> matchingNumberCounts) {
        this.matchingNumberCounts = new ArrayList<>(matchingNumberCounts);
    }

    public List<Integer> getMatchingNumberCounts() {
        return Collections.unmodifiableList(matchingNumberCounts);
    }
}
