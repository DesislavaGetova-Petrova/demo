package desico.project.service.impl;

import com.cloudinary.Cloudinary;
import desico.project.service.CloudinaryService;


import org.springframework.stereotype.Service;


import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public String uploadVideo(MultipartFile multipartFile) throws IOException {



        File file = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        Map<Object, Object> parameters = new HashMap<>();


        parameters.put("resource_type", "video");

       return  this.cloudinary
                .uploader()
                .upload(file, parameters)
                .get(URL)
                .toString();
    }
}
