package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
	private final int lottoCount;
	private final List<Lotto> lottos = new ArrayList<>();

	public Lottos(int lottoCount) {
		this.lottoCount = lottoCount;
		generateLottos();
	}

	private void generateLottos() {
		for (int i = 0; i < lottoCount; i++) {
			Lotto lotto = new Lotto();
			lottos.add(lotto);
		}
	}

	public List<Lotto> getLottos() {
		return lottos;
	}
}
