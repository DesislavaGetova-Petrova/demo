package desico.project.web;

import desico.project.repository.LogRepository;
import desico.project.repository.UserRepository;
import desico.project.repository.UserRoleRepository;
import desico.project.service.CloudinaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private LogRepository logRepository;
    @MockBean
    CloudinaryService mockCloudinaryService;

    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testIndexPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/")).
                andExpect(status().isOk()).
                andExpect(view().name("index"));

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/info")).
                andExpect(status().isOk()).
                andExpect(view().name("info")).
                andExpect(model().attributeExists("firstImg"));

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/offer")).
                andExpect(status().isOk()).
                andExpect(view().name("offer"));
    }
}
