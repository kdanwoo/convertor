package com.example.convertor.service.impl;

import com.example.convertor.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String htmlToString(Map<String, Object> variableMap,String fileName) {
        Context context = new Context();
        context.setVariables(variableMap);

        String htmlToString = templateEngine.process(fileName,context);

        return htmlToString;
    }
}
