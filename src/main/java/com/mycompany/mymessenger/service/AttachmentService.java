package com.mycompany.mymessenger.service;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    File get(String path);

    String post(MultipartFile file) throws IOException;
}
