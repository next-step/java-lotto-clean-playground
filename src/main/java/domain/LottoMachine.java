package domain;

public class LottoMachine {

    private NumberGenerator numberGenerator;
    private LottoPaper workingPaper;
    private int workingPaperRowNum;

    private static final int PRICE_UNIT = 1000;

    public LottoMachine(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    public void generatePaper(int price){
        calculateRowNum(price);
        workingPaper = new LottoPaper(workingPaperRowNum);
        generateRows();
    }

    public void calculateRowNum(int price){
        checkPriceUnit(price);
        workingPaperRowNum = price / PRICE_UNIT;
    }

    private void checkPriceUnit(int price){
        if(price%PRICE_UNIT != 0){
            throw new IllegalArgumentException("금액은 1000원 단위입니다.");
        }
    }

    public void generateRows(){
        if(workingPaperRowNum <= 0){
            throw new RuntimeException("RowNum must be greater than 0.");
        }
        for(int i = 0; i < workingPaperRowNum; i++){
            generateRow();
        }
    }

    private void generateRow(){
        if(workingPaper == null){
            throw new RuntimeException("Generate Lotto Paper first");
        }
        workingPaper.writeRow(new Row(numberGenerator.getNumbers()));
    }

    public int getWorkingPaperRowNum() {
        return workingPaperRowNum;
    }

    public LottoPaper getWorkingPaper(){
        return workingPaper;
    }

}
