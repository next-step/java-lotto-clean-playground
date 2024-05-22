package random;

import java.util.ArrayList;
import java.util.List;

public class ConvertToString { //출력을 위해..?

    public static String convertToString(List<Integer> integerList) {
        StringBuilder sb = new StringBuilder();

        for (Integer num : integerList) {
            sb.append(num).append(", ");
        }

        // 마지막 쉼표와 공백 제거
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }
}
