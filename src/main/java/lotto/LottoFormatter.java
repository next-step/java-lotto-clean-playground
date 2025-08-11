package lotto;

import java.util.stream.Collectors;

public class LottoFormatter {
    public static String format(LottoTicket ticket) {
        String joined = joinNumbers(ticket);
        return surroundWithBrackets(joined);
    }

    private static String joinNumbers(LottoTicket ticket) {
        return ticket.getNumbers().stream()
                .map(n -> String.valueOf(n.getValue()))
                .collect(Collectors.joining(", "));
    }

    private static String surroundWithBrackets(String content) {
        return "[" + content + "]";
    }
}


