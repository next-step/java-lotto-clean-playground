package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

	private final int manualLottoCount;
	private final int autoLottoCount;

	private final List<Lotto> lottos;

	public Lottos(int manualLottoCount, int totalLottoCount, List<List<Integer>> manualLottos) {
		validate(manualLottoCount, totalLottoCount);
		this.manualLottoCount = manualLottoCount;
		this.autoLottoCount = totalLottoCount - manualLottoCount;
		lottos = new ArrayList<>();
		publishManualLottos(manualLottos);
		publishAutoLottos(totalLottoCount - manualLottoCount);
	}

	private void validate(int manualLottoCount, int totalLottoCount) {
		if (manualLottoCount > totalLottoCount) {
			throw new IllegalArgumentException("로또는 구입 금액만큼 발행됩니다.");
		}
	}

	private void publishManualLottos(List<List<Integer>> manualLottos) {
		for (List<Integer> manualLotto : manualLottos) {
			lottos.add(new Lotto(manualLotto));
		}
	}

	private void publishAutoLottos(int autoLottoCount) {
		for (int i = 0; i < autoLottoCount; i++) {
			lottos.add(new Lotto());
		}
	}

	public List<Lotto> getLottos() {
		return List.copyOf(lottos);
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	public int getAutoLottoCount() {
		return autoLottoCount;
	}
}
