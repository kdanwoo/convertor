package com.example.convertor.service;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Map;

public interface PdfService {

    void createPdf(String filePath,String fileName, Map<String,Object> variableMap,String realFileName,String password) throws IOException, DocumentException;


}
