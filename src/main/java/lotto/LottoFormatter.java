package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoFormatter {
    public static String format(List<Integer> ticket) {
        String joined = joinNumbers(ticket);
        return surroundWithBrackets(joined);
    }

    private static String joinNumbers(List<Integer> ticket) {
        return ticket.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private static String surroundWithBrackets(String content) {
        return "[" + content + "]";
    }
}


