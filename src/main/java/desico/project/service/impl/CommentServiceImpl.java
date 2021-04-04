package desico.project.service.impl;

import desico.project.model.entity.CommentEntity;
import desico.project.model.entity.UserEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.view.CommentViewModel;
import desico.project.repository.CommentRepository;
import desico.project.service.CommentService;
import desico.project.service.UserService;
import desico.project.service.VideoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final VideoService videoService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, VideoService videoService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.videoService = videoService;
    }


    @Override
    public void createComment(CommentServiceModel commentServiceModel) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity userEntity = userService.findByName(username);
        VideoEntity videoEntity=videoService.findByVideoName(commentServiceModel.getVideoEntity());


        CommentEntity commentEntity = new CommentEntity()

                .setUserEntity(userEntity)
                .setVideoEntity(videoEntity)
                .setDateTime(LocalDateTime.now())
                .setTextContent(commentServiceModel.getTextContent());;

        commentRepository.save(commentEntity);

    }

    @Override
    public List<CommentViewModel> findAllComments() {
        return commentRepository
                .findAll()
                .stream()
                .map(commentEntity ->  {
                    CommentViewModel commentViewModel = new CommentViewModel();
                    commentViewModel.setAuthor(commentEntity.getUserEntity().getUsername());
                    commentViewModel.setVideo(commentEntity.getVideoEntity().getVideoName());
                    commentViewModel.setDateTime(commentEntity.getDateTime());
                    commentViewModel.setTextContent(commentEntity.getTextContent());
                    return commentViewModel;
                })
                .collect(Collectors.toList());
    }

}
