package closableUtils;

@FunctionalInterface
public interface Supplier_WithExceptions<T, E extends Exception> {
    T get() throws E;
}
