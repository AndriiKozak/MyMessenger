package com.mycompany.mymessenger.controller;

import com.mycompany.mymessenger.service.AttachmentService;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attachments")
public class AttachementsController {

    @Autowired
    AttachmentService attachementServise;

    @RequestMapping(value = "/{path}/{extension}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<File> getFile(@PathVariable String path, @PathVariable String extension) {
        File file = attachementServise.get(path + '.' + extension);
        return ResponseEntity.ok().header("Content-Disposition", "attachment;filename=" + path + "." + extension).body(file);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> upploadFile(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        String path = attachementServise.post(file);
        return ResponseEntity.created(new URI("/attachments/" + path)).build();
    }
}
