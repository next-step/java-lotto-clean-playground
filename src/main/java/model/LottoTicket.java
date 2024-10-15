package model;

import java.util.*;

public class LottoTicket {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_TICKET_PRICE = 1000;
    private static final int MATCHCOUNT_WITH_BONUSBALL_OF_2ND_RANK= 5;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<LottoNumber> numbers) {

        if (numbers.size() != LOTTO_SIZE){
            throw new IllegalArgumentException("로또 티켓은 " + LOTTO_SIZE + "개의 번호로 구성되어야 합니다.");
        }

        for (int i = 0; i < numbers.size(); i++) {
            LottoNumber currentNumber = numbers.get(i);

            if (numbers.indexOf(currentNumber) != numbers.lastIndexOf(currentNumber)) {
                throw new IllegalArgumentException("로또 번호에는 중복이 존재하지 않아야 합니다.");
            }
        }

    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public static int calculateTotalLottoTicketCount(int lottoPurchaseAmount) {
        return lottoPurchaseAmount / LOTTO_TICKET_PRICE;
    }

    public static int calculateAutomaticLottoTicketCount(int lottoTicketCount, int manualLottoTicketCount) {

        if (lottoTicketCount < manualLottoTicketCount){
            throw new IllegalArgumentException("가능한 수동 로또 티켓의 최대 개수를 초과하였습니다.");
        }

        return lottoTicketCount-manualLottoTicketCount;
    }

    public boolean contains(LottoNumber lottoNumber){
        return numbers.contains(lottoNumber);
    }

    public int countMatchingNumbers(LottoTicket winningTicket){
        return (int) numbers.stream()
                .filter(winningTicket::contains)
                .count();
    }

    public static LottoTicket transformStringToLottoTicket(String subjectOfConversion){

        List<LottoNumber> resultOfConversionToLottoTicket = Arrays.stream(subjectOfConversion.split(","))
                .map(number -> new LottoNumber(Integer.parseInt(number.trim())))
                .toList();

        return new LottoTicket(resultOfConversionToLottoTicket);
    }

    public static LottoTicket generateAutomaticNumbers() {

        List<Integer> lottoNumberPool = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            lottoNumberPool.add(i);
        }

        Collections.shuffle(lottoNumberPool);

        List<LottoNumber> selectedLottoNumbers = lottoNumberPool.subList(0, LOTTO_SIZE)
                .stream()
                .map(LottoNumber::new)
                .toList();

        return new LottoTicket(selectedLottoNumbers);

    }

    public LottoRank checkWinningTicketConditionAndReturnRank(LottoTicket winningNumbers, LottoNumber bonusNumber) {

        int matchCount = this.countMatchingNumbers(winningNumbers);
        boolean isMatchBonusBall = this.contains(bonusNumber);

        if(doesMatchWithBonusNumberExcept2ndRank(isMatchBonusBall,matchCount)) {
            matchCount++;
        }

        LottoRank rank = LottoRank.valueOf(matchCount,isMatchBonusBall);

        return rank;
    }

    private static boolean doesMatchWithBonusNumberExcept2ndRank(boolean isMatchBonusBall, int matchCount) {
        return isMatchBonusBall && matchCount != MATCHCOUNT_WITH_BONUSBALL_OF_2ND_RANK;
    }

}
