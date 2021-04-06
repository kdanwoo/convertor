package com.example.convertor.service.impl;

import com.example.convertor.service.PdfService;
import com.example.convertor.utils.ImageReplaceElementFactory;
import com.example.convertor.utils.PdfGenerator;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Value("${pdf.file-path}")
    private String filePath;

    @Async("asyncPdf")
    @Override
    public void createPdf(String htmlToString, String realFileName,String password) throws IOException, DocumentException {
        String savedFilePath = filePath+"/"+realFileName+".pdf";
        OutputStream outputStream = new FileOutputStream(savedFilePath);

        ClassPathResource fontUrl = new ClassPathResource("/static/font/NanumBarunGothic.ttf");
        ITextRenderer renderer = new ITextRenderer();
        renderer.getSharedContext().setReplacedElementFactory(new ImageReplaceElementFactory(renderer.getSharedContext().getReplacedElementFactory()));
        if(password != null){
            renderer.setPDFEncryption(encryptionPDF(password));
        }
        renderer.setDocumentFromString(htmlToString);
        renderer.getFontResolver().addFont(
                fontUrl.getURL().toString(),
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED
        );
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();
        outputStream.close();
    }

    @Async("asyncPdf")
    @Override
    public void createPdf(String htmlToString, String realFileName) throws IOException, DocumentException {
        String savedFilePath = filePath+"/"+realFileName+".pdf";
        OutputStream outputStream = new FileOutputStream(savedFilePath);

        ClassPathResource fontUrl = new ClassPathResource("/static/font/NanumBarunGothic.ttf");
        ITextRenderer renderer = new ITextRenderer();

        renderer.getSharedContext().setReplacedElementFactory(new ImageReplaceElementFactory(renderer.getSharedContext().getReplacedElementFactory()));
        renderer.setDocumentFromString(htmlToString);
        renderer.getFontResolver().addFont(
                fontUrl.getURL().toString(),
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED
        );
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();
        outputStream.close();
    }

    @Override
    public PDFEncryption encryptionPDF(String password) {
        PDFEncryption pdfEncryption = new PDFEncryption();
        pdfEncryption.setUserPassword(password.getBytes());
        return pdfEncryption;
    }


}
