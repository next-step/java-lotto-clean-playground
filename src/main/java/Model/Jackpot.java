package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jackpot {
    private List<Integer> jackpot;

    public Jackpot(String[] inputJackpot) {
        this.jackpot = new ArrayList<>();  // 리스트 초기화

        for (int i = 0; i < 6; i++) {
            jackpot.add(Integer.parseInt(inputJackpot[i]));
        }
        Collections.sort(this.jackpot);
    }

    public List<Integer> getJackpot() {
        return Collections.unmodifiableList(jackpot);
    }

    @Override
    public String toString() {
        return jackpot.toString();
    }
}
