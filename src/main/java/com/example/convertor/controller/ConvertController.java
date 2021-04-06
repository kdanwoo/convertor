package com.example.convertor.controller;

import com.example.convertor.service.ConvertSerivce;
import com.example.convertor.service.PdfService;
import com.example.convertor.service.TemplateService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pdf-convert")
@RestController
public class ConvertController {

    private PdfService pdfService;
    private TemplateService templateService;
    private ConvertSerivce convertSerivce;


    @PostMapping
    public void pdfConvert(@RequestBody Map<String, Object> datas) throws IOException, DocumentException {

        convertSerivce.convertPDF(datas);







    }




}


