package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<Integer> parseNumbers(String input){
        //parseInt에서 발생하는 NumberFormatException을 IllegalArgumentException으로 감싸겠습니다.
        try{
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다. 입력된 값 : " + input);
        }
    }
}
