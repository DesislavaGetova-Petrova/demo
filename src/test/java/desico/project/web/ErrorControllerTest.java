package desico.project.web;

import desico.project.repository.LogRepository;
import desico.project.service.CloudinaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ErrorControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private LogRepository logRepository;
    @MockBean
    CloudinaryService mockCloudinaryService;

    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testErrorPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/error")).
                andExpect(status().isOk()).
                andExpect(view().name("error"));

    }

}
