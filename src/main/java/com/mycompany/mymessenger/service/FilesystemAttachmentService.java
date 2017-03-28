package com.mycompany.mymessenger.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesystemAttachmentService implements AttachmentService {

    private static final String ATTACHMENTS_DESTINATION = "/files/";
    @Autowired
    private HttpServletRequest request;

    @Override
    public File get(String filename) {
        return new File(request.getServletContext().getRealPath(ATTACHMENTS_DESTINATION) + filename);
    }

    @Override
    public String post(MultipartFile file) throws IOException {
        String filename = generateFilename(file);
        File dest = new File(request.getServletContext().getRealPath(ATTACHMENTS_DESTINATION) + filename);
        if (!dest.exists()) {
            dest.getParentFile().mkdirs();
            file.transferTo(dest);
        }
        return filename;
    }

    private String generateFilename(MultipartFile file) throws IOException {
        String extension = getExstension(file.getOriginalFilename());
        String name = DigestUtils.md5DigestAsHex(file.getBytes());
        return name + extension;
    }

    private String getExstension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }
}
