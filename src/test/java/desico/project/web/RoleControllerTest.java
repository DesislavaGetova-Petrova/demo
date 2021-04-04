package desico.project.web;

import desico.project.model.entity.UserEntity;
import desico.project.model.entity.UserRoleEntity;
import desico.project.model.enums.UserRole;
import desico.project.repository.LogRepository;
import desico.project.repository.UnitNameRepository;
import desico.project.repository.UserRepository;
import desico.project.repository.UserRoleRepository;
import desico.project.service.CloudinaryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static desico.project.model.enums.UserRole.USER;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private LogRepository logRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @MockBean
    CloudinaryService mockCloudinaryService;

    @BeforeEach
    public void init(){
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(USER);
        userRoleRepository.save(userRoleEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("gosho").setEmail("p@p").setPassword("password").setRoles(List.of(userRoleEntity));
        userRepository.save(userEntity);
    }
    @AfterEach
    public void clear(){
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }



    @Test
    @WithMockUser(value = "pesho", roles = {"ADMIN"})
    void testRoleAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/roles/add")).
                andExpect(status().isOk()).
                andExpect(view().name("role-add"));

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"ADMIN"})
    void testRoleAddPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/roles/add")
                .param("username", "gosho")
                .param("role","MODERATOR")
                .with(csrf())).andExpect(status().isOk());
        Assertions.assertEquals(1,userRepository.findByUsername("gosho").get().getRoles().size());
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"ADMIN"})
    void testRoleDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/roles/delete")).
                andExpect(status().isOk()).
                andExpect(view().name("role-delete"));

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"ADMIN"})
    void testRoleDeletePost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/roles/delete")
                .param("username", "gosho")
                .param("role","MODERATOR")
                .with(csrf())).andExpect(status().isOk());
        Assertions.assertEquals(0,userRepository.findByUsername("gosho").get().getRoles().size());
    }
}
