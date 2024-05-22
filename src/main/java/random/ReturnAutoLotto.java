package random;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class ReturnAutoLotto { //랜덤로또리스트를 반환
    public static List<Integer> randomLotto(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        while (list.size() < 6) {
            int randomNumber = random.nextInt(45) + 1;

            if (list.contains(randomNumber)) {
                continue;
            }
            list.add(randomNumber);
        }
        Collections.sort(list);
        return list;
    }
}
