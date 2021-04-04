package desico.project.service;

import desico.project.model.entity.*;
import desico.project.model.enums.UserRole;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.view.CommentViewModel;
import desico.project.model.view.VideoViewModel;
import desico.project.repository.CommentRepository;
import desico.project.service.impl.CommentServiceImpl;
import desico.project.service.impl.VideoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    private CommentServiceImpl commentService;
    @Mock
    CommentRepository mockCommentRepository;
    @Mock
    UserService mockUserService;
    @Mock
    VideoService mockVideoService;

    @BeforeEach
    public  void init() {
        commentService=new CommentServiceImpl(mockCommentRepository,mockUserService,mockVideoService);
    }

    @Test
    public void createCommentTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setDateTime(LocalDateTime.now());
        commentEntity.setTextContent("textttttttttttttt");
        commentEntity.setVideoEntity(video);

        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        CommentServiceModel commentServiceModel=new CommentServiceModel();
        commentServiceModel.setVideoEntity(video.getVideoName())
                .setTextContent(commentEntity.getTextContent())
        ;
        Assertions.assertThrows(
                NullPointerException.class, () -> {
                    commentService.createComment(commentServiceModel);
                }
        );
    }
    @Test
    public void findAllCommentsTest() {
        VideoEntity video= new VideoEntity();
        video.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        video.setVideoName("video");
        video.setVideoUrl("videoUrl");

        UserRole userRole=UserRole.USER;
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRole(userRole);

        UserEntity user=new UserEntity();
        user.setPassword("123456").setEmail("d@d").setUsername("pesho").setRoles(List.of(userRoleEntity));

        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setDateTime(LocalDateTime.now());
        commentEntity.setTextContent("textttttttttttttt");
        commentEntity.setVideoEntity(video);
        commentEntity.setUserEntity(user);

        List<CommentViewModel> commentViewModels = new ArrayList<>();
        CommentViewModel commentViewModel= new CommentViewModel();
        commentViewModel
                .setTextContent("textttttttttttttt")
                .setVideo(video.getVideoName())
                .setAuthor(user.getUsername())
                .setDateTime(LocalDateTime.now());

        commentViewModels.add(commentViewModel);
        Mockito.when(mockCommentRepository.findAll()).thenReturn(List.of(commentEntity));
        Assertions.assertEquals(1,commentService.findAllComments().size());

    }
}
