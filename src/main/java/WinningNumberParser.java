import java.util.ArrayList;
import java.util.List;

public class WinningNumberParser {
    public static List<LottoNumber> parse(String[] inputs) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (String s : inputs) {
            numbers.add(new LottoNumber(Integer.parseInt(s.trim())));
        }
        return numbers;
    }
}
