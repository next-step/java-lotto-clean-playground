package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMachineTest {

    @DisplayName("로또머신 자동행 생성 테스트 : 로또 행 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 999})
    public void generateRowsTest(int value) {
        LottoPaper lottoPaper = new LottoPaper();
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        lottoMachine.generateRows(lottoPaper, value);
        assertThat(lottoPaper.getRowNum()).isEqualTo(value);
    }


    private static Stream<Arguments> methodSourceOfManualRow() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.arguments(Arrays.asList(11, 23, 43, 12, 32, 22))
        );
    }

    @DisplayName("로또머신 수동 생성 테스트 : 6개 수에 정상 수행")
    @ParameterizedTest
    @MethodSource("methodSourceOfManualRow")
    public void creationTest(List<Integer> numbers) {
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        LottoPaper lottoPaper = new LottoPaper();
        List<Row> manualRows = new ArrayList<>();
        manualRows.add(rowFromList(numbers));
        lottoMachine.addManualRows(lottoPaper, manualRows);
        assertThat(lottoPaper.getRows()).isEqualTo(manualRows);
    }

    private Row rowFromList(List<Integer> numbers) {
        return new Row(numbers.stream().map(num -> new LottoNumber(num)).collect(Collectors.toList()));
    }
}
