package desico.project.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import desico.project.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase

public class LessonControllerTest {
    private static final String LESSON_CONTROLLER_PREFIX = "/lesson";

    private String  testLessonId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnitNameRepository unitNameRepository;
    @Autowired
    private ChapterNameRepository chapterNameRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LogRepository logRepository;
    private LessonTestData testData;

    @BeforeEach
    public void setup() {
        testData = new LessonTestData(
                unitNameRepository,
                chapterNameRepository,
                lessonRepository,
                logRepository
        );
        testData.init();
        testLessonId = testData.getTestLessonId();
    }

    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                LESSON_CONTROLLER_PREFIX + "/view/{id}", testLessonId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("lesson-details")).
                andExpect(model().attributeExists("lesson"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testViewAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                LESSON_CONTROLLER_PREFIX + "/viewAll")).
                andExpect(status().isOk()).
                andExpect(view().name("lessons-view-all")).
                andExpect(model().attributeExists("lessonEntity"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                LESSON_CONTROLLER_PREFIX + "/add")).
                andExpect(status().isOk()).
                andExpect(view().name("lesson-add")).
                andExpect(model().attributeExists("chapterNames"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testLessonAddPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(LESSON_CONTROLLER_PREFIX + "/add")
                .param("unitName", "unitName")
                .param("chapterName","chapterName")
                .param("lessonUrl", "lessonUrl")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(2, lessonRepository.count());
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                LESSON_CONTROLLER_PREFIX + "/view/{testLessonId}", testLessonId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("lesson-details")).
                andExpect(model().attributeExists("lesson"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                LESSON_CONTROLLER_PREFIX + "/delete/{testLessonId}", testLessonId
        )).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/"));
        Assertions.assertEquals(1, lessonRepository.count());
    }
}
