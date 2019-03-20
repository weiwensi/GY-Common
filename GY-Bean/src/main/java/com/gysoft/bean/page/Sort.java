package com.gysoft.bean.page;

import com.gysoft.bean.utils.TypeFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 周宁
 * @date 2018/4/10 15:32
 */
@Data
@NoArgsConstructor
public class Sort implements Serializable {

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private String sortField;
    /**
     * 排序类型(DESC降序，ASC升序)
     */
    @ApiModelProperty("排序类型(DESC降序，ASC升序)")
    private String sortType = "DESC";

    public Sort(String sortField) {
        this.sortField = sortField;
    }

    public <T, R> Sort(TypeFunction<T, R> function) {
        this.sortField = TypeFunction.getLambdaColumnName(function);
    }

    public Sort(String sortField, String sortType) {
        this.sortField = sortField;
        this.sortType = sortType;
    }

    public <T, R> Sort(TypeFunction<T, R> function, String sortType) {
        this.sortField = TypeFunction.getLambdaColumnName(function);
        this.sortType = sortType;
    }
}
