package com.gysoft.bean.syslog;

import com.gysoft.bean.utils.ProductNumEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 周宁
 */
public interface SysLogConstant {
    /**
     * 系统日志消息队列
     */
    String SYS_LOG_QUEUE = "sys_log_queue";
    /**
     * CENTER_SYS_LOG_ROUTING_KEY
     * 系统日志消息队列绑定交换机
     */
    String SYS_LOG_EXCHANGE = "sys_log_exchange";
    /**
     * DCP-Center系统日志绑定路由
     */
    String DCP_CENTER_SYS_LOG_ROUTING_KEY = "dcp_center_sys_log_routing_key";
    /**
     * DCP-WebEnter 统日志绑定路由
     */
    String DCP_WEBENTER_SYS_LOG_ROUTING_KEY = "dcp_webenter_sys_log_routing_key";
    /**
     * DCP施工端消息队列路由
     */
    String DCP_CONSTRUCT_SYS_LOG_ROUTING_KEY = "dcp_construct_sys_log_routing_key";
    /**
     * DCP设计端消息队列路由
     */
    String DCP_DESIGN_SYS_LOG_ROUTING_KEY = "dcp_design_sys_log_routing_key";
    /**
     * DCP监理端消息队列路由
     */
    String DCP_SUPERVISION_SYS_LOG_ROUTING_KEY = "dcp_supervision_sys_log_routing_key";
    /**
     * DCP审计端消息队列路由
     */
    String DCP_AUDIT_SYS_LOG_ROUTING_KEY = "dcp_audit_sys_log_routing_key";
    /**
     * FAP安卓版App消息队列路由
     */
    String FAP_APP_ANDROID_SYS_LOG_ROUTING_KEY = "fap_app_android_sys_log_routing_key";
    /**
     * FAP苹果版App消息队列路由
     */
    String FAP_APP_APPLE_SYS_LOG_ROUTING_KEY = "fap_app_apple_sys_log_routing_key";
    /**
     * CO-center消息队列路由
     */
    String CO_CENTER_SYS_LOG_ROUTING_KEY = "co_center_sys_log_routing_key";
    /**
     * CO-pc消息队列路由
     */
    String CO_PC_SYS_LOG_ROUTING_KEY = "co_pc_sys_log_routing_key";
    /**
     * CO-revit消息队列路由
     */
    String CO_REVIT_SYS_LOG_ROUTING_KEY = "co_revit_sys_log_routing_key";
    /**
     * CO安卓版App消息队列路由
     */
    String CO_APP_ANDROID_SYS_LOG_ROUTING_KEY = "co_app_android_sys_log_routing_key";
    /**
     * CO苹果版App消息队列路由
     */
    String CO_APP_APPLE_SYS_LOG_ROUTING_KEY = "co_app_apple_sys_log_routing_key";
    /**
     * DCP-Center系统日志消息集合
     */
    String DCP_CENTER_SYS_LOG_COLL_NAME = "dcp.center.sys.log";
    /**
     * DCP-WebEnter系统日志集合
     */
    String DCP_WEBENTER_SYS_LOG_COLL_NAME = "dcp.webenter.sys.log";
    /**
     * DCP设计端系统日志集合
     */
    String DCP_DESIGN_SYS_LOG_COLL_NAME = "dcp.design.sys.log";
    /**
     * DCP施工端系统日志集合
     */
    String DCP_CONSTRUCT_SYS_LOG_COLL_NAME = "dcp.construct.sys.log";
    /**
     * DCP监理端系统日志集合
     */
    String DCP_SUPERVISION_SYS_LOG_COLL_NAME = "dcp.supervision.sys.log";
    /**
     * DCP审计端系统日志集合
     */
    String DCP_AUDIT_SYS_LOG_COLL_NAME = "dcp.audit.sys.log";
    /**
     * FAP设计端消息队列路由
     */
    String FAP_DESIGN_SYS_LOG_ROUTING_KEY = "fap_design_sys_log_routing_key";
    /**
     * FAP审计端消息队列路由
     */
    String FAP_AUDIT_SYS_LOG_ROUTING_KEY = "fap_audit_sys_log_routing_key";
    /**
     * FAP业主端消息队列路由
     */
    String FAP_OWNER_SYS_LOG_ROUTING_KEY = "fap_owner_sys_log_routing_key";
    /**
     * FAP-Center后台消息队列路由
     */
    String FAP_CENTER_SYS_LOG_ROUTING_KEY = "fap_center_sys_log_routing_key";
    /**
     * revit消息队列路由
     */
    String REVIT_SYS_LOG_ROUTING_KEY = "revit_sys_log_routing_key";
    /**
     * FAP设计端系统日志集合
     */
    String FAP_DESIGN_SYS_LOG_COLL_NAME = "fap.design.sys.log";
    /**
     * FAP审计端系统日志集合
     */
    String FAP_AUDIT_SYS_LOG_COLL_NAME = "fap.audit.sys.log";
    /**
     * FAP业主端系统日志集合
     */
    String FAP_OWNER_SYS_LOG_COLL_NAME = "fap.owner.sys.log";
    /**
     * FAP-Center系统日志集合
     */
    String FAP_CENTER_SYS_LOG_COLL_NAME = "fap.center.sys.log";
    /**
     * Revit系统日志集合
     */
    String REVIT_SYS_LOG_COLL_NAME = "revit.sys.log";
    /**
     * FAP_APP安卓系统日志集合
     */
    String FAP_APP_ANDROID_SYS_LOG_COLL_NAME = "fap.app.android.sys.log";
    /**
     * FAP_APP苹果系统日志集合
     */
    String FAP_APP_APPLE_SYS_LOG_COLL_NAME = "fap.app.apple.sys.log";
    /**
     * CO-center系统日志集合
     */
    String CO_CENTER_SYS_LOG_COLL_NAME = "co.center.sys.log";
    /**
     * CO-PC系统日志集合
     */
    String CO_PC_SYS_LOG_COLL_NAME = "co.pc.sys.log";
    /**
     * CO-revit系统日志集合
     */
    String CO_REVIT_SYS_LOG_COLL_NAME = "co.revit.sys.log";
    /**
     * CO安卓版App系统日志集合
     */
    String CO_APP_ANDROID_SYS_LOG_COLL_NAME = "co.app.android.sys.log";
    /**
     * CO苹果版App系统日志集合
     */
    String CO_APP_APPLE_SYS_LOG_COLL_NAME = "co.app.apple.sys.log";

    /**
     * 消息队列routingKey与日志集合名称map
     */
    Map<String, String> ROUTING_KEY_COLL_NAME_MAP = new HashMap() {{
        put(DCP_CENTER_SYS_LOG_ROUTING_KEY, DCP_CENTER_SYS_LOG_COLL_NAME);
        put(DCP_AUDIT_SYS_LOG_ROUTING_KEY, DCP_AUDIT_SYS_LOG_COLL_NAME);
        put(DCP_CONSTRUCT_SYS_LOG_ROUTING_KEY, DCP_CONSTRUCT_SYS_LOG_COLL_NAME);
        put(DCP_DESIGN_SYS_LOG_ROUTING_KEY, DCP_DESIGN_SYS_LOG_COLL_NAME);
        put(DCP_SUPERVISION_SYS_LOG_ROUTING_KEY, DCP_SUPERVISION_SYS_LOG_COLL_NAME);
        put(FAP_DESIGN_SYS_LOG_ROUTING_KEY, FAP_DESIGN_SYS_LOG_COLL_NAME);
        put(FAP_AUDIT_SYS_LOG_ROUTING_KEY, FAP_AUDIT_SYS_LOG_COLL_NAME);
        put(FAP_CENTER_SYS_LOG_ROUTING_KEY, FAP_CENTER_SYS_LOG_COLL_NAME);
        put(FAP_OWNER_SYS_LOG_ROUTING_KEY, FAP_OWNER_SYS_LOG_COLL_NAME);
        put(DCP_WEBENTER_SYS_LOG_ROUTING_KEY, DCP_WEBENTER_SYS_LOG_COLL_NAME);
        put(REVIT_SYS_LOG_ROUTING_KEY, REVIT_SYS_LOG_COLL_NAME);
        put(FAP_APP_ANDROID_SYS_LOG_ROUTING_KEY, FAP_APP_ANDROID_SYS_LOG_COLL_NAME);
        put(FAP_APP_APPLE_SYS_LOG_ROUTING_KEY, FAP_APP_APPLE_SYS_LOG_COLL_NAME);
        put(CO_CENTER_SYS_LOG_ROUTING_KEY, CO_CENTER_SYS_LOG_COLL_NAME);
        put(CO_PC_SYS_LOG_ROUTING_KEY, CO_PC_SYS_LOG_COLL_NAME);
        put(CO_REVIT_SYS_LOG_ROUTING_KEY, CO_REVIT_SYS_LOG_COLL_NAME);
        put(CO_APP_ANDROID_SYS_LOG_ROUTING_KEY, CO_APP_ANDROID_SYS_LOG_COLL_NAME);
        put(CO_APP_APPLE_SYS_LOG_ROUTING_KEY, CO_APP_APPLE_SYS_LOG_COLL_NAME);
    }};

    /**
     * 根据productNum获得集合名称
     *
     * @param productNum
     * @return String
     */
    static String dispatchRoutingKey(String productNum) {
        String routingKey = null;
        if (ProductNumEnum.DCP_DESIGN_UNIT.getKey().equals(productNum)) {

            routingKey = DCP_DESIGN_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.DCP_CONSTRUCT_UNIT.getKey().equals(productNum)) {
            routingKey = DCP_CONSTRUCT_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.DCP_SUPERVISION_UNIT.getKey().equals(productNum)) {
            routingKey = DCP_SUPERVISION_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.DCP_AUDIT_UNIT.getKey().equals(productNum)) {
            routingKey = DCP_AUDIT_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.DCP_CENTER.getKey().equals(productNum)) {
            routingKey = DCP_CENTER_SYS_LOG_COLL_NAME;
        }
        if (ProductNumEnum.DCP_WEB_ENTER.getKey().equals(productNum)) {
            routingKey = DCP_WEBENTER_SYS_LOG_COLL_NAME;
        }
        if (ProductNumEnum.FAP_DESIGN_UNIT.getKey().equals(productNum)) {
            routingKey = FAP_DESIGN_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.FAP_AUDIT_UNIT.getKey().equals(productNum)) {
            routingKey = FAP_AUDIT_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.FAP_OWNER_UNIT.getKey().equals(productNum)) {
            routingKey = FAP_OWNER_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.FAP_CENTER.getKey().equals(productNum)) {
            routingKey = FAP_CENTER_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.REVIT.getKey().equals(productNum)) {
            routingKey = REVIT_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.FAP_APP_ANDROID.getKey().equals(productNum)) {
            routingKey = FAP_APP_ANDROID_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.FAP_APP_APPLE.getKey().equals(productNum)) {
            routingKey = FAP_APP_APPLE_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.CO_CENTER.getKey().equals(productNum)) {
            routingKey = CO_CENTER_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.CO_PC.getKey().equals(productNum)) {
            routingKey = CO_PC_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.CO_REVIT.getKey().equals(productNum)) {
            routingKey = CO_REVIT_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.CO_APP_ANDROID.getKey().equals(productNum)) {
            routingKey = CO_APP_ANDROID_SYS_LOG_ROUTING_KEY;
        }
        if (ProductNumEnum.CO_APP_APPLE.getKey().equals(productNum)) {
            routingKey = CO_APP_APPLE_SYS_LOG_ROUTING_KEY;
        }
        return routingKey;
    }

    /**
     * 根据产品名称获取该产品的日志集合名称
     *
     * @param productNum
     * @return String
     */
    static String dispatchCollName(String productNum) {
        return ROUTING_KEY_COLL_NAME_MAP.get(dispatchRoutingKey(productNum));
    }

}
