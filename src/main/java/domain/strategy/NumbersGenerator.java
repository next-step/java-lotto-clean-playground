package domain.strategy;

import java.util.List;

public interface NumbersGenerator<T> {
    List<T> generate();
}
