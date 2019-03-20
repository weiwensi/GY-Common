package com.gysoft.utils.bean;

import com.gysoft.bean.utils.BaseEnum;


/**
 * @author 周宁
 * @Date 2018-09-28 16:03
 */
public enum EmqResponseCode implements BaseEnum<Integer,String> {
    Success(0, "成功"), BadRpc(101, "badrpc"), UnknownError(102, "未知错误"),
    UserPasswordError(103, "用户名密码错误"), UserPassworNotEmpty(104, "用户名密码不能为空"),
    DelUserNotExists(105, "删除用户不存在"), AdminCannotDel(106, "admin用户不能删除"),
    ReqParamMiss(107, "请求参数缺失"), ReqParamTypeError(108, "请求参数类型错误"),
    ReqParamNotJson(109, "请求参数不是json类型"), PluginAlreadyLoad(110, "插件已经加载，不能重复加载"),
    PluginAlreadyUnistall(111, "插件已经卸载，不能重复卸载"), UserOffline(112, "用户不在线"),
    UserAlreadyExists(113, "用户已经存在"), OldPwdError(114, "旧密码错误");

    private final int code;

    private final String msg;

    EmqResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getKey() {
        return code;
    }

    @Override
    public String getValue() {
        return msg;
    }
}
