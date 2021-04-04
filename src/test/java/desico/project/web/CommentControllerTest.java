package desico.project.web;

import desico.project.repository.CommentRepository;
import desico.project.repository.LogRepository;
import desico.project.repository.UnitNameRepository;
import desico.project.service.CloudinaryService;
import org.junit.jupiter.api.AfterEach;
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
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private LogRepository logRepository;
    @Autowired
    private CommentRepository commentRepository;
    @MockBean
    CloudinaryService mockCloudinaryService;

    @AfterEach
    public void clear(){
        commentRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testComments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/comments")).
                andExpect(status().isOk()).
                andExpect(view().name("comments"));

    }

}
