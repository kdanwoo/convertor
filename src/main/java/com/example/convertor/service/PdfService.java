package com.example.convertor.service;

import com.itextpdf.text.DocumentException;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.IOException;
import java.util.Map;

public interface PdfService {

    void createPdf(String htmlToString,String realFileName,String password) throws IOException, DocumentException;
    void createPdf(String htmlToString,String realFileName) throws IOException, DocumentException;
    PDFEncryption encryptionPDF(String password);

}
