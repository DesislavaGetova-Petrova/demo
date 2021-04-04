package desico.project.web;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.LessonEntity;
import desico.project.model.entity.UnitNameEntity;

import desico.project.repository.*;

import org.junit.jupiter.api.AfterEach;
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
public class LessonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnitNameRepository unitNameRepository;
    @Autowired
    private ChapterNameRepository chapterNameRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @AfterEach
    public void setup() {
        lessonRepository.deleteAll();
        chapterNameRepository.deleteAll();
        unitNameRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER","MODERATOR","ADMIN"})
    void testFetchVideos() throws Exception {
        UnitNameEntity unitNameEntity=new UnitNameEntity();
        unitNameEntity.setUnitName("unit");
        unitNameEntity=unitNameRepository.save(unitNameEntity);

        ChapterNameEntity chapterNameEntity=new ChapterNameEntity();
        chapterNameEntity.setChapterName("Chapter");
        chapterNameEntity.setUnitName(unitNameEntity);
        chapterNameEntity=chapterNameRepository.save(chapterNameEntity);

        LessonEntity lessonEntity=new LessonEntity();
        lessonEntity.setLessonUrl("/img/lesson/lesson.mp4").setLessonName("lessonName").setChapterName(chapterNameEntity);
        lessonEntity=lessonRepository.save(lessonEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/lesson/viewAll/api")).
                andExpect(status().isOk()).
                andExpect(jsonPath("[0].lessonUrl").value("/img/lesson/lesson.mp4")).
                andExpect(jsonPath("[0].lessonName").value("lessonName")).
                andExpect(jsonPath("[0].chapterName").value(chapterNameEntity.getChapterName()));


    }
}
