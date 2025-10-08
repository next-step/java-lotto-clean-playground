package Model;

import java.util.Collections;
import java.util.List;

public class Jackpot {
    private List<Integer> jackpot;
    private int bounsNumber;

    public Jackpot(String[] inputJackpot, int bounsNumber) {
        JackpotGenerator from = JackpotGenerator.from(inputJackpot);
        this.jackpot = from.getNumber();
        validateBonusNumber(bounsNumber, this.jackpot);
        this.bounsNumber = bounsNumber;
    }

    private void validateBonusNumber(int bounsNumber, List<Integer> jackpot) {
        if (bounsNumber < 1 || bounsNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1과 45 사이의 숫자여야 합니다.");
        }
        if (jackpot.contains(bounsNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getJackpot() {
        return Collections.unmodifiableList(jackpot);
    }

    public int getBounsNumber() {
        return bounsNumber;
    }

    @Override
    public String toString() {
        return jackpot.toString();
    }
}
