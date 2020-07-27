package com.example.demo.util;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zhourui
 * @Date 2019/9/30 14:17
 */
@Slf4j
public class PDFUtil {

    public static void handleTtemplate(Map<String, String> params, String path, String fileName, String fontPath) {
        String filepath = path + File.separator + "templates/template.docx";
        String name = path + File.separator + "files" + File.separator + fileName;
        String outpath = name + ".pdf";
        PdfOptions options = PdfOptions.create();
        // 中文字体处理
        options.fontProvider(new IFontProvider() {
            public Font getFont(String familyName, String encoding, float size, int style, Color color) {
                try {
                    // 仿宋字体
                    BaseFont bfChinese = BaseFont.createFont(fontPath + File.separator +  "simfang.ttf",
                            BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                    Font fontChinese = new Font(bfChinese, size, style, color);
                    if (familyName != null)
                        fontChinese.setFamily(familyName);
                    return fontChinese;
                } catch (Exception e) {
                    log.error("pdf生成中文字体失败!, {}", fileName, e);
                    return null;
                }
            }
        });
        OutputStream target = null;
        try (FileInputStream source = new FileInputStream(filepath)) {
            target = new FileOutputStream(outpath);
            wordConverterToPdf(source, target, options, params, name);
            pdfToPng(path, fileName, "png");
        } catch (Exception e) {
            log.error("获取文件流失败!, {}", e);
            throw new RuntimeException();
        } finally {
            if (null != target) {
                try {
                    target.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @throws Exception
     */
    public static void wordConverterToPdf(FileInputStream source,
                                          OutputStream target, Map<String, String> params, String name) throws Exception {
        wordConverterToPdf(source, target, null, params, name);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param target 目标输出
     * @param params 需要替换的变量
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    public static void wordConverterToPdf(FileInputStream source, OutputStream target,
                                          PdfOptions options,
                                          Map<String, String> params, String name) throws Exception {
        try {
            XWPFDocument doc = new XWPFDocument(source);
            paragraphReplace(doc.getParagraphs(), params);
//            addHeader(doc, "广 东 睦 信 律 师 事 务 所");
//            makeWordFile(doc, name);
            PdfConverter.getInstance().convert(doc, target, options);
        } catch (Exception e) {
            log.error("生成pdf失败!, {}", e);
        } finally {
            target.close();
        }
    }

    /**
     * 生成word文档
     *
     * @Author zhourui
     */
    private static void makeWordFile(XWPFDocument doc, String name) {
        try {
            FileOutputStream os = new FileOutputStream(name + ".docx");
            doc.write(os);
        } catch (IOException e) {
            log.error("生成word失败!, {}", e);
        } finally {
            try {
                doc.close();
            } catch (IOException e) {
                log.error("关闭word流失败!, {}", e);
            }
        }
    }

    private static String getHeaderStr(String header) {
        StringBuilder builder = new StringBuilder();
        int num1 = (18 - header.length()) / 2;
        for (int i = 0; i < num1; i++) {
            builder.append(" ");
        }
        builder.append(header);
        for (int i = 0; i < num1; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private static String getOtherStr(String header) {
        StringBuilder builder = new StringBuilder();
        int num1 = (18 - header.length()) / 2;
        for (int i = 0; i < num1; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 添加页眉
     *
     * @Author zhourui
     */
    public static XWPFDocument addHeader(XWPFDocument doc, String header) {
        try {
            CTP ctP1 = CTP.Factory.newInstance();

            CTR ctR1 = ctP1.addNewR();
            CTText t = ctR1.addNewT();
            t.setStringValue(getHeaderStr(header));
//            t.setStringValue("       " + header + "       ");

            /*CTR ctR2 = ctP1.addNewR();
            CTText t1 = ctR2.addNewT();
            t1.setStringValue("       " + getHeaderStr("GUANGDONG MUXIN LAWFIRM") + "       ");*/

            XWPFParagraph codePara = new XWPFParagraph(ctP1, doc);
            XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
            newparagraphs[0] = codePara;
            CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
            for (XWPFParagraph paragraph : newparagraphs) {
                paragraph.setFontAlignment(2); // 字体对齐方式：1左对齐，2居中，3右对齐
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
//                    run.setUnderline(UnderlinePatterns.SINGLE);// 下横线
                    run.setFontSize(22); // 字体大小
                    run.setColor("CED2D2"); // 字体颜色
                    run.setTextPosition(20);
//                    run.setText(getHeaderStr(header));
//                    run.setFontFamily("方正舒体", XWPFRun.FontCharRange.eastAsia); // 字体类型
//                    run.setItalic(true); // 是否斜体

                }
            }
            headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, newparagraphs);


        } catch (IOException e) {
            log.error("添加页眉失败!, {}", e);
        }
        return doc;
    }

    /** 替换段落中内容 */
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            Matcher matcher;
            for (XWPFParagraph p : paragraphs){
                if (matcher(p.getParagraphText()).find()) {
                    for (int i = 0; i < p.getRuns().size(); i++) {
                        XWPFRun run = p.getRuns().get(i);
                        String runText = run.toString();
                        matcher = matcher(runText);
                        if (matcher.find()) {
                            if(StringUtils.isNotBlank(matcher.group(1)) && params.containsKey(matcher.group(1))) {
                                run.setText(params.get(matcher.group(1)), 0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 替换变量正则表达式
     *
     * @Author zhourui
     */
    private static Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * pdf转图片
     *
     * @Author zhourui
     */
    private static void pdfToPng(String path, String fileName, String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(path + File.separator + "files" + File.separator + fileName + ".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            if (pageCount == 1) {
                BufferedImage image = renderer.renderImageWithDPI(0, 296); // Windows native DPI
                ImageIO.write(image, type, new File(path + File.separator + "images" + File.separator + fileName + "."+type));
            } else {
                for (int i = 0; i < pageCount; i++) {
                    BufferedImage image = renderer.renderImageWithDPI(i, 296); // Windows native DPI
                    ImageIO.write(image, type, new File(path + File.separator + "images" + File.separator + fileName +"_" + (i + 1) + "."+type));
                }
            }
            doc.close();
        } catch (IOException e) {
            log.error("pdf转图片失败!, {}", fileName, e);
            throw new RuntimeException();
        }
    }
}
