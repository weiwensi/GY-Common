package com.gysoft.utils.jdbc.bean;

/**
 * @author 周宁
 */
public enum JoinType {
    LeftJoin("LEFT JOIN"),
    RightJoin("RIGHT JOIN"),
    InnerJoin("INNER JOIN"),
    NatureJoin(",");
    private final String type;

    JoinType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
