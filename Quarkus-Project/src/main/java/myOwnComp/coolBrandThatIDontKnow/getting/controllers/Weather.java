package myOwnComp.coolBrandThatIDontKnow.getting.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Path("/weather")
@Tag(name = "Weather", description = "Endpoint to get mock weather data")
public class Weather {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get weather information by city")
    @APIResponse(responseCode = "200", description = "Weather information",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = WeatherData.class)))
    public WeatherData getWeather(@QueryParam("city") String city) {

        String temperature = generateRandomTemperature();
        String condition = generateRandomCondition();

        Map<String, String> weatherData = new HashMap<>();
        weatherData.put("temperature", temperature);
        weatherData.put("condition", condition);
        weatherData.put("city", city);
        return new WeatherData(weatherData);
    }

    private String generateRandomTemperature() {

        Random random = new Random();
        int temperatureValue = random.nextInt(61) - 20;
        return Integer.toString(temperatureValue);
    }

    private String generateRandomCondition() {

        String[] conditions = {"Sunny", "Cloudy", "Partly Cloudy", "Rainy", "Snowy"};


        Random random = new Random();
        int index = random.nextInt(conditions.length);
        return conditions[index];
    }


    public static class WeatherData {
        @JsonProperty("temperature")
        private String temperature;

        @JsonProperty("condition")
        private String condition;

        @JsonProperty("city")
        private String city;

        public WeatherData(Map<String, String> data) {
            this.temperature = data.get("temperature");
            this.condition = data.get("condition");
            this.city = data.get("city");
        }

        public String getTemperature() {
            return temperature;
        }

        public String getCondition() {
            return condition;
        }

        public String getCity() {
            return city;
        }
    }
}