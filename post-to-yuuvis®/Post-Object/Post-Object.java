import okhttp3.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PostObject
{

    public static final String BASEURL = "https://api.yuuvis.io"+"/dms-core";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    public static void main(String[] args)
    {
        Map<String, String> headerMap = new HashMap<>();
        String key = "";

        headerMap.put("Content-Type", "multipart/form-data, application/x-www-form-urlencoded");
        headerMap.put("Ocp-Apim-Subscription-Key", key);

        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient client = builder.build();

            Request request = null;
            Headers headers = Headers.of(headerMap);

            RequestBody body = null;

            // relative path to your content file
            String contentFilePath = "";
            // relative path to your metadata file
            String metaDataFilePath = "";

            // import multipart body
            body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("data", "metadata.json", RequestBody.create(new File(metaDataFilePath), JSON))
                    .addFormDataPart("cid_63apple", "filename.txt", RequestBody.create(new File(contentFilePath), PLAINTEXT)).build();

            request = new Request.Builder().headers(headers).url(BASEURL + "/objects").post(body).build();

            Response response = client.newCall(request).execute();
            System.out.println(response.toString());
            System.out.println(response.body().string());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
