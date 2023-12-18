import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

public class MathControllerTest {

    @Mock
    private MathOperator mathOperator;

    @InjectMocks
    private MathController mathController;

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();

    @Test
    public void testDoMath() throws Exception {
        // Mocking mathOperator behavior
        Mockito.when(mathOperator.doMath(5, 4, "*")).thenReturn(20.0);

        // Performing the request and asserting the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/math/doMath")
                        .content("{\"operand1\": 5, \"operand2\": 4, \"operation\": \"*\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(20.0));
    }
}
