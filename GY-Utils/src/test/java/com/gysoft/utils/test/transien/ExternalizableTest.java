package com.gysoft.utils.test.transien;

import java.io.*;

/**
 * @author 周宁
 * @Date 2018-08-02 15:58
 */
public class ExternalizableTest implements Externalizable {

    private transient String content="不论transient是否修饰我，我都会被序列化";
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        content = (String) in.readObject();
    }

    public static void main(String[] args)throws Exception {
        ExternalizableTest et = new ExternalizableTest();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:/externalizable"));
        out.writeObject(et);

        ObjectInputStream is = new ObjectInputStream(new FileInputStream("D:/externalizable"));
        et = (ExternalizableTest) is.readObject();
        System.out.println(et.content);
        out.close();
        is.close();
    }
}
