package model;

import java.util.List;

import java.util.TreeSet;

public class Lotto {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final TreeSet<Integer> numbers;

    private Lotto(TreeSet<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    public static Lotto create(List<Integer> numbers) {
        TreeSet<Integer> lottoNumbers = new TreeSet<>();
        for (Integer number : numbers) {
            addLottoNumbers(number, lottoNumbers);
        }
        return new Lotto(lottoNumbers);
    }

    private static void addLottoNumbers(Integer number, TreeSet<Integer> lottoNumbers) {
        if(!lottoNumbers.add(number)) {
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다!");
        }
    }

    public Ranking calculateRanking(Lotto winningNumbers) {
        int matchingCount = (int) numbers.stream()
                .filter(number -> winningNumbers.getNumbers().contains(number))
                .count();
        return Ranking.getRanking(matchingCount);
    }

    private static void validateLottoNumbers(TreeSet<Integer> lottoNumbers) {
        validateLottoSize(lottoNumbers);
        validateLottoBound(lottoNumbers);
    }

    private static void validateLottoBound(TreeSet<Integer> lottoNumbers) {
        boolean hasOutOfBoundNumber = lottoNumbers.stream().anyMatch(number -> number > LOTTO_MAX_NUMBER || number < LOTTO_MIN_NUMBER);
        if (hasOutOfBoundNumber) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
        }
    }

    private static void validateLottoSize(TreeSet<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_COUNT + "자리 입니다!");
        }
    }

    public int size() {
        return numbers.size();
    }

    public TreeSet<Integer> getNumbers() {
        return new TreeSet<>(numbers);
    }
}
