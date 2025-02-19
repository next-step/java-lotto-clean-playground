package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    // 로또 번호 관련 상수 선언
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_CREATE_SIZE = 6;
    public static final int LOTTO_PRICE = 1000;
    public static final List<Integer> LOTTO_NUMBER_POOL =
            IntStream
                    .rangeClosed(LOTTO_MIN_NUMBER,LOTTO_MAX_NUMBER)
                    .boxed()
                    .collect(Collectors.toList());

    private List<Integer> numbers = new ArrayList<>();

    public static int getTicketCount(int purchaseAmount){
        return purchaseAmount / LOTTO_PRICE;
    }

    public Lotto(){
        this.numbers = createLottoNumbers();
    }

    private List<Integer> createLottoNumbers(){
        List<Integer> shuffledNumbers = new ArrayList<>(LOTTO_NUMBER_POOL);
        Collections.shuffle(shuffledNumbers);
        numbers = shuffledNumbers.subList(0, LOTTO_CREATE_SIZE);

        return numbers;
    }

    public static List<Lotto> generateLottoTickets(int ticketCount){
        List<Lotto> tickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++){
            tickets.add(new Lotto());
        }

        return tickets;
    }

    public List<Integer> getNumbers() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);

        return sortedNumbers;
    }
}
