package domain;

public class LottoMachine {

    private NumberGenerator numberGenerator;
    private LottoPaper workingPaper;
    private int workingPaperRowNum;

    public LottoMachine(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    public void calculateRowNum(int price){
        checkPriceUnit(price);
        workingPaperRowNum = price / 1000;
    }

    private void checkMinPrice(int price){
        if(price < 1000){
            throw new IllegalArgumentException("최소 금액은 1000원입니다.");
        }
    }

    private void checkPriceUnit(int price){
        checkMinPrice(price);
        if(price%1000 != 0){
            throw new IllegalArgumentException("금액은 1000원 단위입니다.");
        }
    }

    public void generatePaper(){
        workingPaper = new LottoPaper(workingPaperRowNum);
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
        workingPaper.writeRow(numberGenerator.getNumbers());
    }

    public int getWorkingPaperRowNum() {
        return workingPaperRowNum;
    }

    public LottoPaper getWorkingPaper(){
        return workingPaper;
    }

}
