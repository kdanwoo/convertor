package com.example.convertor.service;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Map;

public interface ConvertSerivce {
    void convertPDF(Map<String, Object> datas) throws IOException, DocumentException;
}
