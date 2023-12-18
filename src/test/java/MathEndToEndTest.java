import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathEndToEndTest {

    // Autowire the WebTestClient to perform end-to-end tests
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testDoMathEndToEnd() {
        // Perform the end-to-end test similar to MathControllerTest and MathIntegrationTest
        webTestClient.post().uri("/api/math/doMath")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"operand1\": 5, \"operand2\": 4, \"operation\": \"*\"}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.calcResponse").isEqualTo(20.0);
    }
}