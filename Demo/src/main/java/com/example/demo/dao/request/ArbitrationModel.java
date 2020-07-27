package com.example.demo.dao.request;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhourui
 * @Date 2020/5/31 10:32
 */
@Data
@ExcelTarget("arbitrationModel")
public class ArbitrationModel {

    @Excel(name = "申请人名称", orderNum = "0")
    private String applicator;
    @Excel(name = "申请人住所地", orderNum = "1")
    private String applicatorAddress;
    @Excel(name = "负责人", orderNum = "2")
    private String charge;
    @Excel(name = "统一社会信用代码", orderNum = "3")
    private String socialCreditCode;
    @Excel(name = "被申请人名称", orderNum = "4")
    private String respondentor;
    @Excel(name = "身份证号码", orderNum = "5")
    private String idCardNo;
    @Excel(name = "被申请人户籍地", orderNum = "6")
    private String respondentCardAddress;
    @Excel(name = "投保人地址", orderNum = "7")
    private String policyHolderAddress;
    @Excel(name = "被申请人电话", orderNum = "8")
    private String respondentorPhone;
    @Excel(name = "被申请人电子邮箱", orderNum = "9")
    private String respondentorEmail;
    @Excel(name = "理赔金额", orderNum = "10")
    private BigDecimal claimAmount;
    @Excel(name = "理赔日期", orderNum = "11")
    private Date claimDate;
    @Excel(name = "报价利率", orderNum = "12")
    private BigDecimal offerRate;
    @Excel(name = "申请日期", orderNum = "13")
    private Date applyDate;
    @Excel(name = "利息", orderNum = "14")
    private BigDecimal interest;
    @Excel(name = "费用总计", orderNum = "15")
    private BigDecimal totalAmount;
    @Excel(name = "合同签订日期", orderNum = "16")
    private Date contractSignDate;
    @Excel(name = "借款日期", orderNum = "17")
    private Date borrowerDate;
    @Excel(name = "出借人", orderNum = "18")
    private String lender;
    @Excel(name = "出借人简称", orderNum = "19")
    private String lenderAbbreviated;
    @Excel(name = "借款合同名称", orderNum = "20")
    private String loanContactName;
    @Excel(name = "合同编号", orderNum = "21")
    private String contatcNo;
    @Excel(name = "贷款金额", orderNum = "22")
    private BigDecimal loanAmount;
    @Excel(name = "贷款用途", orderNum = "23")
    private String loanPurpose;
    @Excel(name = "借款期限", orderNum = "24")
    private Integer loanTerm;
    @Excel(name = "借款年利率", orderNum = "25")
    private BigDecimal loanYearRate;
    @Excel(name = "仲裁委名称", orderNum = "26")
    private String arbitrationName;
    @Excel(name = "保险单号", orderNum = "27")
    private String insuranceNum;
    @Excel(name = "借款到期日", orderNum = "28")
    private Date loanEndDate;
    @Excel(name = "《保险权益转让书》出具日期", orderNum = "29")
    private Date issueDate;
    @Excel(name = "案由", orderNum = "30")
    private String causeOfAction;
    @Excel(name = "申请人送达地址", orderNum = "31")
    private String applyDeliverAddress;
    @Excel(name = "申请人邮箱", orderNum = "32")
    private String applicatorEmail;
    @Excel(name = "函号", orderNum = "33")
    private String contractNo;
}
