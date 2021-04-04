package desico.project.web;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.repository.ChapterNameRepository;
import desico.project.repository.LogRepository;
import desico.project.repository.UnitNameRepository;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ChapterControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private LogRepository logRepository;
    @Autowired
    private UnitNameRepository unitNameRepository;
    @Autowired
    private ChapterNameRepository chapterNameRepository;
    @MockBean
    CloudinaryService mockCloudinaryService;

    @BeforeEach
    public void init(){
        UnitNameEntity unitNameEntity = new UnitNameEntity();
        unitNameEntity.setUnitName("unit");
        unitNameEntity = unitNameRepository.save(unitNameEntity);

        ChapterNameEntity chapterNameEntity = new ChapterNameEntity();
        chapterNameEntity.setChapterName("Chapter");
        chapterNameEntity.setUnitName(unitNameEntity);
        chapterNameEntity = chapterNameRepository.save(chapterNameEntity);
    }

    @AfterEach
    public void clear(){
        chapterNameRepository.deleteAll();
        unitNameRepository.deleteAll();

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testChapterAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/chapter/add")).
                andExpect(status().isOk()).
                andExpect(view().name("chapter-add"));

    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testChapterAddPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/chapter/add")
                .param("unitName", "unit")
                .param("chapterName","chapterName")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(2,chapterNameRepository.count());
    }
}
