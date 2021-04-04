package desico.project.service.impl;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.LogServiceModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.model.view.VideoViewModel;
import desico.project.repository.VideoRepository;
import desico.project.service.ChapterNameService;
import desico.project.service.CloudinaryService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;
    private final ChapterNameService chapterNameService;
    private final CloudinaryService cloudinaryService;

    public VideoServiceImpl(VideoRepository videoRepository,
                            ModelMapper modelMapper,
                            ChapterNameService chapterNameService,
                            CloudinaryService cloudinaryService) {
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void add(VideoServiceModel videoServiceModel) {
        VideoEntity videoEntity=this.modelMapper.map(videoServiceModel,VideoEntity.class);
        videoEntity.setChapterName(this.chapterNameService.findByChapterName(videoServiceModel.getChapterName()));       this.videoRepository.saveAndFlush(videoEntity);

    }


    @Override
    public void addVideo(VideoServiceModelCloud videoServiceModel) throws IOException {
        MultipartFile videoUrl=videoServiceModel.getVideoUrl();
        String videoUrlNew =cloudinaryService.uploadVideo(videoUrl);
        VideoEntity videoEntity=new VideoEntity()
                .setVideoName(videoServiceModel.getVideoName())
                .setChapterName(this.chapterNameService.findByChapterName(videoServiceModel.getChapterName()))
                .setVideoUrl(videoUrlNew)
                .setDescription(videoServiceModel.getDescription());
        videoRepository.saveAndFlush(videoEntity);
    }

    @Override
    public boolean videoNameExists(String videoName) {
        return videoRepository.findByVideoName(videoName).isPresent();
    }

    @Override
    public List<VideoEntity> findbyChapterName(ChapterNameEntity chapterNameEntity) {
        return videoRepository.findByChapterName(chapterNameEntity);
    }

    @Override
    public VideoEntity findByVideoName(String videoName) {
        return videoRepository.findByVideoName(videoName).orElseThrow(
                () -> new IllegalStateException("Няма видео-решение с такова име."));
    }


    @Override
    public VideoViewModel findById(String id){
        return videoRepository
                .findById(id)
                .map(videoEntity -> {
                    VideoViewModel videoViewModel = modelMapper
                            .map(videoEntity, VideoViewModel.class);
                    videoViewModel.setChapterName(videoEntity.getChapterName().getChapterName());
                    return videoViewModel;
                })
                .orElseThrow(IllegalArgumentException::new);
    }
    @Override
    public VideoEntity findEntityById(String videoId) {
        return videoRepository
                .findById(videoId)
                .orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public List<VideoEntity> findAll() {
        return videoRepository.findAll();
    }


    @Override
    public List<VideoViewModel> findAllVideos() {
        return videoRepository.findAll().stream()
                .map(videoEntity -> {
                    VideoViewModel videoViewModel=modelMapper
                            .map(videoEntity,VideoViewModel.class);
                    videoViewModel.setChapterName(videoEntity.getChapterName().getChapterName());
                    return videoViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override

    public void deleteVideo(String id) {
        VideoEntity targetVideo = this.videoRepository.findById(id).orElse(null);
        this.videoRepository.delete(targetVideo);

    }


}
