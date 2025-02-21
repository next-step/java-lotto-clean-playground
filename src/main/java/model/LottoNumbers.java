package model;

import java.util.*;

public class LottoNumbers {

    private static final Comparator<LottoNumber> COMPARATOR = Comparator.comparingInt(LottoNumber::getValue);
    private static final int LOTTO_SIZE = 6;

    private final SortedSet<LottoNumber> lottoNumbers = new TreeSet<>(COMPARATOR);

    private LottoNumbers(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers.size());
        this.lottoNumbers.addAll(lottoNumbers);
    }

    public static LottoNumbers getRandomLottoNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();

        while (isSmallThanMaxSize(lottoNumbers.size())) {
            LottoNumber randomLottoNumber = LottoNumber.getRandomLottoNumber();
            lottoNumbers.add(randomLottoNumber);
        }

        return new LottoNumbers(lottoNumbers);
    }

    public SortedSet<LottoNumber> getCopy() {
        return Collections.unmodifiableSortedSet(lottoNumbers);
    }

    private void validateSize(int size) {
        if (isIllegalSize(size)) {
            throw new IllegalArgumentException(String.format("로또 번호 갯수는 %d개여야 합니다: %d", LOTTO_SIZE, size));
        }
    }

    private boolean isIllegalSize(int size) {
        return size != LOTTO_SIZE;
    }

    private static boolean isSmallThanMaxSize(int size) {
        return size < LOTTO_SIZE;
    }

}
