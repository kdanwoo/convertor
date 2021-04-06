package com.example.controller;

import com.example.convertor.service.PdfService;
import com.example.convertor.service.TemplateService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pdf-convert")
@RestController
public class ConvertController {

    private PdfService pdfService;
    private TemplateService templateService;


    @PostMapping
    public void pdfConvert(Map<String, Object> datas) throws IOException, DocumentException {

        String htmlToString = null;
        String realFileName = null;
        String password = null;
        Map<String, Object> map;

        if(datas!=null){
            //변수 Mapping convert html to String
            if(datas.get("variableMap")!=null) {
                map = (Map<String, Object>) datas.get("variableMap");
                String fileName = (String) datas.get("fileName");
                htmlToString = templateService.htmlToString(map, fileName);
            }

            realFileName = (String) datas.get("realFileName");
            password = (String) datas.get("password");

        }

        if(password!=null && password.length()>0){
            pdfService.createPdf(htmlToString,realFileName,password);
        }else{
            pdfService.createPdf(htmlToString,realFileName);
        }



    }




}


