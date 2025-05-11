package utils;

import dto.MatchResultDto;
import dto.PrintableMatchDto;
import dto.PrintableProfitDto;
import dto.ProfitDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ResultFormatterTest {

    private final ResultFormatter formatter = new ResultFormatter();

    @Test
    @DisplayName("매칭 결과를 포맷된 문자열로 반환한다")
    void formatSingleMatchResult() {
        MatchResultDto match = new MatchResultDto(3, 5000, 2);

        PrintableMatchDto result = formatter.formatMatchResult(match);

        assertThat(result.messageLine()).isEqualTo("3개 일치 (5000원)- 2개");
    }

    @Test
    @DisplayName("여러 매칭 결과를 포맷된 문자열 리스트로 반환한다")
    void formatMultipleMatchResults() {
        List<MatchResultDto> matches = List.of(
                new MatchResultDto(3, 5000, 1),
                new MatchResultDto(4, 50000, 2)
        );

        List<PrintableMatchDto> result = formatter.formatMatchResults(matches);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.get(0).messageLine()).isEqualTo("3개 일치 (5000원)- 1개");
        softly.assertThat(result.get(1).messageLine()).isEqualTo("4개 일치 (50000원)- 2개");
        softly.assertAll();
    }

    @ParameterizedTest(name = "수익률 {0}, 손해 여부 {1} → \"{2}\"")
    @CsvSource({
            "1.25,false,총 수익률은 1.25입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)",
            "0.75,true,총 수익률은 0.75입니다.(기준이 1이기 때문에 결과적으로 손해이라는 의미임)"
    })
    @DisplayName("수익률 포맷 결과가 올바르게 출력된다")
    void formatProfitResult(double rate, boolean isLoss, String expectedMessage) {
        ProfitDto profit = new ProfitDto(rate, isLoss);

        PrintableProfitDto result = formatter.formatProfitResult(profit);

        assertThat(result.messageLine()).isEqualTo(expectedMessage);
    }
}
