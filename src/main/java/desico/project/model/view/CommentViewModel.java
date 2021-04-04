package desico.project.model.view;
import java.time.LocalDateTime;

public class CommentViewModel {
    private String textContent;
    private String author;
    private String video;
    private LocalDateTime dateTime;

    public CommentViewModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentViewModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public CommentViewModel setVideo(String video) {
        this.video = video;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CommentViewModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
