package desico.project.web;

import desico.project.repository.LogRepository;
import desico.project.repository.UnitNameRepository;
import desico.project.service.CloudinaryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UnitControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private LogRepository logRepository;
    @Autowired
    private UnitNameRepository unitNameRepository;
    @MockBean
    CloudinaryService mockCloudinaryService;

    @AfterEach
    public void clear(){
        unitNameRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testUnitAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/unit/add")).
                andExpect(status().isOk()).
                andExpect(view().name("unit-add"));

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testUnitAddPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/unit/add")
                .param("unitName", "unitName")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(1, unitNameRepository.count());
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testUnitView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/unit/view")).
                andExpect(status().isOk()).
                andExpect(view().name("unit-view")).
                andExpect(model().attributeExists("unitNamesEnteties"));

    }

}
