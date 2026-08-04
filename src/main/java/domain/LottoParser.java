package domain;

import java.util.*;

public class LottoParser {

    public List<Integer> parseInput(String input) {
        List<Integer> lottoNumbers = new ArrayList<>();
        String[] items = input.split(",");
        for (String item : items) {
            lottoNumbers.add(Integer.parseInt(item.trim()));
        }
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
