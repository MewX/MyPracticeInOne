public class TestDefaultMethod {
    private interface I {
        public void d(int a);

        default void show() {
            System.out.println("testing output");
        }
    }

    private interface I2 {
        // it can do nothing, but it acts like an optional function to be implemented
        default void test() {
            // it's a function place holder that does nothing
        }
    }

    private static class Impl implements I, I2 {
        @Override
        public void d(int a) {
            System.out.println("testing implementation");
            show();
        }
    }

    public static void main(String[] args) {
        Impl impl = new Impl();
        impl.show();
        impl.d(1);
        impl.test();
    }

}
