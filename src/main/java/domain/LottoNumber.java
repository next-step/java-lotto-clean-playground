package domain;

public class LottoNumber implements Comparable<LottoNumber> { // 각각의 로또 번호 포장

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int lottoNumber;

    public LottoNumber(int lottoNumber){
        verifyLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void verifyLottoNumber(int lottoNumber){
            if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException("로또 번호는 1~45 사이입니다.");
            }
        }


    @Override // 이 부분은 AI의 도움을 받았습니다
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.lottoNumber, other.lottoNumber);
    }


    @Override // 이 부분은 AI의 도움을 받았습니다
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof LottoNumber other)) {
            return false;
        }

        return lottoNumber == other.lottoNumber;
    }


    @Override // 이 부분은 AI의 도움을 받았습니다
    public int hashCode() {
        return Integer.hashCode(lottoNumber);
    }


    @Override // 이 부분은 AI의 도움을 받았습니다
    public String toString() {
        return String.valueOf(lottoNumber);
    }
}
