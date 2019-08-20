import okhttp3.*;
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
        String objectId = "";
        String versionId = "";

    	  headerMap.put("Ocp-Apim-Subscription-Key", key);

        try{
          OkHttpClient.Builder builder = new OkHttpClient.Builder();
          OkHttpClient client = builder.build();

          Request request = null;
          Headers headers = Headers.of(headerMap);

          request = new Request.Builder()
            .headers(headers)
            .url(baseUrl +"/dms/objects/"+objectId+"/versions/"+versionId)
            .get().build();



        Response response = client.newCall(request).execute();
        System.out.println(response.toString());


        } catch(Exception e) {
          e.printStackTrace();
        }

    }
}
