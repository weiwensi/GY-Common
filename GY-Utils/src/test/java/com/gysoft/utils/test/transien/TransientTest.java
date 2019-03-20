package com.gysoft.utils.test.transien;

import com.gysoft.utils.test.transien.bean.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 周宁
 * @Date 2018-08-02 15:35
 */
public class TransientTest {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setPassword("123456");
        user.setUsername("zhouning");
        System.out.println("序列化之前用户数据为:"+user.toString());
        //将user写进文件中
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("D:/user.txt"));
        os.writeObject(user);
        os.flush();
        os.close();
        //从流中读取User的数据
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("D:/user.txt"));
        user = (User) is.readObject();
        is.close();
        System.out.println("读取序列化之后用户数据为"+user.toString());

    }
}
