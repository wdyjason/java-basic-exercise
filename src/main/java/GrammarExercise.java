import java.util.*;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";
        Scanner in = new Scanner(System.in);
        firstWordList = in.nextLine();
        secondWordList = in.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> firstList = preDeal(firstWordList);
        List<String> secondList = preDeal(secondWordList);
        List<String> retList = new ArrayList<>();
        for (String s : firstList) {
            if (secondList.contains(s)) retList.add(s);
        }
        Collections.sort(retList, String.CASE_INSENSITIVE_ORDER);
        return retList;
    }

    public static List<String> preDeal(String str) throws RuntimeException {
        // throw exception and transform to upper case
        List<String> strings = Arrays.stream(str.split(",")).map( s -> {
            s = s.toUpperCase();
            if (!s.matches("^[A-Z]*$")) throw new RuntimeException("input not valid");
            return s;
        }).collect(Collectors.toList());
        // remove repeat and add space
        return  removeRepeatAddSpace(strings);
    }

    public static List<String> removeRepeatAddSpace(List<String> stringList) {
        List<String> ret = stringList.stream()
                .distinct()
                .map(s -> {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < s.length(); i++) {
                        stringBuffer.append(s.charAt(i) + " ");
                    }
                    stringBuffer.setLength(stringBuffer.length() - 1);
                    return stringBuffer.toString();
                })
                .collect(Collectors.toList());
        return ret;
    }
}
