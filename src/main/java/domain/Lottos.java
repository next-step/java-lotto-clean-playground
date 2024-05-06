package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
	private final int lottoCount;
	private final List<Lotto> lottos;

	public Lottos(int lottoCount) {
		this.lottoCount = lottoCount;
		this.lottos = generateLottos();
	}

	private List<Lotto> generateLottos() {
		List<Lotto> generatedLottos = new ArrayList<>();
		for (int i = 0; i < lottoCount; i++) {
			generatedLottos.add(new Lotto());
		}
		return generatedLottos;
	}

	public List<Lotto> getLottos() {
		return List.copyOf(lottos);
	}

	public Map<Integer, Boolean> getMatchedLottoCounts(List<Integer> lottoWinningNumber, int bonusNumber) {
		Map<Integer, Boolean> matchedLottoCounts = new HashMap<>();
		for (int i = 0; i < lottoCount; i++) {
			Lotto lotto = lottos.get(i);
			int matchedCount = lotto.getMatchedNumberCount(lottoWinningNumber);
			if (matchedCount == 5 && lotto.getMatchedNumberCount(Arrays.asList(bonusNumber)) > 0) {
				matchedLottoCounts.put(matchedCount, true);
			} else if (matchedCount == 5 && lotto.getMatchedNumberCount(Arrays.asList(bonusNumber)) == 0) {
				matchedLottoCounts.put(matchedCount, false);
			} else if (matchedCount != 5) {
				matchedLottoCounts.put(matchedCount, false);
			}

		}
		return matchedLottoCounts;
	}

}
