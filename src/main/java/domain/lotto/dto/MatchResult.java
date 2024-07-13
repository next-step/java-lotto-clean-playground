package domain.lotto.dto;

public class MatchResult {
    
    private final Integer match;
    
    private final Integer amount;
    
    private final Integer count;

    private final boolean hasBonusNumber;

    public MatchResult(Integer match, Integer amount, Integer count, boolean hasBonusNumber) {
        this.match = match;
        this.amount = amount;
        this.count = count;
        this.hasBonusNumber = hasBonusNumber;
    }

    public Integer getMatch() {
        return match;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getCount() {
        return count;
    }

    public boolean hasHasBonusNumber() {
        return hasBonusNumber;
    }
}
