package com.example.demo.dao.request;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhourui
 * @Date 2020/8/6 9:07
 */
@Data
@ExcelTarget("ContractDisputeModel")
public class ContractDisputeModel {

    @Excel(name = "被告", orderNum = "0")
    private String accusedName;
    @Excel(name = "借款合同名称", orderNum = "1")
    private String contractName;
    @Excel(name = "合同签约时间", orderNum = "2")
    private String contractDate;
    @Excel(name = "贷款人", orderNum = "3")
    private String lender;
    @Excel(name = "借款数额", orderNum = "4")
    private BigDecimal lendAmount;
    @Excel(name = "借款期限", orderNum = "5")
    private String loanTerm;
    @Excel(name = "还款方式", orderNum = "6")
    private String payType;
    @Excel(name = "借款利率", orderNum = "7")
    private BigDecimal loanRate;
    @Excel(name = "罚息约定", orderNum = "8")
    private String penaltyAgreement;
    @Excel(name = "逾期日期", orderNum = "9")
    private String overdueDate;
    @Excel(name = "本金", orderNum = "10")
    private BigDecimal principal;
    @Excel(name = "利息", orderNum = "11")
    private BigDecimal interest;
    @Excel(name = "罚息", orderNum = "12")
    private BigDecimal penalty;
    @Excel(name = "合计", orderNum = "13")
    private BigDecimal amount;
    @Excel(name = "保证保险合同名称", orderNum = "14")
    private String guaranteeContraceName;
    @Excel(name = "保单签约时间", orderNum = "15")
    private String guaranteeContractDate;
    @Excel(name = "投保人", orderNum = "16")
    private String policyHolder;
    @Excel(name = "被保险人", orderNum = "17")
    private String insured;
    @Excel(name = "保险金额", orderNum = "18")
    private BigDecimal insurance;
    @Excel(name = "保险费数额", orderNum = "19")
    private BigDecimal insurancePremium;
    @Excel(name = "保险费付费约定", orderNum = "20")
    private String insurancePremiumAgreement;
    @Excel(name = "保险期间", orderNum = "21")
    private String withinInsurance;
    @Excel(name = "违约责任约定", orderNum = "22")
    private String liabilityOfContract;
    @Excel(name = "赔付时间", orderNum = "23")
    private Date payoutTime;
    @Excel(name = "赔付款", orderNum = "24")
    private BigDecimal compensation;
    @Excel(name = "保险费欠付数额", orderNum = "25")
    private String owedInsurance;
    @Excel(name = "已缴纳保险费", orderNum = "26")
    private BigDecimal paidInsurance;
    @Excel(name = "借款放款账户", orderNum = "27")
    private String loanAccount;


}
