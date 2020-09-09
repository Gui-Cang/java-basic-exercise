import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        boolean isChar = (firstWordList.matches("[a-zA-Z]+")) && (firstWordList.matches("[a-zA-Z]+"));
        boolean doubleComma = (firstWordList.contains(",,")) && (firstWordList.contains(",,"));
        if (!isChar || doubleComma) {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                System.out.println("input not valid");
            }
        }
        firstWordList = firstWordList.toLowerCase();
        List<String> firstList = Arrays.asList(firstWordList.split(","));
        List<String> firstCollect = firstList.stream().distinct().sorted().collect(Collectors.toList());
        secondWordList = secondWordList.toLowerCase();
        List<String> secondList = Arrays.asList(secondWordList.split(","));
        List<String> secondCollect = secondList.stream().distinct().sorted().collect(Collectors.toList());
        List<String> list = new ArrayList<>();
        list.addAll(firstCollect);
        list.addAll(secondCollect);

        List<String> duplicate = list.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        return duplicate;
    }
}
