package com.example.convertor.utils;

import com.itextpdf.text.Image;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageReplaceElementFactory implements  ReplacedElementFactory {

    private final ReplacedElementFactory superFactory;

    public ImageReplaceElementFactory(ReplacedElementFactory superFactory) {
        this.superFactory = superFactory;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box, UserAgentCallback uac, int cssWidth, int cssHeight) {

        Element element = box.getElement();
        if (element == null) {
            return null;
        }
        String nodeName = element.getNodeName();
        String srcPath  = element.getAttribute("src");

        // img 노드이면서 image의 경로(src)가 /image로 시작하는 노드를 필터링한다.

        if ("img".equals(nodeName) && srcPath.contains("/image")) {
            InputStream input = null;
            try {
                 input = this.getClass().getResourceAsStream(element.getAttribute("src"));
                final byte[] bytes = IOUtils.toByteArray(input);
                final Image image = Image.getInstance(bytes);
                final FSImage fsImage = new ITextFSImage(image);
                if (fsImage != null) {
                    if ((cssWidth != -1) || (cssHeight != -1)) {
                        fsImage.scale(cssWidth, cssHeight);
                    }
                    return new ITextImageElement(fsImage);
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(input);
            }
        }
        return this.superFactory.createReplacedElement(c, box, uac, cssWidth, cssHeight);

    }



    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }
}
