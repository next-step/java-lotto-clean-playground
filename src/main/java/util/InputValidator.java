package util;

import model.Lotto;
import model.LottoShop;

public class InputValidator {

    public static int validateLottoAmount(String lottoAmount) {
        int amount;
        try{
            amount = Integer.parseInt(lottoAmount);
            if(amount < 1000){
                throw new RuntimeException(LottoShop.PRICE_PER_TICKET + "이상 구매하여야 합니다.");
            }
            if(amount % 1000 != 0){
                throw new RuntimeException(LottoShop.PRICE_PER_TICKET + "단위로 구매하여야 합니다.");
            }
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("잘못된 값을 입력하였습니다.");
        }
        return amount;
    }

    public static int validateManualLottoAmount(int randomLottoAmount, String manualLottoAmount) {
        int amount;
        try{
            amount = Integer.parseInt(manualLottoAmount);
            if(amount > randomLottoAmount){
                throw new RuntimeException("총 구매할 로또 수 이하로 구매하여야 합니다.");
            }
            if(amount < 0){
                throw new RuntimeException("0개 이상 구매하여야 합니다.");
            }
        }
        catch (NumberFormatException e){
            throw new RuntimeException("잘못된 값을 입력하였습니다.");
        }
        return amount;
    }

    public static int validateBonusBallNumber(String bonusBallNumber) {
        int bonusBall;
        try{
            bonusBall = Integer.parseInt(bonusBallNumber);
            if(bonusBall < Lotto.MIN_LOTTO_NUMBER || bonusBall > Lotto.MAX_LOTTO_NUMBER){
                throw new RuntimeException("로또 번호는 1이상 45이하여야 합니다.");
            }
        }
        catch (NumberFormatException e){
            throw new RuntimeException("잘못된 값을 입력하였습니다.");
        }
        return bonusBall;
    }
}
