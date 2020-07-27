package com.example.demo.dao.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author zhourui
 * @Date 2019/10/10 11:33
 */
@Builder
@Data
@ApiModel(value = "对象请求类实体")
public class ModelRequest {

    @ApiModelProperty("参数列表")
    List<ImportExampleModel> list;
}
