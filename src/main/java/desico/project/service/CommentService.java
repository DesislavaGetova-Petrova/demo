package desico.project.service;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    void createComment(CommentServiceModel commentServiceModel);
    List<CommentViewModel> findAllComments();
}
