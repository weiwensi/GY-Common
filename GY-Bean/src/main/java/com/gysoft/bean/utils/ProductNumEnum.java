package com.gysoft.bean.utils;

import java.io.Serializable;

/**
 * @author 周宁
 * @Date 2018-05-23 20:33
 */
public enum ProductNumEnum implements BaseEnum<String, String>, Serializable {

    DCP_DESIGN_UNIT("GY-001", "设计单位"), DCP_CONSTRUCT_UNIT("GY-002", "施工单位"), DCP_SUPERVISION_UNIT("GY-003", "监理单位"), DCP_AUDIT_UNIT("GY-004", "审计单位"),
    DCP_CENTER("GY-005", "企业后台"), DCP_WEB_ENTER("GY-006", "Web数据录入端"), FAP_DESIGN_UNIT("GY-007", "设计单位"), FAP_AUDIT_UNIT("GY-008", "审核单位"),
    FAP_OWNER_UNIT("GY-009", "业主单位"), FAP_CENTER("GY-010", "图审后台"), FAP_APP_ANDROID("GY-011", "图审APP(android)"), REVIT("GY-012", "revit端"), FAP_APP_APPLE("GY-013", "图审APP(apple)"),
    CO_REVIT("GY-014", "MAX-BIM协同平台(Revit版)"), CO_PC("GY-015", "MAX-BIM协同平台PC端"), CO_APP_ANDROID("GY-016", "MAX-BIM协同平台APP(android)"), CO_APP_APPLE("GY-017", "MAX-BIM协同平台APP(ios)"),
    CO_CENTER("GY-018", "MAX-BIM协同平台center后台");

    String key;

    String name;

    ProductNumEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return name;
    }

}
