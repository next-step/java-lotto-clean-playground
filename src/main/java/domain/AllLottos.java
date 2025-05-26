package domain;

public record AllLottos(Lottos manualLottos, Lottos autoLottos, Lottos merged) {

    public AllLottos(Lottos manual, Lottos auto) {
        this(manual, auto, Lottos.merge(manual, auto));
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
