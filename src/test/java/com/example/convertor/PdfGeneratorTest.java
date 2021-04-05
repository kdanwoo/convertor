package com.example.convertor;

import com.example.convertor.utils.PdfGenerator;
import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PdfGeneratorTest {


    //private ThymeleafParser thymeleafParser;
    private PdfGenerator pdfGenerator;

    @Test
    public void pdfConvert() throws IOException, DocumentException {

        Map<String, Object> variableMap = new HashMap<>();

        variableMap.put("name","펭수");
        variableMap.put("age",10);
        variableMap.put("job","EBS");
        variableMap.put("address","abc");

        //쪼개~
       // String html = thymeleafParser.parseHtmlFileToString("giant_peng", variableMap);
        //pdfGenerator.generateFromHtml("/Users/kdanw/pdf","giant_peng",html,"1234567");

    }

    @Test
    public void pdfConvert2(){

    }


}
