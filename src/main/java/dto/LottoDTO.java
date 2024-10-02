package dto;

public class LottoDTO {
    public Integer correct3, correct4, correct5, correct5WithBonus, correct6;
    public Double income;

    public LottoDTO(Integer correct3, Integer correct4, Integer correct5, Integer correct5WithBonus, Integer correct6, Double income) {
        this.correct3 = correct3;
        this.correct4 = correct4;
        this.correct5 = correct5;
        this.correct5WithBonus = correct5WithBonus;
        this.correct6 = correct6;
        this.income = income;
    }
}
