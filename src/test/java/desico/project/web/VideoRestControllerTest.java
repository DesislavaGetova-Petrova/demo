package desico.project.web;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.repository.ChapterNameRepository;
import desico.project.repository.VideoRepository;
import desico.project.repository.UnitNameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class VideoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnitNameRepository unitNameRepository;
    @Autowired
    private ChapterNameRepository chapterNameRepository;
    @Autowired
    private VideoRepository videoRepository;

    @AfterEach
    public void setup() {
        videoRepository.deleteAll();
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

        VideoEntity videoEntity=new VideoEntity();
        videoEntity.setChapterName(chapterNameEntity).setVideoName("video").setVideoUrl("/img/video/video.mp4");
        videoEntity=videoRepository.save(videoEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/video/viewAll/api")).
                andExpect(status().isOk()).
                andExpect(jsonPath("[0].chapterName").value(chapterNameEntity.getChapterName())).
                andExpect(jsonPath("[0].videoName").value("video")).
                andExpect(jsonPath("[0].videoUrl").value("/img/video/video.mp4"));

    }
}
