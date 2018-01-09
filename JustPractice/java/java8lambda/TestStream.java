import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Practice based on: http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class TestStream {
    public static void main(String[] args) {
        // full list of stream operations: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
        List<Integer> arr1 = Arrays.asList(1, 2, 3, 4);
        arr1.stream()
                .filter(i -> i <= 3)
                .map(i -> i * 10)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        System.out.println();

        // without collections
        Stream.of(2, 4, 1, 3)
                .findFirst() // Note: .findAny() is used for parallel computation
                .ifPresent(System.out::println);
        IntStream.range(1, 5) // this can replace simple for loop: for (int i = 1; i < 5; i ++)
                .map(i -> ~i)
                .sorted()
                .map(i -> ~i)
                .forEach(System.out::print);
        System.out.println();
        Stream.of(2, 4, 1, 3)
                .sorted(Comparator.reverseOrder())
                .map(i -> "a" + i)
                .forEach(System.out::print);
        System.out.println();
        System.out.println();

        // processing order test 1 - not matter
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println("----");

        System.out.println(Stream.of("d2", "a2", "b1", "b3", "c")
                .map(a -> {
                    System.out.println("map: " + a);
                    return a.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                }));

        // processing order test 2 - matters
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((a, b) -> {
                    // sorted() is executed all together at the beginning
                    // however, if there is only one element this function is not executed (no comparable values)
                    System.out.printf("sort: %s, %s\n", a, b);
                    return a.compareTo(b);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();

        // to reuse stream
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(s -> true));
        System.out.println(streamSupplier.get().noneMatch(s -> true));

        Consumer<String> consumer = System.out::println;
        consumer.accept("Consumer call!");
        System.out.println();

        // specific operations
        List<String> collectedList = Stream.of("d2", "a2", "b1", "b3", "c", "b4")
                .filter(s -> s.startsWith("b"))
                .collect(Collectors.toList());
        collectedList.forEach(System.out::print);
        System.out.println();
        collectedList.stream().reduce((a, b) -> String.format("%s, %s", a, b)).ifPresent(System.out::println);
        // non-null
        Stream.of(1, 2, 3, 4)
                .mapToInt(i -> i)
                .average().ifPresent(System.out::println);
        System.out.println(Stream.of(1, 2, 3, 4)
                .collect(Collectors.averagingInt(i -> i)));
        // null
        Arrays.stream(new Integer [] {})
                .mapToInt(i -> i)
                .average().ifPresent(System.out::println); // Good, it's not executed
        System.out.println(Arrays.stream(new Integer [] {})
                .collect(Collectors.averagingInt(i -> i))); // Not good, it shows 0.0

        // parallel stream (by default, is uses all cores)
        // ref: https://stackoverflow.com/questions/20375176/should-i-always-use-a-parallel-stream-when-possible
        // use sequential streams by default and only consider parallel ones if:
        // 1. have a massive amount of items to process (or the processing of each item takes time and is parallelizable)
        // 2. have a performance problem in the first place
        // 3. already run the process in a multi-thread environment (-Djava.util.concurrent.ForkJoinPool.common.parallelism=5)
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }
}
