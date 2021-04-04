package desico.project.service;

import com.google.gson.Gson;
import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.UnitNameServiceModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.model.view.VideoViewModel;
import desico.project.repository.VideoRepository;
import desico.project.service.impl.UnitNameServiceImpl;
import desico.project.service.impl.VideoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    private VideoServiceImpl videoService;
    @Mock
    VideoRepository mockVideoRepository;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    ChapterNameService mockChapterNameService;
    @Mock
    CloudinaryService mockCloudinaryService;

    @BeforeEach
    public  void init() {
        mockModelMapper = new ModelMapper();
        videoService=new VideoServiceImpl(mockVideoRepository,mockModelMapper,mockChapterNameService,mockCloudinaryService);
    }

    @Test
    void deleteVideoTest(){
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        Mockito.when(mockVideoRepository.findById(video.getId())).thenReturn(Optional.of(video));
        videoService.deleteVideo(video.getId());
        Mockito.verify(mockVideoRepository).delete(video);
    }
    @Test
    void findAllVideosTest(){
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        VideoEntity video1= new VideoEntity();
        video1.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video1.setVideoName("video");
        video1.setVideoUrl("videoUrl");
        Mockito.when(mockVideoRepository.findAll()).thenReturn(List.of(video,video1));
        Assertions.assertEquals(2, videoService.findAll().size());
    }
    @Test
    void findVideoEntityByIdTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        Mockito.when(mockVideoRepository.findById(video.getId())).thenReturn(Optional.of(video));
        videoService.findEntityById(video.getId());
        Mockito.verify(mockVideoRepository).findById(video.getId());

    }
    @Test
    void videoNameExistTest(){
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        assertEquals(false,videoService.videoNameExists("No"));
    }
    @Test
    void findAllVideoViewModelsTest(){
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        List<VideoViewModel> videoViewModels = new ArrayList<>();
        videoViewModels.add(this.mockModelMapper.map(video, VideoViewModel.class));
        Mockito.when(mockVideoRepository.findAll()).thenReturn(List.of(video));
        Assertions.assertEquals(1,videoService.findAllVideos().size());
    }
    @Test
    void findVideoEntityByChapterNameTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        Mockito.when(mockVideoRepository.findByChapterName(video.getChapterName())).thenReturn(List.of(video));
        videoService.findbyChapterName(video.getChapterName());
        Mockito.verify(mockVideoRepository).findByChapterName(video.getChapterName());
    }
    @Test
    void findVideoEntityByVideoNameTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        Mockito.when(mockVideoRepository.findByVideoName(video.getVideoName())).thenReturn(Optional.of(video));
        videoService.findByVideoName(video.getVideoName());
        Mockito.verify(mockVideoRepository).findByVideoName(video.getVideoName());
    }
    @Test
    void findVideoEntityByVideoNameTest2() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        Assertions.assertThrows(
                IllegalStateException.class, () -> {
                   videoService.findByVideoName("novideo");
                }
        );
    }
    @Test
    void findVideoViewModelByIdTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        List<VideoViewModel> videoViewModels = new ArrayList<>();
        videoViewModels.add(this.mockModelMapper.map(video, VideoViewModel.class));
        Mockito.when(mockVideoRepository.findById(video.getId())).thenReturn(Optional.of(video));
        videoService.findById(video.getId());
        Mockito.verify(mockVideoRepository).findById(video.getId());
    }
    @Test
    public void addVideoTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        mockVideoRepository.save(video);
        VideoServiceModel videoServiceModel = mockModelMapper.map(video, VideoServiceModel.class);
        videoService.add(videoServiceModel);
        Mockito.verify(mockVideoRepository).save(video);
    }
    @Test
    public void addVideoMultipartTest() throws IOException {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        mockVideoRepository.save(video);
        VideoServiceModelCloud videoServiceModel = mockModelMapper.map(video, VideoServiceModelCloud.class);
        videoService.addVideo(videoServiceModel);
        Mockito.verify(mockVideoRepository).save(video);
    }
}
