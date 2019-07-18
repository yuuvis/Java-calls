import okhttp3.*;
import java.io.File;
import java.util.HashMap;

public class JavaSample{
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
        public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
        public static final String baseUrl = "https://api.yuuvis.io";
    
    public static void main (String[] args){
        HashMap headerMap = new HashMap();
        HashMap parameterMap = new HashMap();
        String key = "";
        

    	  headerMap.put("Content-Disposition", "");
    	  headerMap.put("Content-Type", "application/octet-stream");
    	  headerMap.put("Ocp-Apim-Subscription-Key", "{subscription key}");

        try{
          OkHttpClient.Builder builder = new OkHttpClient.Builder();
          OkHttpClient client = builder.build();

          Request request = null;
          Headers headers = Headers.of(headerMap);


          RequestBody body = null;
          
          

          //relative path to your content file
          String contentFilePath = "";
          //update content RequestBody
          body = RequestBody.create(PLAINTEXT, new File(contentFilePath));
          
          request = new Request.Builder()
            .headers(headers)
            .url(baseUrl + "/dms/objects/{objectId}/contents/file")
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