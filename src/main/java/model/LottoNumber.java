package model;

public class LottoNumber implements Comparable<LottoNumber>{
    private final Integer number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45){
            throw new IllegalArgumentException("로또 번호 범위에서 벗어났습니다. (로또 번호 범위: 1~45)");
        }
    }

    public Integer getNumber() {
        return number;
    }


    @Override
    public int compareTo(LottoNumber other) {
        return this.number.compareTo(other.getNumber());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LottoNumber)) return false;
        LottoNumber other = (LottoNumber) obj;
        return this.number.equals(other.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString(){
        return number.toString();
    }
}
