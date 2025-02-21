package util;

import model.LottoNumber;
import model.LottoNumbers;

import java.util.*;
import java.util.stream.Collectors;

public class InputConverter {

    private static final String INPUT_SEPARATOR = "[\\s|,]";

    public List<LottoNumbers> getLottoNumbersListFromInputs(List<String> lottoNumbersInputs) {
        List<LottoNumbers> lottoNumbersList = new ArrayList<>();

        for (String lottoNumbersInput : lottoNumbersInputs) {
            String[] lottoNumberStrings = splitLottoNumbersInput(lottoNumbersInput);
            Set<Integer> parsedIntegers = parseStringsToIntegers(lottoNumberStrings);
            lottoNumbersList.add(createLottoNumbersFromIntegers(parsedIntegers));
        }

        return Collections.unmodifiableList(lottoNumbersList);
    }

    public LottoNumbers getLottoNumbersFromInput(String lottoNumbersInput) {
        String[] lottoNumberStrings = splitLottoNumbersInput(lottoNumbersInput);
        Set<Integer> parsedIntegers = parseStringsToIntegers(lottoNumberStrings);

        return createLottoNumbersFromIntegers(parsedIntegers);
    }

    private String[] splitLottoNumbersInput(String winningLottoInput) {
        return winningLottoInput.split(INPUT_SEPARATOR);
    }

    private Set<Integer> parseStringsToIntegers(String[] lottoNumberStrings) {
        Set<Integer> parsedIntegers = new HashSet<>();

        for (String lottoNumberString : lottoNumberStrings) {
            int parsedNumber = Integer.parseInt(lottoNumberString);
            parsedIntegers.add(parsedNumber);
        }

        return Collections.unmodifiableSet(parsedIntegers);
    }

    private LottoNumbers createLottoNumbersFromIntegers(Set<Integer> lottoNumberIntegers) {
        Set<LottoNumber> lottoNumbers = convertIntegersToLottoNumbers(lottoNumberIntegers);

        return new LottoNumbers(lottoNumbers);
    }

    private Set<LottoNumber> convertIntegersToLottoNumbers(Set<Integer> lottoNumberIntegers) {
        return lottoNumberIntegers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());
    }

}
