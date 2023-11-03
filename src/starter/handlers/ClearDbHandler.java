package handlers;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import responses.clearResponse;
import services.ClearService;

public class ClearDbHandler {
    private final ClearService clearService;
    private final Gson gson;

    public ClearDbHandler(ClearService clearService, Gson gson) {
        this.clearService = clearService;
        this.gson = gson;
    }

    public Object handleClear(Request request, Response response) {
        response.type("application/json");
        try {
            clearService.clear();
            response.status(200);
            return gson.toJson(new clearResponse(true, "Databases cleared successfully"));
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new clearResponse(false, "Failed to clear the databases"));
        }
    }
}