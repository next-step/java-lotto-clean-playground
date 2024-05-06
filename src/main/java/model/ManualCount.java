package model;

public class ManualCount {
    private int manualCount;

    public ManualCount(String manualCount){
        this.manualCount = convertStringToInt(manualCount)
        ;
    }

    public int convertStringToInt(String manualCount){
        try {
            return Integer.parseInt(manualCount);
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] 숫자로만 입력해주세요.");
            throw e;
        }
    }
    public int getManualCount(){
        return manualCount;
    }
}
