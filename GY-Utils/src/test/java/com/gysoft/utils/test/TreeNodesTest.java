package com.gysoft.utils.test;

import com.gysoft.bean.tree.Tree;
import com.gysoft.bean.tree.TreeNode;
import com.gysoft.utils.util.tree.TreeNodeUtils;
import com.gysoft.utils.util.tree.TreeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

/**
 * 模拟测试树节点
 */
public class TreeNodesTest {

    Map<String, MockTreeNode> allNodes = new HashMap<>();
    private MockTreeNode root;

    private MockTreeNode level10;
    private MockTreeNode level11;
    private MockTreeNode level12;

    private MockTreeNode level20;
    private MockTreeNode level21;
    private MockTreeNode level22;

    private MockTreeNode level30;
    private MockTreeNode level31;
    private MockTreeNode level32;

    @Before
    public void setUp() {
        String rootId = UUID.randomUUID().toString().replace("-", "");
        root = new MockTreeNode(rootId, rootId, "root");
        allNodes.put(root.getId(), root);

        level10 = root.createSubNode("level10");
        level11 = root.createSubNode("level11");
        level12 = root.createSubNode("level12");

        allNodes.put(level10.getId(), level10);
        allNodes.put(level11.getId(), level11);
        allNodes.put(level12.getId(), level12);

        level20 = level10.createSubNode("level20");
        level21 = level10.createSubNode("level21");
        level22 = level10.createSubNode("level22");

        allNodes.put(level20.getId(), level20);
        allNodes.put(level21.getId(), level21);
        allNodes.put(level22.getId(), level22);

        level30 = level20.createSubNode("level30");
        level31 = level20.createSubNode("level31");
        level32 = level20.createSubNode("level32");

        allNodes.put(level30.getId(), level30);
        allNodes.put(level31.getId(), level31);
        allNodes.put(level32.getId(), level32);
    }

    @Test
    public void testSearchAncestors() throws Exception {
        Set<MockTreeNode> result = TreeNodeUtils.searchAncestors(allNodes, level20, (Supplier<Set<MockTreeNode>>) () -> new HashSet<>());
        Set<TreeNode<String>> expected = new HashSet<>();
        expected.add(level20);
        expected.add(level10);
        expected.add(root);
        assertEquals(expected, result);
    }

    @Test
    public void testSearchDescendants() throws Exception {
        Set<MockTreeNode> result = TreeNodeUtils.searchDescendants(allNodes, level10, (Supplier<Set<MockTreeNode>>) () -> new HashSet<>());

        Set<TreeNode<String>> expected = new HashSet<>();
        expected.add(level10);
        expected.add(level20);
        expected.add(level21);
        expected.add(level22);

        expected.add(level30);
        expected.add(level31);
        expected.add(level32);
        assertEquals(expected, result);
    }

    @Test
    public void testSearchAncestorsAndDescendants() throws Exception {
        Set<MockTreeNode> result = TreeNodeUtils.searchAncestorsAndDescendants(allNodes, level20, (Supplier<Set<MockTreeNode>>) () -> new HashSet<>());

        Set<TreeNode<String>> expected = new HashSet<>();
        expected.add(root);
        expected.add(level10);
        expected.add(level20);
        expected.add(level30);
        expected.add(level31);
        expected.add(level32);
        assertEquals(expected, result);
    }

    @Test
    public void testBuildTree() {
        Tree<MockTreeNode> t = TreeUtils.buildTree(allNodes.values());
        System.out.println(t);
    }

}
