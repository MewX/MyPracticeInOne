import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * Reference: http://www.baeldung.com/java-8-lambda-expressions-tips
 */
public class TestInterface {
    private String value = "Enclosing scope value";

    @FunctionalInterface
    public interface Foo {
        String method(String string);
    }

    @FunctionalInterface
    public interface TwoParam {
        String justAName(String a, String b);
    }

    public String add(String string, Foo foo) {
        return foo.method(string);
    }

    public String add(String string, Function<String, String> function) {
        return function.apply(string);
    }

    public String innerClassTest() {
        Foo fooIC = new Foo() {
            String value = "Inner class value";

            @Override
            public String method(String string) {
                return this.value;
            }
        };
        String resultIC = fooIC.method("");

        Foo fooLambda = parameter -> {
            String value = "Lambda value";
            return this.value;
        };
        String resultLambda = fooLambda.method("");
        return "Results: resultIC = " + resultIC + ", resultLambda = " + resultLambda;
    }

    public static void main(String[] args) throws InterruptedException {
        TestInterface test = new TestInterface();
        Foo foo = a -> a + "from lambda1";
        String result = test.add("Message: ", foo);
        System.out.println(result);

        Function<String, String> fn = a -> a + "from lambda2";
        String result2 = test.add("Message again: ", fn);
        System.out.println(result2);

        TwoParam foo2 = (a, b) -> a + b;
        System.out.println(foo2.justAName("1", "2"));

        // inner class test
        System.out.println(test.innerClassTest());

        // always use method reference when available
        Integer[] arrInt = new Integer[] {4, 2, 3, 1};
        Arrays.sort(arrInt, Integer::compareTo);
        for (int i : arrInt) System.out.print(i);
        System.out.println();

        List<Integer> arrayList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(arrInt)));
        Runnable run = () ->{
            synchronized(arrayList) {
                arrayList.add(5);
            }
        };
        run.run();
        for (int i : arrayList) System.out.print(i);
        System.out.println();

        List<Integer> arrayList2 = new CopyOnWriteArrayList<>(arrayList);
        Runnable run2 = () -> arrayList2.add(6);
        run2.run();
        for (int i : arrayList2) System.out.print(i);
        System.out.println();

    }
}
