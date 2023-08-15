import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class WeatherTest {

    @Test
    public void testWeatherEndpoint() {
        given()
                .when().get("/weather?city=NewYork")
                .then()
                .statusCode(200)
                .body("temperature", equalTo("25")) // Change to your expected temperature
                .body("condition", equalTo("Sunny")) // Change to your expected condition
                .body("city", equalTo("NewYork")); // Change to your expected city
    }
}





