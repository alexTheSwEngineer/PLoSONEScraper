package closableUtils;

@FunctionalInterface
public interface Function_WithExceptions<T, R, E extends Exception> {
    R apply(T t) throws E;
}
