package domain.lotto.dto;

public class MatchResult {
    
    private final Integer match;
    
    private final Integer amount;
    
    private final Integer count;

    public MatchResult(Integer match, Integer amount, Integer count) {
        this.match = match;
        this.amount = amount;
        this.count = count;
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
}
