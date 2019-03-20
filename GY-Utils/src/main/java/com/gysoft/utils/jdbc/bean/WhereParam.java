package com.gysoft.utils.jdbc.bean;

import com.gysoft.utils.util.EmptyUtils;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 *@author 周宁
 *@date 2018/8/28 13:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhereParam {

    private String key;
    private String opt;
    private Object value;

    public static boolean isValid(Collection<WhereParam> whereParams){
        boolean isValid = false;
        if(CollectionUtils.isNotEmpty(whereParams)){
            for(WhereParam whereParam : whereParams){
                if(EmptyUtils.isNotEmpty(whereParam.getKey())){
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }

    public boolean isValid(){
        return isValid(Arrays.asList(this));
    }
}