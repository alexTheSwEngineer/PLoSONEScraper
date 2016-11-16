package closableUtils;

/**
 * Created by atrposki on 11/14/2016.
 */

@FunctionalInterface
public interface Consumer_WithException<T, E extends Exception> {
    void accept(T t) throws E;
}

