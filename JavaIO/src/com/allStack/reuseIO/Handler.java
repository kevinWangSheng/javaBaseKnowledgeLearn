package com.allStack.reuseIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang
 * @create 2023-2023-22-18:32
 */
public class Handler implements  Runnable{
//    这个selector是专门用来单独处理线程池中业务的计算也编解码的业务的
    private volatile Selector selector;
    private final SocketChannel channel;
    private SelectionKey key;
    private volatile ByteBuffer input = ByteBuffer.allocate(1024);
    private volatile ByteBuffer output = ByteBuffer.allocate(1024);

    public Handler(SocketChannel socketChannel) throws IOException {
        this.channel = socketChannel;
        selector = Selector.open();
        channel.configureBlocking(false);
        key = channel.register(selector,SelectionKey.OP_READ);

    }

    @Override
    public void run() {
        try {
            while(selector.isOpen() && channel.isOpen()){
    //            等待事件执行完成
                selector.select();
                Set<SelectionKey> keys = select();
                Iterator<SelectionKey> keyIterator = keys.iterator();
                while(keyIterator.hasNext()){
                    SelectionKey nextKey = keyIterator.next();
//                    keyIterator.remove();
                    if(nextKey.isReadable()){
                        read(nextKey);
                    }else if(nextKey.isWritable()){
                        write(nextKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<SelectionKey> select() throws IOException {
        selector.select();
        Set<SelectionKey> keys = selector.keys();
        if(keys.isEmpty()){
            int interestOps = key.interestOps();
            selector = Selector.open();
            key = channel.register(selector,interestOps);
            return select();
        }
        return keys;
    }

    private void write(SelectionKey nextKey) throws IOException {
        output.flip();
        if(channel.isOpen()) {
            channel.write(output);  //当有写事件的时候，将业务处理的数据写入到缓冲区中
            nextKey.channel();
            channel.close();
            output.clear();
        }

    }

    private void read(SelectionKey nextKey) throws IOException {
        channel.read(input);
        if(input.position()==0){
            return;
        }
//        反转进行写
        input.flip();
        process();
//        写完成之后进行clear
        input.clear();
        nextKey.interestOps(SelectionKey.OP_WRITE);
    }

    private void process(){
        byte[] bytes = new byte[input.remaining()];
        input.get(bytes);
        String message = new String(bytes);
//        读出缓冲区的数据
        System.out.println("reveive the data from the client is :"+message);

//        对缓冲区的数据写
        output.put("hello client".getBytes());
    }


}
