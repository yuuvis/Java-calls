import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class ApiCaller {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType PLAINTEXT = MediaType.parse("text/plain; charset=utf-8");
    public static final MediaType OCTETSTREAM = MediaType.parse("application/octetstream; charset=utf-8");
    private final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private String key = "";
    private String baseURL = "https://api.yuuvis.io";
    private OkHttpClient client;

    public ApiCaller(String key) {
        this.key = key;
        this.client = builder.build();
    }

    /**
     * imports the given file to yuuvis®
     * @param fileName, filePath
     * @return
     */
    public Response importFile(String fileName, String filePath) throws IOException {
        RequestBody importRequestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("data", "stubMetadata.json", RequestBody.create(JSON, new File("D:\\Projects\\helloYuuvisJava\\src\\main\\resources\\stubMetadata.json")))
                .addFormDataPart("cid_63apple", fileName, RequestBody.create(OCTETSTREAM, new File(filePath)))
                .build();

        Request importRequest = new Request.Builder()
                .header("Ocp-Apim-Subscription-Key", key)
                .url(baseURL + "/dms/objects")
                .post(importRequestBody)
                .build();

        Response response = client.newCall(importRequest).execute();
        return response;
    }

    /**
     * retrieves the content of the yuuvis® object corresponding to the given objectId
     * @param objectId
     * @return
     */
    public Response getFileByObjectId(String objectId) throws IOException {
        Request getContentRequest = new Request.Builder()
                .header("Ocp-Apim-Subscription-Key", key)
                .url(baseURL + "/dms/objects/" + objectId + "/contents/file")
                .get().build();

        Response response = client.newCall(getContentRequest).execute();
        return response;
    }

    /**
     * updates the content of the yuuvis® object corresponding to the given objectId using the given file
     * @param objectId
     * @return
     */
    public Response updateObjectContentByObjectId(String objectId, String filepath) throws IOException {
        Request updateContentRequest = new Request.Builder()
                .header("Ocp-Apim-Subscription-Key", key)
                .url(baseURL + "/dms/objects/" + objectId + "/contents/file")
                .post(RequestBody.create(OCTETSTREAM, new File(filepath)))
                .build();

        Response response = client.newCall(updateContentRequest).execute();
        return response;
    }

    /**
     * creates a full text search query matching to the given keyword and posts to the yuuvis® search endpoint
     * @param keyword
     * @return
     */
    public Response fullTextSearchForKeyword(String keyword) throws IOException {
        String query = createFulltextQuery(keyword);

        Request searchRequest = new Request.Builder()
                .header("Ocp-Apim-Subscription-Key", key)
                .url(baseURL+"/dms/objects/search")
                .post(RequestBody.create(JSON, query))
                .build();

        Response response = client.newCall(searchRequest).execute();
        return response;
    }

    /**
     * deletes the specified object from yuuvis®
     * @param objectId
     * @return
     */
    public Response deleteObjectById(String objectId, String versionId) throws IOException {
        Request deleteRequest = new Request.Builder()
                .header("Ocp-Apim-Subscription-Key", key)
                .url(baseURL + "/dms/objects/" + objectId + "/versions/" +versionId)
                .delete()
                .build();

        Response response = client.newCall(deleteRequest).execute();
        return response;
    }


    /**
     * creates simple CMIS query for a full-text search
     * @param text
     * @return query in JSON format
     */
    public static String createFulltextQuery(String text) {
        String result = "";
        try {
            String statement = "SELECT * FROM enaio:object WHERE CONTAINS('" + text + "')";
            return createQueryJSON(statement, 0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * creates JSON from query parameters
     * @param statement
     * @param skipCount
     * @param maxItems
     * @return
     */
    public static String createQueryJSON(String statement, int skipCount, int maxItems) {
        JSONObject queryObject = new JSONObject();
        JSONObject queryAttributes = new JSONObject();
        queryAttributes.put("statement", statement);
        queryAttributes.put("skipCount", skipCount);
        queryAttributes.put("maxItems", maxItems);
        queryObject.put("query", queryAttributes);

        return queryObject.toString();
    }

    public String copyObjectIdFromResponse(String apiResponseString){
        JSONObject responseObject = new JSONObject(apiResponseString);
        return responseObject
                .getJSONArray("objects")
                .getJSONObject(0)
                .getJSONObject("properties")
                .getJSONObject("enaio:objectId")
                .getString("value");
    }
}
