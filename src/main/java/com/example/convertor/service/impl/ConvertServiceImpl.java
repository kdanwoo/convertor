package com.example.convertor.service.impl;

import com.example.convertor.service.ConvertSerivce;
import com.example.convertor.service.PdfService;
import com.example.convertor.service.TemplateService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class ConvertServiceImpl implements ConvertSerivce {

    private PdfService pdfService;
    private TemplateService templateService;

    @Override
    public void convertPDF(Map<String, Object> datas) throws IOException, DocumentException {

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

            pdfService.createPdf(htmlToString,realFileName,password);
        }



    }
}
