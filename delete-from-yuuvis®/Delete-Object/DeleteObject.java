import okhttp3.*;
import java.util.HashMap;
import java.util.Map;

public class DeleteObject
{

    public static final String BASEURL = "https://api.yuuvis.io"+"/dms-core";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    public static void main(String[] args)
    {
        Map<String, String> headerMap = new HashMap<>();
        String key = "";
        String objectId = "";

        headerMap.put("Ocp-Apim-Subscription-Key", key);

        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient client = builder.build();

            Request request = null;
            Headers headers = Headers.of(headerMap);

            request = new Request.Builder().headers(headers).url(BASEURL + "/objects/" + objectId).delete().build();

            Response response = client.newCall(request).execute();
            System.out.println(response.toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
