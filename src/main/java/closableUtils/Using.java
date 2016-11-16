package closableUtils;

/**
 * Created by atrposki on 11/14/2016.
 */

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by atrposki on 11/14/2016.
 */
public class Using{
    private   static  <T extends Closeable,E extends  Exception> void Closable(T closable, Consumer_WithException<T,E> action) throws E,IOException {
        try {
            action.accept(closable);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(closable!=null)
            {
                closable.close();
            }
        }
    }

    public  static <T extends Closeable,ES extends  Exception,EA extends  Exception>  void Closable(Supplier_WithExceptions<T,ES> create, Consumer_WithException<T,EA> action) throws ES,EA,IOException {
        Closable(create.get(),action);
    }

    public  static <Res,T extends Closeable,ES extends  Exception,EA extends  Exception>  Res Closable(Supplier_WithExceptions<T,ES> create, Function_WithExceptions<T,Res,EA> action) throws ES,EA,IOException {
        return  Closable(create.get(),action);
    }

    private   static  <Res,T extends Closeable,E extends  Exception> Res Closable(T closable, Function_WithExceptions<T,Res,E> action) throws E,IOException {
        try {
            return action.apply(closable);
        } finally {
            if(closable!=null)
            {
                closable.close();
            }
        }
    }
}
