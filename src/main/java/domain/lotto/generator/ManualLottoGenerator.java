package domain.lotto.generator;

import domain.lotto.IssuanceType;
import domain.lotto.LottoNumber;
import domain.lotto.LottoTicket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    @Override
    public boolean supports(IssuanceType issuanceType) {
        return issuanceType == IssuanceType.MANUAL;
    }

    @Override
    public LottoTicket issue() {
        final Scanner scanner = new Scanner(System.in);
        List<LottoNumber> list = Arrays.stream(scanner.nextLine().split(", "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        return new LottoTicket(list);
    }
}
