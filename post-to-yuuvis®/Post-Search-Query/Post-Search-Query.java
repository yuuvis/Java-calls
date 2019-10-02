import okhttp3.*;
import java.io.File;
import java.util.HashMap;

public class JavaSample{
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
        public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
        public static final String baseUrl = "https://api.yuuvis.io"+"/dms-core";

    public static void main (String[] args){
        HashMap headerMap = new HashMap();
        HashMap parameterMap = new HashMap();
        String key = "";


    	  headerMap.put("Content-Type", "application/json");
    	  headerMap.put("Ocp-Apim-Subscription-Key", key);

        try{
          OkHttpClient.Builder builder = new OkHttpClient.Builder();
          OkHttpClient client = builder.build();

          Request request = null;
          Headers headers = Headers.of(headerMap);


          RequestBody body = null;



        //relative path to your query file
        String queryFilePath = "";
        body = RequestBody.create(JSON, new File(queryFilePath));

          request = new Request.Builder()
            .headers(headers)
            .url(baseUrl + "/objects/search")
            .post(body)
            .build();


        Response response = client.newCall(request).execute();
        System.out.println(response.toString());
        System.out.println(response.body().string());


        } catch(Exception e) {
          e.printStackTrace();
        }

    }
}
