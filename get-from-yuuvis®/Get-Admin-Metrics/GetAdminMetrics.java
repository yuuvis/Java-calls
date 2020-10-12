import okhttp3.*;
        import java.util.HashMap;
        import java.util.Map;

public class GetAdminMetrics
{
    public static final String BASEURL = "https://api.yuuvis.io"+"/admin";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    public static void main(String[] args)
    {
        Map<String, String> headerMap = new HashMap<>();
        String key = "5f0a8c7a1f924f76bb4875f71eb136c4";

        headerMap.put("Ocp-Apim-Subscription-Key", key);

        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient client = builder.build();

            Request request = null;
            Headers headers = Headers.of(headerMap);

            request = new Request.Builder().headers(headers).url(BASEURL + "/metrics").get().build();

            Response response = client.newCall(request).execute();
            System.out.println(response.toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}