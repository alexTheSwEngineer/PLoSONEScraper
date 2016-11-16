import java.io.IOException;

/**
 * Created by atrposki on 11/14/2016.
 */
public interface IGetJson<T> {
    public T Get(String url) throws IOException;
}
