package com.gysoft.utils.util.path;

import com.gysoft.utils.util.EmptyUtils;
import org.apache.commons.lang3.RandomStringUtils;
import strman.Strman;

import java.util.*;

/**
 * @author 周宁
 * @Date 2018-04-25 16:11
 */
public class PathUtils {
    public static final String ROOT_PARENT_PATHID = "-1";

    public static final String PATH_SPLIT = "/";

    public static final Integer PATH_LENGTH = 8;

    /**
     * 虚拟树节点名称
     */
    public static final String VIRTUAL_PATH_NAME = "全部";
    /**
     * 生成八位长度的随机字符串
     *
     * @param
     * @return String
     * @throws
     * @author 周宁
     * @version 1.0
     */
    public static String genPath() {
        return RandomStringUtils.randomAlphabetic(PATH_LENGTH);
    }

    /**
     * 拆分路径字符串，按照以下格式拆分 <br/>
     * /a/b/c->/a,/a/b/,/a/b/c <br/>
     * a/b/c->a,a/b,a/b/c <br/>
     *
     * @param paths
     * @return List<String>
     */
    public static List<String> splitPath(List<String> paths) {
        if (EmptyUtils.isEmpty(paths)) {
            return Collections.EMPTY_LIST;
        }
        Set<String> temp = new HashSet();
        paths.forEach(path -> {
            if (path.startsWith("/")) {
                do {
                    temp.add(path);
                    path = path.substring(0, path.lastIndexOf("/"));
                } while (path.contains("/"));
            } else {
                while (path.contains("/")) {
                    temp.add(path);
                    path = path.substring(0, path.lastIndexOf("/"));
                }
                temp.add(path);
            }
        });
        return new ArrayList<>(temp);
    }

    /**
     * 获取父path路径
     * @author 周宁
     * @param path
     * @param pathId
     * @throws
     * @version 1.0
     * @return
     */
    public static String getParentPath(String path,String pathId){
        if(path.length()==PATH_LENGTH){
            return path.replace(pathId, EmptyUtils.EMPTY_STR);
        }
        return path.replace(PATH_SPLIT + pathId, EmptyUtils.EMPTY_STR);
    }

}
