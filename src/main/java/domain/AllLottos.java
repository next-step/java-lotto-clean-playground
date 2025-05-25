package domain;

public class AllLottos {
    private final Lottos manualLottos;
    private final Lottos autoLottos;
    private final Lottos merged;

    public AllLottos(Lottos manual, Lottos auto) {
        this.manualLottos = manual;
        this.autoLottos = auto;
        this.merged = Lottos.merge(manual, auto);
    }

    public Lottos getAllLottos() {
        return merged;
    }

    public int manualSize() { return manualLottos.size(); }
    public int autoSize() { return autoLottos.size(); }

    public int size() {
        return manualLottos.size() + autoLottos.size();
    }
}
