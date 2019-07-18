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
        

    	  headerMap.put("Content-Type", "multipart/form-data");
    	  headerMap.put("Ocp-Apim-Subscription-Key", "{subscription key}");

        try{
          OkHttpClient.Builder builder = new OkHttpClient.Builder();
          OkHttpClient client = builder.build();

          Request request = null;
          Headers headers = Headers.of(headerMap);


          RequestBody body = null;
          
          

        //relative path to your schema file
        String schemaFilePath = "";
          //schema update multipart body
          body = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", "schema.xml", RequestBody.create(XML, new File(schemaFilePath)))
            .build();

          request = new Request.Builder()
            .headers(headers)
            .url(baseUrl + "/admin/schema")
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