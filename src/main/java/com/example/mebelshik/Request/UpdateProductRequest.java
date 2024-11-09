package com.example.mebelshik.Request;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private Float price;
    private Long categoryId;
}