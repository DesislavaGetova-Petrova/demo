package desico.project.web;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class VideoControllerTest {
    private static final String VIDEO_CONTROLLER_PREFIX = "/video";

    private String  testVideoId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnitNameRepository unitNameRepository;
    @Autowired
    private ChapterNameRepository chapterNameRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private LogRepository logRepository;
    private VideoTestData testData;

    @BeforeEach
    public void setup() {
        testData = new VideoTestData(
                unitNameRepository,
                chapterNameRepository,
                videoRepository,
                logRepository
        );
        testData.init();
        testVideoId = testData.getTestVideoId();
    }

    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                VIDEO_CONTROLLER_PREFIX + "/view/{id}", testVideoId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("video-details")).
                andExpect(model().attributeExists("video"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                VIDEO_CONTROLLER_PREFIX + "/add")).
                andExpect(status().isOk()).
                andExpect(view().name("video-add")).
                andExpect(model().attributeExists("chapterNames"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testAddLimited() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                VIDEO_CONTROLLER_PREFIX + "/addLimited")).
                andExpect(status().isOk()).
                andExpect(view().name("video-add-limited")).
                andExpect(model().attributeExists("chapterNames"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testVideoAddPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(VIDEO_CONTROLLER_PREFIX + "/add")
                .param("unitName", "unitName")
                .param("chapterName","chapterName")
                .param("videoUrl", "videoUrl")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(2, videoRepository.count());
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                VIDEO_CONTROLLER_PREFIX + "/view/{testVideoId}", testVideoId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("video-details")).
                andExpect(model().attributeExists("video"));
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"MODERATOR","ADMIN"})
    void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                VIDEO_CONTROLLER_PREFIX + "/delete/{testvideoId}", testVideoId
        )).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/video/viewAll"));
        Assertions.assertEquals(1, videoRepository.count());
    }


}
