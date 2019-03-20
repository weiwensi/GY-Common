package com.gysoft.utils.test.commons.collections;

import com.gysoft.utils.test.commons.collections.bean.Book;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.BagUtils;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.lang3.StringUtils;

/**
 * 测试bag集合
 *
 * @author 周宁
 * @Date 2018-08-02 8:40
 */
public class BagTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.center("bagTest start", 41, "="));
        Book book1 = new Book("thinking in java", "1-222-333", 70.8);
        Book book2 = new Book("effecitve java", "4-555-666", 78.1);
        Book book3 = new Book("design pattern", "7-888-999", 90.1);

        Bag myBag = BagUtils.typedBag(new HashBag(), Book.class);
        myBag.add(book1, 360);
        myBag.add(book2, 500);
        myBag.add(book3, 170);

        double price1 = book1.getRetailPrice();
        double price2 = book2.getRetailPrice();
        double price3 = book3.getRetailPrice();
        //从myBag中删除指定对象，删除全部
        myBag.remove(book1);
        //从myBag中删除指定对象，指定删除数量
        myBag.remove(book2, 200);

        int book1Count = myBag.getCount(book1);
        int book2Count = myBag.getCount(book2);
        int book3Count = myBag.getCount(book3);
        double totalValue = (price1 * book1Count) + (price2 * book2Count)
                + (price3 * book3Count);
        System.out.println("There are " + book1Count + " copies of "
                + book1.getName() + ".");
        System.out.println("There are " + book2Count + " copies of "
                + book2.getName() + ".");
        System.out.println("There are " + book3Count + " copies of "
                + book3.getName() + ".");
        System.out.println("The total value of these books is: " + totalValue);
    }
}
