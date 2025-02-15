package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;
    public LottoNumber(int number) throws IllegalArgumentException {
        this.number = number;
        this.validateNumber(this.number);
    }
    public int getNumber() {
        return number;
    }

    private void validateNumber(int number){
        if(number < 1 || number > 45){
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(other == null || getClass() != other.getClass()){
            return false;
        }
        return number == ((LottoNumber) other).number;
    }

    @Override
    public int hashCode(){
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber other){
        return this.number - other.number;
    }

    @Override
    public String toString(){
        return String.valueOf(number);
    }
}
