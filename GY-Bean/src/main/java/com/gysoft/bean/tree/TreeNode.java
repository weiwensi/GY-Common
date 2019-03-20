package com.gysoft.bean.tree;

import java.io.Serializable;

/**
 * 树节点
 * @author 周宁
 * @date 2018/4/11 17:51
 */
public interface TreeNode<ID extends Serializable> {

    ID getId();

    ID getParentId();

    String getName();

    boolean isRoot();

}
