package com.example.mebelshik.Request;

import lombok.Data;

import java.util.List;

@Data
public class FilterCreateRequest {
    private String filterType;
    private Long category_id;
    private List<String> values;
}
