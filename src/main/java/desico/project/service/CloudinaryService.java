package desico.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadVideo(MultipartFile multipartFile) throws IOException;
}
