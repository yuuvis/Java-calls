import okhttp3.*;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import Java.util.HashMap;

public class JavaSample{
    public static void main (String[] args){
        public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
        public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
        public static final String baseUrl = "https://yuuvis.azure-api.net";

        private HashMap headerMap = new HashMap();
        private HashMap parameterMap = new HashMap();

    	  headerMap.put("Ocp-Apim-Subscription-Key", "{subscription key}");

        try{
          OkHttpClient.Builder builder = new OkHttpClient.Builder();
          builder.cookieJar(new JavaNetCookieJar(new CookieManager(null, CookiePolicy.ACCEPT_ALL)));
          OkHttpClient client = builder.build();

          Request request = null;
          Headers headers = Headers.of(headerMap)

          request = new Request.Builder()
            .headers(headers)
            .url(baseUrl + "/dms/schema/native")
            .get().build();



        Response response = client.newCall(request).execute();

        } catch(Exception e) {
          e.printStackTrace();
        }


    }
}