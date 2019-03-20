package com.gysoft.utils.test.commons.collections;

import com.gysoft.utils.test.commons.collections.bean.Book;
import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.BlockingBuffer;
import org.apache.commons.collections.buffer.BoundedFifoBuffer;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 周宁
 * @Date 2018-08-02 9:57
 */
public class BufferTest {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(StringUtils.center(" bufferTest start", 31, "="));
        Book book1 = new Book("thinking in java", "1-222-333", 70.8);
        Book book2 = new Book("effecitve java", "4-555-666", 78.1);
        Book book3 = new Book("design pattern", "7-888-999", 90.1);
        Book book4 = new Book("spring code", "9-111-2321", 100);
        Buffer buffer = BufferUtils.typedBuffer(new BoundedFifoBuffer(3), Book.class);
        buffer.add(book1);
        buffer.add(book2);
        buffer.add(book3);
        Book removed = (Book) buffer.remove();
        System.out.println("Removed:");
        System.out.println(removed);
        buffer.add(book4);
        BlockingBuffer blockingBuffer = (BlockingBuffer) BufferUtils.blockingBuffer(buffer);
        /**
         * 只有三个线程能够获取到缓冲区中的对象，当主线成sleep三秒以后
         * 继续往下执行，则第四个线程获取到缓冲区中新添加的对象，其余
         * 6个线程会一致阻塞
         */
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                synchronized (lock) {
                    System.out.println(blockingBuffer.get() + "Thread" + i);
                    blockingBuffer.remove();
                }
            }).start();
        });
        TimeUnit.SECONDS.sleep(3);
        blockingBuffer.add(book1);
        System.out.println(StringUtils.center("buffer test end",31,"="));


    }
}
