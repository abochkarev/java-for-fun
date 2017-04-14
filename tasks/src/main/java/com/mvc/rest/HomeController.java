package com.mvc.rest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private AmazonS3Client s3Client;

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public String home() {
        return "Welcome to aws prototype.";
    }

    @RequestMapping(value = "/buckets", method = GET, produces = APPLICATION_JSON_VALUE)
    public List<Bucket> getBuckets() {
        return s3Client.listBuckets();
    }
}