package util;

import java.util.function.Supplier;

public class Counter {

    private int count;

    public void increaseIfTrue(Supplier<Boolean> conditionExpression) {
        if (conditionExpression.get()) {
            this.count++;
        }
    }

    public int getCount() {
        return this.count;
    }

}
