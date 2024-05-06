package model;

public class BonusNumber {

    private int bonusNumber;

    public BonusNumber(String bonusNumber){
        this.bonusNumber = convertStringToInt(bonusNumber)
        ;
    }

    public int convertStringToInt(String bonusNumber){
        try {
            return Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] 숫자로만 입력해주세요.");
            throw e;
        }
    }
    public int getBonusNumber(){
        return bonusNumber;
    }
}
