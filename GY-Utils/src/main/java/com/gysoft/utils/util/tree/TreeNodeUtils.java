package com.gysoft.utils.util.tree;

import com.gysoft.bean.tree.TreeNode;
import com.gysoft.utils.exception.ParamInvalidException;
import com.gysoft.utils.exception.ResultException;
import com.gysoft.utils.util.EmptyUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author 周宁
 * @date 2018/4/11 17:56
 */
public class TreeNodeUtils {

    /**
     * 最大递归深度，超过该深度抛出异常避免栈溢出
     */
    private static final int MAX_RECURSION_DEEP = 1 << 10;

    private static final Exception DATA_ERROR_EXCEPTION = new ResultException("父子节点相互引用,数据错误");

    /**
     * 获取祖先和孩子节点
     *
     * @param allNodes
     * @param node
     * @param factory
     * @param <E>
     * @param <ID>
     * @param <T>
     * @return
     */
    public static <E extends TreeNode, ID extends Serializable, T extends Collection<E>> T searchAncestorsAndDescendants(Map<ID, E> allNodes, E node, Supplier<T> factory) throws Exception {
        T result = factory.get();
        result.add(node);
        if (EmptyUtils.isEmpty(allNodes) || null == node) {
            return result;
        }
        putAncestors(allNodes, node, result, 0);
        putDescendants(allNodes, node, result, 0);
        return result;
    }

    /**
     * 获取祖先节点
     *
     * @param allNodes
     * @param node
     * @param factory
     * @param <E>
     * @param <ID>
     * @param <T>
     * @return
     */
    public static <E extends TreeNode, ID extends Serializable, T extends Collection<E>> T searchAncestors(Map<ID, E> allNodes, E node, Supplier<T> factory) throws Exception {
        T result = factory.get();
        result.add(node);
        if (EmptyUtils.isEmpty(allNodes) || null == node) {
            return result;
        }
        putAncestors(allNodes, node, result, 0);
        return result;
    }

    /**
     * 获取孩子节点
     *
     * @param allNodes
     * @param node
     * @param factory
     * @param <E>
     * @param <ID>
     * @param <T>
     * @return
     */
    public static <E extends TreeNode, ID extends Serializable, T extends Collection<E>> T searchDescendants(Map<ID, E> allNodes, E node, Supplier<T> factory) throws Exception {
        T result = factory.get();
        if (EmptyUtils.isEmpty(allNodes) || null == node) {
            return result;
        }
        result.add(node);
        putDescendants(allNodes, node, result, 0);
        return result;
    }
    /**
     *
     * @Description： 获取孩子节点
     * @author DJZ-HXF
     * @date 2018/12/11 11:27
     * @param allNodes
     * @param key node在allNodes中的key
     * @param factory
     * @return
     * @throws
     */
    public static <E extends TreeNode, ID extends Serializable, T extends Collection<E>> T searchDescendantsByKey(Map<ID, E> allNodes, ID key, Supplier<T> factory) throws Exception {
        return searchDescendants(allNodes,allNodes.get(key),factory);
    }

    /**
     * 递归当前节点的父节点
     *
     * @param allNodes
     * @param node
     * @param result
     * @param <E>
     * @param <ID>
     * @return
     */
    private static <E extends TreeNode, ID extends Serializable> Collection<E> putAncestors(Map<ID, E> allNodes, E node, Collection<E> result, int deep) throws Exception {
        if (deep > MAX_RECURSION_DEEP) {
            throw DATA_ERROR_EXCEPTION;
        }
        if (node.isRoot()) {
            return result;
        }
        node = allNodes.get(node.getParentId());
        result.add(node);
        return putAncestors(allNodes, node, result, deep += 1);
    }

    /**
     * 递归当前节点的子节点
     *
     * @param allNodes
     * @param node
     * @param result
     * @param <E>
     * @param <ID>
     * @return
     */
    private static <E extends TreeNode, ID extends Serializable> Collection<E> putDescendants(Map<ID, E> allNodes, E node, Collection<E> result, int deep) throws Exception {
        if (deep > MAX_RECURSION_DEEP) {
            throw DATA_ERROR_EXCEPTION;
        }
        Iterator<Map.Entry<ID, E>> iterator = allNodes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<ID, E> entry = iterator.next();
            E e = entry.getValue();
            if (e.getParentId().equals(node.getId())) {
                putDescendants(allNodes, e, result, deep += 1);
                result.add(e);
            }
        }
        return result;
    }

}
