
import closableUtils.Using;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by atrposki on 11/14/2016.
 */
class GetJson<T> implements IGetJson<T> {
    HttpClient client;
    Gson gson;
    Class<T> resultClass;
    public GetJson(HttpClient client, Gson gson,Class<T> resultClass)
    {
        this.client = client;
        this.gson=gson;
        this.resultClass = resultClass;
    }


    @Override
    public T Get(String url) throws IOException {
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept","application/json");
        HttpResponse response = client.execute(getRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        String responseContent =
        Using.Closable(()-> new BufferedReader(new InputStreamReader((response.getEntity().getContent()))),
        br->{
            StringBuilder sb = new StringBuilder();
            String line ="";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        });        
        T result =   ConvertFromJson(responseContent);
        return  result;

    }

    private  T ConvertFromJson(String json)
    {
        try {
            Type type = this.getClass().getGenericInterfaces()[0];
            Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];
            T result =  (T) gson.fromJson(json, this.resultClass);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


}
