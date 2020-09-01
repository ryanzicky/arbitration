package com.example.demo.dao.request;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhourui
 * @Date 2020/4/28 9:54
 */
@Data
@ExcelTarget("indictmentModel")
public class IndictmentModel {

    @Excel(name = "原告名称", orderNum = "0")
    private String demandantName;
    @Excel(name = "统一社会信用代码", orderNum = "1")
    private String socialCreditCode;
    @Excel(name = "负责人", orderNum = "2")
    private String charge;
    @Excel(name = "原告地址", orderNum = "3")
    private String demandantAddr;
    @Excel(name = "被告名称", orderNum = "4")
    private String accusedName;
    @Excel(name = "身份证号", orderNum = "5")
    private String idCard;
    @Excel(name = "被告户籍地地址", orderNum = "6")
    private String accusedCardAddr;
    @Excel(name = "贷款合同约定地址", orderNum = "7")
    private String contractAddr;
    @Excel(name = "被告手机", orderNum = "8")
    private String accusedPhone;
    @Excel(name = "理赔金额", orderNum = "9")
    private BigDecimal claimAmount;
    @Excel(name = "逾期日期", orderNum = "10")
    private Date overdueDate;
    @Excel(name = "理赔日期", orderNum = "11")
    private Date claimDate;
    @Excel(name = "拖欠保费金额", orderNum = "12")
    private BigDecimal arrearAmount;
    @Excel(name = "违约金金额", orderNum = "13")
    private BigDecimal liquidatedDamagesAmount;
    @Excel(name = "违约金起算日期", orderNum = "14")
    private Date liquidatedDamagesDate;
    @Excel(name = "起诉日期", orderNum = "15")
    private Date sueDate;
    @Excel(name = "暂计金额", orderNum = "16")
    private BigDecimal suspenseAmount;
    @Excel(name = "贷款日期", orderNum = "17")
    private Date loanDate;
    @Excel(name = "贷款人名称", orderNum = "18")
    private String loanerName;
    @Excel(name = "贷款人名称简称", orderNum = "19")
    private String loanerAbbreviated;
    @Excel(name = "贷款合同名称", orderNum = "20")
    private String loanContractName;
    @Excel(name = "贷款金额", orderNum = "21")
    private BigDecimal loanAmount;
    @Excel(name = "利率上浮比", orderNum = "22")
    private String floatingRatioRate;
    @Excel(name = "贷款利率", orderNum = "23")
    private String loanRate;
    @Excel(name = "保险金额", orderNum = "24")
    private BigDecimal insureAmount;
    @Excel(name = "每月应还保费金额", orderNum = "25")
    private BigDecimal monthReturnAmount;
    @Excel(name = "贷款人所在地", orderNum = "26")
    private String loanerAddr;
    @Excel(name = "起诉法院", orderNum = "27")
    private String courtOfSue;
    @Excel(name = "分公司名称", orderNum = "28")
    private String branchOfficeName;
    @Excel(name = "函号", orderNum = "29")
    private String contractNo;
    @Excel(name = "保费逾期日期", orderNum = "30")
    private Date insureSudDate;
    @Excel(name = "年利率", orderNum = "31")
    private String yearRate;
    @Excel(name = "贷款发放日期", orderNum = "32")
    private Date loanSendDate;
    @Excel(name = "特别约定", orderNum = "33")
    private String specialAgreement;

}
