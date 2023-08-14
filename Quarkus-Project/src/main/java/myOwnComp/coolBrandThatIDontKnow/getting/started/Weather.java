package myOwnComp.coolBrandThatIDontKnow.getting.started;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/weather")
public class Weather {

    @GET
    public String getWeather() {
        // Return mock weather data (JSON, XML, etc.)
        return "{\"temperature\": 25, \"condition\": \"Sunny\"}";
    }
}