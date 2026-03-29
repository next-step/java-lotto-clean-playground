package model;

import java.util.List;

public record Lotto(List<Integer> numbers) {
//    public int compare(List<Integer> otherNumbers) {
//        return otherNumbers.stream().filter(numbers::contains).toList().size();
//    }
}
