package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    List<Integer> lottoNumbers;

    public LottoTicket (List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    public String toDisplayString() {
        return lottoNumbers.toString();
    }

    public int getMatchedNumbers(List<Integer> winningNumbers) {
        int count = 0;
        for(Integer winningNumber: winningNumbers) {
            if(lottoNumbers.contains(winningNumber)) {
                count++;
            }
        }
        return count;
    }
}
