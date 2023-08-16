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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/currency")
@Tag(name = "Currency", description = "Endpoint to get mock currency values")
public class Currency {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get currency information by country")
    @APIResponse(responseCode = "200", description = "Currency information",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = CurrencyData.class)))
    public List<CurrencyData> getCurrency(@QueryParam("country") String country) {
        List<CurrencyData> currencyList = new ArrayList<>();

        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        Random random = new Random();

        for (String currencyCode : currencies) {
            String value = String.format("%.2f", random.nextDouble() * 100);
            currencyList.add(new CurrencyData(currencyCode, value, country));
        }

        return currencyList;
    }


    public static class CurrencyData {
        @JsonProperty("currencyCode")
        private String currencyCode;

        @JsonProperty("value")
        private String value;

        @JsonProperty("country")
        private String country;

        public CurrencyData(String currencyCode, String value, String country) {
            this.currencyCode = currencyCode;
            this.value = value;
            this.country = country;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public String getValue() {
            return value;
        }

        public String getCountry() {
            return country;
        }
    }
}
