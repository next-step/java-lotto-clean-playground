package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//자바는 integer의 비교 방법은 이미 알고 있지만, 우리는 LottoNumber을 객체로 만들어서
//관리하고 있기 때문에, 비교 방법에 대해서 알려줘야 한다. > comparable<LottoNumber>
public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final int number;

    private static final Map<Integer, LottoNumber> CACHE = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), LottoNumber::new));
    //lottonumber 객체를 미리 생성하여 저장해둚

    private LottoNumber(int number) { // 생성자를 private으로 막아서 외부에서 new를 못하게 하고, 반드시 valueOf를 통하게 합
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validate(number);
        return CACHE.get(number);
    }

    private static void validate(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    // Lotto 등 다른 곳에서 전체 번호 리스트가 필요할 때 사용하기 위한 메서드
    public static List<LottoNumber> values() {
        return Collections.unmodifiableList(new java.util.ArrayList<>(CACHE.values()));
    }

    //모든 클래스는 자동으로 boolean equals, haseCode, toString을 상속한다.
    //그러므로 LottoNumber도 이미 가지고 있음
    //우리가 다시 override하는 이유는, 기본 equals는 주소 비교여서다. 우리가 원하는 것은
    //값이 같으면 같은 객체로 두게끔이다. 그래서 override해서 수정한다.
    //equals가 같으면 hashCode도 같아야 하므로 같이 수정.

    @Override
    public boolean equals(Object o) {//주소가 같아야 동일한게 아니라, 숫자가 같으면 동일함을 알려주기 위한 메서드
        if (this == o) return true;
        //getClass는 해당 객체의 타입을 알려주는 메서드
        if (o == null || getClass() != o.getClass()) return false;
        //if문 다 통과했으니 o는 null이 아니고 o의 클래스가 LottoNumber과 같다고 볼 수 있음
        LottoNumber that = (LottoNumber) o; //형변환. o는 object지만 LottoNumber임에 틀림없으니 형변환을 해준다
        //that에 저장
        return number == that.number; //boolean이므로 같으면 true를 다르면 false를 반환함
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    //compareTo : 정렬 순서가 오름차순임을 알려줌. Integer.compare 하기 때문
    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    //toString으로 바꿔주지 않는다면 LottoNumber 객체를 출력했을 때 주소값이 출력됨
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
