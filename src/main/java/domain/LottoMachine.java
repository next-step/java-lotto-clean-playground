package domain;

public class LottoMachine {

    private NumberGenerator numberGenerator;

    private static final int PRICE_UNIT = 1000;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public LottoPaper generatePaper(LottoPrice price) {
        LottoPaper lottoPaper = new LottoPaper(calculateRowNum(price));
        generateRows(lottoPaper);
        return lottoPaper;
    }

    public int calculateRowNum(LottoPrice price) {
        return price.price() / PRICE_UNIT;
    }

    public void generateRows(LottoPaper lottoPaper) {
        for (int i = 0; i < lottoPaper.getRowNum(); i++) {
            generateRow(lottoPaper);
        }
    }

    private void generateRow(LottoPaper lottoPaper) {
        if (lottoPaper == null) {
            throw new RuntimeException("Generate Lotto Paper first");
        }
        lottoPaper.writeRow(new Row(numberGenerator.getNumbers()));
    }

}
