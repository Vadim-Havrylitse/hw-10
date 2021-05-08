import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    // for task 1 and 2
    public static List<String> names = new ArrayList<>(
            Arrays.asList("Kolya", "Dima", "Fedya",
                    "Ivan", "Yana", "Petro",
                    "Olga", "Sofiya", "Vasya"));

    // for task 3
    static String[] arr = {"1, 2, 0", "4, 5"};

    // for task 4
    static long a = 25214903917L;
    static long c = 11;
    static long m = 281474976710656L;

    public static void printLine(int n) {
        UnaryOperator<String> repeatLine = str -> str.repeat(n);
        System.out.println("\n" + repeatLine.apply("-") + "\n");
    }


    public static void main(String[] args) {
        BiFunction<String, Integer, String> concatNameAndNumber = (name, index) -> index.toString().concat(". ").concat(name).concat(", ");
        System.out.println(task1(names, concatNameAndNumber));
        printLine(30);

        System.out.println(task2(names));
        printLine(30);

        System.out.println(task3(arr));
        printLine(30);

        task4(a, c, m, 45221568)
                .limit(5)
                .forEach(n -> System.out.println(n));
        printLine(30);

        //task5
        zip(Stream.of(arr), names.parallelStream()).forEach(System.out::println);
    }


    public static String task1(List<String> names, BiFunction function) {
        if (names.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) {
                result.append(function.apply(names.get(i), i + 1));
            }
        }
        return result.substring(0, result.length() - 2);

    }

    public static List<String> task2(List<String> list) {
        List<String> result = list.stream()
                .map(name -> name.toUpperCase())
                .sorted((name1, name2) -> name2.compareTo(name1))
                .collect(Collectors.toList());
        return result;
    }

    public static String task3(String[] array) {
        List<Integer> intList = Arrays.stream(array).map(x -> x.split(", "))
                .flatMap(Arrays::stream)
                .map(c -> Integer.parseInt(String.valueOf(c)))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return intList.toString();
    }

    public static Stream<Long> task4(long a, long c, long m, long seed) {
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }

    //task5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> result = new ArrayList<>();
        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();
        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            result.add(firstIterator.next());
            result.add(secondIterator.next());
        }
        return result.stream();
    }

}

