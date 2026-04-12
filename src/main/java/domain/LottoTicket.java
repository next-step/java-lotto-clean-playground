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

    public List<Integer> getTicketNumbers() {
        return Collections.unmodifiableList(this.lottoNumbers);
    }

    public int getTicketPrice(int grade){
        if(grade == 5) return 5000;
        else if (grade == 4) return 50000;
        else if (grade == 3) return 1500000;
        else if (grade == 2) return 30000000;
        else if (grade == 1) return 2000000000;
        else return 0;
    }

    public int getTicketGrade(List<Integer> winningNumbers, int bonusNumber){
        int matchedCount = getMatchedNumbers(winningNumbers);
        boolean hasBonusNumber = hasBonusNumber(bonusNumber);

        if(matchedCount == 3) return 5;
        else if (matchedCount == 4) return 4;
        else if (matchedCount == 5 && !hasBonusNumber) return 3;
        else if (matchedCount == 5) return 2;
        else if (matchedCount == 6) return 1;
        else return 6;
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

    public boolean hasBonusNumber(int bonusNumber){
        return  lottoNumbers.contains(bonusNumber);
    }
}
