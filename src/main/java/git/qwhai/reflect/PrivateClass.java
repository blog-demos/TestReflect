package git.qwhai.reflect;

public class PrivateClass {
    @SuppressWarnings("unused")
    private int i = 0;

    private PrivateClass() {
    }

    @SuppressWarnings("unused")
    private void sayHello() {
        System.out.println("Haha, I'm coming...bilibili");
    }
}
