package domain;

import java.util.ArrayList;
import java.util.List;

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
			Lotto lotto = new Lotto();
			generatedLottos.add(lotto);
		}
		return generatedLottos;
	}

	public List<Lotto> getLottos() {
		return lottos;
	}

	public List<Integer> getMatchedLottoCounts(List<Integer> lottoWinningNumber) {
		List<Integer> matchedLottoCounts = new ArrayList<>();
		for (int i = 0; i < lottoCount; i++) {
			Lotto lotto = lottos.get(i);
			matchedLottoCounts.add(lotto.getMatchedNumberCount(lottoWinningNumber));
		}
		return matchedLottoCounts;
	}

}
