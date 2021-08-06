public class ReferenceTypeDemo {
    public static void main(String[] args) {
        Writer a = new Writer(18);
        Writer b = new Writer(18);
        modify(a, b);

        System.out.println(a.getAge());
        System.out.println(b.getAge());

        for (int i = 0; i < 5; i++) {
            Object o = new Object();
        }
        Object o = new Object();

    }

    private static void modify(Writer a1, Writer b1) {
        a1.setAge(30);

        b1 = new Writer(18);
        b1.setAge(30);
    }
    static class Writer{
        private Integer age;

        public Writer(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
