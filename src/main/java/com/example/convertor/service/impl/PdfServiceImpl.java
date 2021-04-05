package com.example.convertor.service.impl;

import com.example.convertor.service.PdfService;
import com.example.convertor.utils.PdfGenerator;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private PdfGenerator pdfGenerator;


    @Async("asyncPdf")
    @Override
    public void createPdf(String filePath, String fileName, Map<String, Object> variableMap, String realFileName,String password) throws IOException, DocumentException {
        log.info("create Pdf : {}", realFileName);
        //지정한 html 파일과 context를 읽어 String으로 변환한다.
        Context context = new Context();
        context.setVariables(variableMap);
        String htmlToString = templateEngine.process(fileName,context);
        pdfGenerator.generateFromHtml(filePath,realFileName,htmlToString,password);

    }
}
