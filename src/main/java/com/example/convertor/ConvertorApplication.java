package com.example.convertor;

import com.example.convertor.service.PdfService;
import com.example.convertor.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class ConvertorApplication {


    @Autowired
    private PdfService pdfService;

    @Autowired
    private TemplateService templateService;

    public static void main(String[] args) {
        SpringApplication.run(ConvertorApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("Create PDF");


        //PDF로 변환시킬 .html 파일 이름
        String fileName = "giant_peng";

        String realFileName = "giant_peng1111";

        String htmlToString = null;

        //PDF 암호화시 설정할 암호
        String password = "gurtls414!";

        //Template에 binding되는 변수.
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("name","펭수");
        variableMap.put("age",10);
        variableMap.put("job","EBS");
        variableMap.put("address","abc");

        if(variableMap != null){
            htmlToString = templateService.htmlToString(variableMap,fileName);
        }
        pdfService.createPdf(htmlToString,realFileName,password);


        stopWatch.stop();
        log.info("수행시간 >> {}", stopWatch.getTotalTimeSeconds());  // 수행시간 >> 5.866

    }*/
}
