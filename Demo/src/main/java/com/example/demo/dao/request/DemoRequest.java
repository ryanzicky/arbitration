package com.example.demo.dao.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Author zhourui
 * @Date 2019/9/29 14:50
 */
@Builder
@Data
@ApiModel("请求类实体")
public class DemoRequest {

    @ApiModelProperty("公司名称")
    private String compName;
    @ApiModelProperty("公司地址")
    private String address;
    @ApiModelProperty("法定代表人")
    private String legalName;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("落款人")
    private String name;
    @ApiModelProperty("日期")
    private String date;
}
