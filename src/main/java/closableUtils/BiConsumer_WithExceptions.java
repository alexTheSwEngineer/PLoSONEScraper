package closableUtils;

@FunctionalInterface
public interface BiConsumer_WithExceptions<T, U, E extends Exception> {
    void accept(T t, U u) throws E;
}
