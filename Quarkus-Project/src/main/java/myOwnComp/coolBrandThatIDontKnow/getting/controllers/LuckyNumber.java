package myOwnComp.coolBrandThatIDontKnow.getting.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Random;

@Path("/lucky-number")
@Tag(name = "Lucky Number", description = "Endpoint to get a lucky number")
public class LuckyNumber {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a lucky number")
    @APIResponse(responseCode = "200", description = "Lucky number",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = LuckyNumberData.class)))
    public LuckyNumberData getLuckyNumber() {
        int luckyNumber = generateLuckyNumber();
        return new LuckyNumberData(luckyNumber);
    }

    private int generateLuckyNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public static class LuckyNumberData {
        @JsonProperty("luckyNumber")
        private int luckyNumber;

        public LuckyNumberData(int luckyNumber) {
            this.luckyNumber = luckyNumber;
        }

        public int getLuckyNumber() {
            return luckyNumber;
        }
    }
}
