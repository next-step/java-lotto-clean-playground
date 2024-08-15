package domain;

import java.util.List;

public class LottoMachine {

    private NumberGenerator numberGenerator;

    private static final int PRICE_UNIT = 1000;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public LottoPaper generatePaper(LottoPrice price, List<Row> manualRows) {
        LottoPaper lottoPaper = new LottoPaper();
        addManualRows(lottoPaper, manualRows);
        int rowNum = price.price() / PRICE_UNIT - manualRows.size();
        generateRows(lottoPaper, rowNum);
        return lottoPaper;
    }

    public void addManualRows(LottoPaper lottoPaper, List<Row> manualRows) {
        for(Row manualRow : manualRows) {
            lottoPaper.writeRow(manualRow);
        }
    }

    public void generateRows(LottoPaper lottoPaper, int rowNum) {
        for (int i = 0; i < rowNum; i++) {
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