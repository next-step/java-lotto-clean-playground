package domain;

public class LottoNumber {
    int lottoNumber;

    public int getLottoNumber() {
        return lottoNumber;
    }

    public LottoNumber(int lottoNumber){
        this.lottoNumber=lottoNumber;
    }

    public LottoNumber(String lottoNumber) {
        this.lottoNumber = validate(lottoNumber);
    }
    private int validate(String lottoNumber) {
        int IntLottoNumber = validateInteger(lottoNumber);
        validateRange(IntLottoNumber);
        return IntLottoNumber;
    }

    private void validateRange(int lottoNumber){
        if(lottoNumber<1||lottoNumber>45){
            throw new IllegalArgumentException("로또 숫자는 1에서 45사이의 숫자이어야 합니다.");
        }
    }


    public static int validateInteger(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("로또 숫자는 숫자형식이어야 합니다.");
        }
    }
}
