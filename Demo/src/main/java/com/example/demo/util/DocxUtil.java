package com.example.demo.util;

import com.example.demo.dao.request.DemoRequest;
import com.example.demo.dao.request.IndictmentModel;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Docx工具类
 *
 * @Author zhourui
 * @Date 2019/9/29 15:46
 */
@Slf4j
public class DocxUtil {

    public static void main(String[] args){
        String path = "templates/template1222222.docx";
        DemoRequest request = DemoRequest.builder().address("111")
                .compName("222")
                .content("dqweqweq")
                .legalName("333")
                .name("4444")
                .date("2019年9月29日")
                .build();
        File file = handleWordFile(request, path);
    }

    /**
     * 根据模版写入信息
     *
     * @Author zhourui
     */
    private static XWPFDocument writeWordContent(Map<String, Object> map, String path) {
        XWPFDocument doc = null;

        ClassPathResource classPathResource = new ClassPathResource(path);
        try (InputStream in = classPathResource.getInputStream()) {
            doc = new XWPFDocument(in);
            replaceInDoc(doc, map);
        } catch (IOException e) {
            log.error("读取模版文件失败!,{}", e);
        }
        return doc;
    }

    private static void makeFile(String fileName, String path, Map<String, Object> map, String templatePath) {
        String fileDir = path + "/civilfiles/";
        if (fileName.contains("原告提供被告的送达地址")) {
            fileDir = fileDir + "1/";
            new File(WordUtil.createWord(templatePath + "accusedAddress.docx", fileDir, fileName, map));
        } else if (fileName.contains("诚信诉讼承诺书")) {
            fileDir = fileDir + "2/";
            new File(WordUtil.createWord(templatePath + "goodFaithLitigation.docx", fileDir, fileName, map));
        } else if (fileName.contains("当事人电子邮件送达确认书")) {
            fileDir = fileDir + "3/";
            new File(WordUtil.createWord(templatePath + "emailAddress.docx", fileDir, fileName, map));
        } else if (fileName.contains("对方当事人送达地址及相关信息确认书")) {
            fileDir = fileDir + "4/";
            new File(WordUtil.createWord(templatePath + "addressAndInfo.docx", fileDir, fileName, map));
        } else if (fileName.contains("诉讼文书送达地址确认书")) {
            fileDir = fileDir + "5/";
            new File(WordUtil.createWord(templatePath + "lawsuitFileAddress.docx", fileDir, fileName, map));
        } else if (fileName.contains("民事起诉状")) {
            fileDir = fileDir + "6/";
            new File(WordUtil.createWord(templatePath + "civiltemplate.docx", fileDir, fileName, map));
        } else if (fileName.contains("授权委托书")) {
            fileDir = fileDir + "7/";
            new File(WordUtil.createWord(templatePath + "delegationtemplate.docx", fileDir, fileName, map));
        } else if (fileName.contains("所函")) {
            fileDir = fileDir + "8/";
            new File(WordUtil.createWord(templatePath + "lawofficetemplate.docx", fileDir, fileName, map));
        }
    }

    public static void makeArbrationFile(Map<String, Object> map, String path) {
        String templatePath = path + "/templates/4/";
        String fileDir = path + "/arbrationfiles/";
        String respondentor = (String) map.get("respondentor");
        String insuranceNum = (String) map.get("insuranceNum");
        new File(WordUtil.createWord(templatePath + "addressAndInfo.docx", fileDir + "1/", respondentor + "-" + insuranceNum + "-当事人送达地址确认单", map));
        new File(WordUtil.createWord(templatePath + "civiltemplate.docx", fileDir + "2/", respondentor + "-" + insuranceNum + "-仲裁申请书", map));
        new File(WordUtil.createWord(templatePath + "delegationtemplate.docx", fileDir + "3/", respondentor + "-" + insuranceNum + "-授权委托书", map));
        new File(WordUtil.createWord(templatePath + "lawofficetemplate.docx", fileDir + "4/", respondentor + "-" + insuranceNum + "-所函", map));
        new File(WordUtil.createWord(templatePath + "accusedAddress.docx", fileDir + "5/", respondentor + "-" + insuranceNum + "-原告提供被告的送达地址", map));
    }

    /**
     * 操作word文件
     *
     * @Author zhourui
     */
    public static void makeSueWordFile(Map<String, Object> map, String path) {
        String accusedName = (String) map.get("accusedName");
        String branchOfficeName = (String) map.get("branchOfficeName");
        String templatePath = path + "/templates/";
        if (branchOfficeName.equals("东莞市分公司")) {
            templatePath = templatePath + "1/";
            makeFile(branchOfficeName + "-" + accusedName + "-原告提供被告的送达地址", path, map, templatePath);
        }else if (branchOfficeName.equals("广州市分公司")) {
            templatePath = templatePath + "2/";
            makeFile(branchOfficeName + "-" + accusedName + "-诚信诉讼承诺书", path, map, templatePath);
            makeFile(branchOfficeName + "-" + accusedName + "-当事人电子邮件送达确认书", path, map, templatePath);
            makeFile(branchOfficeName + "-" + accusedName + "-对方当事人送达地址及相关信息确认书", path, map, templatePath);
            makeFile(branchOfficeName + "-" + accusedName + "-诉讼文书送达地址确认书", path, map, templatePath);
        }else if (branchOfficeName.equals("惠州市分公司")) {
            templatePath = templatePath + "3/";
            makeFile(branchOfficeName + "-" + accusedName + "-当事人电子邮件送达确认书", path, map, templatePath);
            makeFile(branchOfficeName + "-" + accusedName + "-对方当事人送达地址及相关信息确认书", path, map, templatePath);
            makeFile(branchOfficeName + "-" + accusedName + "-诉讼文书送达地址确认书", path, map, templatePath);
        }
        makeFile(branchOfficeName + "-" + accusedName + "-民事起诉状", path, map, templatePath);
        makeFile(branchOfficeName + "-" + accusedName + "-授权委托书", path, map, templatePath);
        makeFile(branchOfficeName + "-" + accusedName + "-所函", path, map, templatePath);
    }

    /**
     * 操作word文件
     *
     * @Author zhourui
     */
    /*public static File makeSueWordFile(Map<String, Object> map, Integer type, String path) {
        File file = null;
        String contractNo = (String) map.get("contractNo");
        path = path + "/src/main/resources";
        String filePath = "";
        switch (type) {
            case 1:
                filePath = path + "/civiltemplate.docx";
                break;
            default:
                break;
        }
        XWPFDocument doc = writeWordContent(map, filePath);
        file = new File(path + "/templates/files/" +  contractNo +  ".docx");
        try (OutputStream os = new FileOutputStream(file)) {
            doc.write(os);
        } catch (IOException e) {
            log.info("将信息写入文件失败!,{}", e);
            throw new RuntimeException();
        }
        return file;
    }*/

    /**
     * 操作word文件
     *
     * @Author zhourui
     */
    public static File handleWordFile(DemoRequest request, String path) {
        File file = null;
        XWPFDocument doc = writeWordContent(request, "templates/template1222222.docx");
        file = new File(path + File.separator + System.currentTimeMillis() +  ".docx");
        try (OutputStream os = new FileOutputStream(file)) {
            doc.write(os);
        } catch (IOException e) {
            log.info("将信息写入文件失败!,{}", e);
            throw new RuntimeException();
        }
        return file;
    }

    /**
     * 根据模版写入信息
     *
     * @Author zhourui
     */
    private static XWPFDocument writeWordContent(DemoRequest request, String path) {
        XWPFDocument doc = null;
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("compName", request.getCompName());
        paramMap.put("address", request.getAddress());
        paramMap.put("legalName", request.getLegalName());
        paramMap.put("content", request.getContent());
        paramMap.put("name", request.getName());
        paramMap.put("date", request.getDate());

        ClassPathResource classPathResource = new ClassPathResource(path);
        try (InputStream in = classPathResource.getInputStream()) {
            doc = new XWPFDocument(in);
            replaceInDoc(doc, paramMap);
        } catch (IOException e) {
            log.error("读取模版文件失败!,{}", e);
        }
        return doc;
    }

    /**
     * 替换段落里的变量
     *
     * @Author zhourui
     */
    private static void replaceInDoc(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            replaceInPara(para, params);
        }
    }

    /**
     * 替换段落里面的变量
     * @param para 要替换的段落
     * @param params 参数     */
    private static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                matcher = matcher(runText);
                if (matcher.find()) {
                    while ((matcher = matcher(runText)).find()) {
                        runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                    }
                    para.removeRun(i);
                    para.insertNewRun(i).setText(runText);
                    log.info("para.insertNewRun(i).setText(runText) = {}", runText);
                }
            }
        }
    }

    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private static Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    public static void makeContractDisputeFile(Map<String, Object> map, String path) {
        String templatePath = path + "/templates/5/";
        String fileDir = path + "/contractDispute/";
        String accusedName = (String) map.get("accusedName");
        new File(WordUtil.createWord(templatePath + "cover1.docx", fileDir + "1/", accusedName + "-民事案件封面(正卷)", map));
        new File(WordUtil.createWord(templatePath + "cover2.docx", fileDir + "2/", accusedName + "-民事案件封面(副卷)", map));
        new File(WordUtil.createWord(templatePath + "contractDispute.docx", fileDir + "3/", accusedName + "-保证保险合同纠纷要素表", map));
    }
}
