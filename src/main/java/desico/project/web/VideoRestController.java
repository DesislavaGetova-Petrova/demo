package desico.project.web;
import java.util.List;

import desico.project.model.view.VideoViewModel;
import desico.project.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/video/viewAll")
@RestController
public class VideoRestController {
    private final VideoService videoService;

    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;    }


    @GetMapping("/api")
    public ResponseEntity<List<VideoViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(videoService.findAllVideos());
    }
}
