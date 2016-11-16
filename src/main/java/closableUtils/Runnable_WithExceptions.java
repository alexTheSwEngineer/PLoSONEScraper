package closableUtils;

@FunctionalInterface
public interface Runnable_WithExceptions<E extends Exception> {
    void run() throws E;
}
