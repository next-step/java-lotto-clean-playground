package model;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int LOTTO_TICKET_PRICE = 1000;
    private final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE){
            throw new IllegalArgumentException("로또 티켓은 6개의 번호로 구성되어야 합니다.");
        }

        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호에는 중복이 존재하지 않아야 합니다.");
        }

    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public boolean contains(LottoNumber lottoNumber){
        return numbers.contains(lottoNumber);
    }

    public int countMatchingNumbers(LottoTicket winningTicket){
        return (int) numbers.stream()
                .filter(winningTicket::contains)
                .count();
    }

    public static LottoTicket transformStringToLottoTicket(String st){

        List<LottoNumber> result = Arrays.stream(st.split(","))
                .map(number -> new LottoNumber(Integer.parseInt(number.trim())))
                .toList();

        return new LottoTicket(result);
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", "));
    }

}
