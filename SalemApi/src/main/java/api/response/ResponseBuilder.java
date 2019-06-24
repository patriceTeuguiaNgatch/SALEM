package api.response;

import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.Response;

public class ResponseBuilder {

    public static Response createResponse( Response.Status status, String message ) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put( "message", message );
        }
        catch( JSONException e ) {
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }

        return Response.status( status ).entity( jsonObject.toString() ).build();
    }

}