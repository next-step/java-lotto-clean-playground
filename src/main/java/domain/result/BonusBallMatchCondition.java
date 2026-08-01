package domain.result;

public enum BonusBallMatchCondition {
    ANY {
        @Override
        public boolean matches(LottoResult lottoResult) {
            return true;
        }
    },
    MATCHED {
        @Override
        public boolean matches(LottoResult lottoResult) {
            return lottoResult.hasMatchedBonusBall();
        }
    },
    UNMATCHED {
        @Override
        public boolean matches(LottoResult lottoResult) {
            return lottoResult.hasUnmatchedBonusBall();
        }
    };

    public abstract boolean matches(LottoResult lottoResult);
}
