package com.example.convertor.service;

import java.util.Map;

public interface TemplateService {
    String htmlToString(Map<String,Object> map, String fileName);
}
