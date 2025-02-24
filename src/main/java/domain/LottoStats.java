package domain;

public class LottoStats {
    private final Money income;
    private int countFifth = 0;
    private int countFourth = 0;
    private int countThird = 0;
    private int countSecond = 0;
    private int countFirst = 0;

    public LottoStats(){
       this.income = new Money(0);
    }

    public void addIncome(Long amount){
        this.income.addMoney(amount);
    }
    public Money getIncome(){
        return this.income;
    }

    public int getCountFifth() {
        return countFifth;
    }
    public int getCountFourth() {
        return countFourth;
    }
    public int getCountThird() {
        return countThird;
    }
    public int getCountSecond() {
        return countSecond;
    }
    public int getCountFirst() {
        return countFirst;
    }

    public void addCountFifth(){
        this.countFifth++;
    }
    public void addCountFourth(){
        this.countFourth++;
    }
    public void addCountThird(){
        this.countThird++;
    }
    public void addCountSecond(){
        this.countSecond++;
    }
    public void addCountFirst(){
        this.countFirst++;
    }
}
