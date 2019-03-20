package com.gysoft.utils.util.tree;

import com.gysoft.bean.tree.Tree;
import com.gysoft.bean.tree.TreeNode;
import com.gysoft.utils.util.EmptyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-04-26 20:03
 */
public class TreeUtils {

    /**
     * 将数据组装成树结构
     *
     * @param list
     * @param <T>
     * @return Tree<T>
     */
    public static <T extends TreeNode> Tree<T> buildTree(Collection<T> list) {
        List<Tree<T>> nodes = new ArrayList<>();
        list.forEach(node -> {
            Tree<T> tree = new Tree<T>();
            tree.setId(node.getId() + EmptyUtils.EMPTY_STR);
            tree.setParentId(node.isRoot() ? EmptyUtils.EMPTY_STR : node.getParentId() + EmptyUtils.EMPTY_STR);
            tree.setText(node.getName());
            nodes.add(tree);
        });
        if (EmptyUtils.isEmpty(nodes)) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (EmptyUtils.isEmpty(pid)) {
                topNodes.add(children);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setHasChildren(true);
                    return;
                }
            });
        });
        Tree<T> root = new Tree();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root.setId("-1");
            root.setParentId(EmptyUtils.EMPTY_STR);
            root.setHasParent(false);
            root.setHasChildren(true);
            root.setChecked(true);
            root.setChildren(topNodes);
            root.setText("根节点");
        }
        return root;
    }
}
