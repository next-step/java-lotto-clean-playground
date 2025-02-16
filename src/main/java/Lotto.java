import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Lotto {

    private final Set<Integer> lottoNumbers = new HashSet<>();
    private List<Integer> sortedLotto = new ArrayList<>();
    private final List<List<Integer>> lottoNum = new ArrayList<>();

    private Integer[] rank = new Integer[8]; // 당첨 개수 저장
    private final Integer[] winningPrize = {0, 0, 0, 5000, 50000, 1500000, 30000000, 2000000000};
    private int bonusBall = 0;

    public Lotto() {
        setLottoNumbers();
        SetToList();
        Arrays.fill(rank, 0); // rank 배열을 0으로 초기화
    }

    public void setLottoNumbers() {
        Random random = new Random();
        while (lottoNumbers.size() < 6) {
            int number = random.nextInt(45) + 1;
            lottoNumbers.add(number);
        }
    }

    public List<Integer> SetToList() {
        sortedLotto = new ArrayList<>(lottoNumbers);
        Collections.sort(sortedLotto);
        return sortedLotto;
    }

    public void printLotto(int inputPrice) {
        int price = 1000;
        int loop = inputPrice / price;
        for (int i = 0; i < loop; i++) {
            Lotto generateLotto = new Lotto();
            System.out.println(generateLotto.sortedLotto);
            lottoNum.add(generateLotto.sortedLotto);
        }
    }

    public List<Integer> StringToInt(String[] StringlastNum) {
        List<Integer> WinningNum = new ArrayList<>();
        for (String value : StringlastNum) {
            int lastNumber = Integer.parseInt(value.trim()); // 공백 제거 후 변환
            WinningNum.add(lastNumber);
        }
        return WinningNum;
    }

    public int PureStringToInt(StringBuilder str) {
        return Integer.parseInt(str.toString().trim()); // 공백 제거 후 변환
    }

    public void checkingWinningnumbers(List<Integer> lastWinningNum) {
        for (List<Integer> singleLotto : lottoNum) {
            int matchCount = countMatchingnumbers(singleLotto, lastWinningNum);
            boolean hasBonus = isBonusBall(singleLotto);

            if (matchCount == 5 && hasBonus) {
                rank[6]++; // 5개 + 보너스 볼 = 2등
            } else if (matchCount <= 6) {
                rank[matchCount]++; // 일반 당첨 처리
            }
        }

        for (int i = 3; i < 8; i++) {
            if (i == 6) {
                System.out.println("5개 일치, 보너스 볼 일치 (" + winningPrize[i] + "원)- " + rank[i] + "개");
            } else {
                System.out.println(i + "개 일치 (" + winningPrize[i] + "원)- " + rank[i] + "개");
            }
        }
    }

    public int countMatchingnumbers(List<Integer> singleLotto, List<Integer> lastWinningNum) {
        int count = 0;
        for (int value : lastWinningNum) {
            if (value != bonusBall) { // 보너스 볼을 제외하고 비교
                count += isMatching(value, singleLotto);
            }
        }
        return count;
    }

    public int isMatching(int value, List<Integer> singleLotto) {
        if (singleLotto.contains(value)) {
            return 1;
        }
        return 0;
    }

    public double getProfit(int money) {
        if (money == 0) return 0; // 0으로 나누기 방지

        double profit = 0;
        double prize = 0;
        for (int i = 3; i < 8; i++) {
            if (rank[i] > 0) {
                prize += winningPrize[i] * rank[i];
            }
        }
        profit = prize / money;
        return profit;
    }

    public void getbonusBall(StringBuilder bonus) {
        bonusBall = PureStringToInt(bonus);
    }

    public boolean isBonusBall(List<Integer> singleLotto) {
        return singleLotto.contains(bonusBall); // 보너스 볼이 포함되었는지만 체크
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Lotto lotto = new Lotto();
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해주세요:");

        Integer money = scanner.nextInt();
        lotto.printLotto(money);

        scanner.nextLine();

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        StringBuilder inputWinningNum = new StringBuilder(scanner.nextLine());
        String[] WinningNum = inputWinningNum.toString().split(",");
        System.out.println("보너스 볼을 입력해 주세요.");
        StringBuilder bonusBall = new StringBuilder(scanner.nextLine());
        lotto.getbonusBall(bonusBall);
        List<Integer> intWinningNum = lotto.StringToInt(WinningNum);
        lotto.checkingWinningnumbers(intWinningNum);
        System.out.println("총 수익률은 " + lotto.getProfit(money) + "입니다.");
    }
}
