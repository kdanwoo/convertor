package com.example.convertor.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.*;

@Configuration
public class PdfGenerator {

    public void generateFromHtml(String filePath,String realFileName, String htmlToString) throws IOException, DocumentException {
        this.generateFromHtml( filePath, realFileName, htmlToString, null);
    }

    public void generateFromHtml(String filePath,String realFileName, String htmlToString,String password) throws IOException, DocumentException {

        String savedFilePath = filePath+"/"+realFileName+".pdf";
        OutputStream outputStream = new FileOutputStream(savedFilePath);

        ClassPathResource fontUrl = new ClassPathResource("/static/font/NanumBarunGothic.ttf");
        ITextRenderer renderer = new ITextRenderer();

        renderer.getSharedContext().setReplacedElementFactory(new ImageReplaceElementFactory(renderer.getSharedContext().getReplacedElementFactory()));
        if(password != null){
            renderer.setPDFEncryption(pdfEncryption(password));
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
    
    //pdf 암호화 객체
    public PDFEncryption pdfEncryption(String password){
        PDFEncryption pdfEncryption = new PDFEncryption();
        pdfEncryption.setUserPassword(password.getBytes());
        return pdfEncryption;
    }


}
