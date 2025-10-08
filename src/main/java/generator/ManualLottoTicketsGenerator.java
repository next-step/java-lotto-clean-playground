package generator;

import domain.Lotto;
import utils.ParseInputStringToNumbers;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoTicketsGenerator {
    public static List<Lotto> createManualTickets(List<String> lottoNumberLines) {
        List<Lotto> manualTickets = new ArrayList<>();
        for (String lottoNumberLine : lottoNumberLines) {
            List<Integer> manualLottoNumbers = ParseInputStringToNumbers.parse(lottoNumberLine);
            manualTickets.add(new Lotto(manualLottoNumbers));
        }
        return manualTickets;
    }
}
