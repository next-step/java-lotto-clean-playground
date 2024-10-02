package service;

import model.LottoNumber;
import model.LottoTicket;

import java.util.*;

public class AutomaticLottoNumberGenerator {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public static LottoTicket generateAutomaticNumbers() {

        Set<LottoNumber> lottoNumbers = new HashSet<>();

        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            int number = (int) (Math.random() * MAX_NUMBER) + MIN_NUMBER;
            lottoNumbers.add(new LottoNumber(number));
        }

        List<LottoNumber> sortedLottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(sortedLottoNumbers);

        LottoTicket lottoTicket = new LottoTicket(sortedLottoNumbers);

        return lottoTicket;
    }

}
