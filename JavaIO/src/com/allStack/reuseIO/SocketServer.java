package com.allStack.reuseIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wang
 * @create 2023-2023-23-0:20
 */
public class SocketServer {
//    static{
//        BasicConfigurator.configure();
//    }
//
//    private static Log logger = LogFactory.getLog(SocketServer.class);
//
//    public static void main(String[] args) throws IOException {
//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        serverSocketChannel.configureBlocking(false);
//        ServerSocket socket = serverSocketChannel.socket();
//        socket.bind(new InetSocketAddress("127.0.0.1",8011));
//        Selector selector = Selector.open();
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//
//        while(true){
//            if(selector.select(100)==0){
//                continue;
//            }
//
//            Set<SelectionKey> selectionKeys = selector.selectedKeys();
//
//            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
//
//            while(keyIterator.hasNext()){
//                SelectionKey readyKey = keyIterator.next();
//
//                keyIterator.remove();
//
//                SelectableChannel selectableChannel = readyKey.channel();
//                if(readyKey.isValid() && readyKey.isAcceptable()){
//                    SocketServer.logger.info("channel 通道已经准备好了");
//
//                    /*当server socket channel通道已经准备好，就可以从server socket channel中获取socketchannel了
//                         * 拿到socket channel后，要做的事情就是马上到selector注册这个socket channel感兴趣的事情。
//                         * 否则无法监听到这个socket channel到达的数据
//                    */
//                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectableChannel;
//                    SocketChannel acceptSocket = serverSocketChannel1.accept();
//                    register(acceptSocket,selector);
//                }else if(readyKey.isValid() && readyKey.isConnectable()){
//                    logger.info("连接已经准备完成了");
//                }else if(readyKey.isValid() && readyKey.isReadable()){
//                    logger.info("数据已经准备完成，可以读了");
//                    readSocketChannel(readyKey);
//                }
//            }
//        }
//    }
//
//
//
//    private static void register(SocketChannel acceptSocket, Selector selector) throws IOException {
//        acceptSocket.configureBlocking(false);
//        acceptSocket.register(selector,SelectionKey.OP_READ, ByteBuffer.allocateDirect(2048));
//    }
//
//    /**
//     * 这个方法用于读取从客户端传来的信息。
//     * 并且观察从客户端过来的socket channel在经过多次传输后，是否完成传输。
//     * 如果传输完成，则返回一个true的标记。
//     * @param readyKey
//     * @throws Exception
//     */
//    private static void readSocketChannel(SelectionKey readyKey) throws IOException {
//        SocketChannel socketChannel = (SocketChannel) readyKey.channel();
//        InetSocketAddress clientAddress = (InetSocketAddress)socketChannel.getLocalAddress();
//        int port = clientAddress.getPort();
//
//        ByteBuffer buffer = (ByteBuffer) readyKey.attachment();
//
//        int readLen = 0;
//
//        StringBuilder sb = new StringBuilder();
//
//        try {
//            readLen = socketChannel.read(buffer);
//        } catch (IOException e) {
//            logger.error("read error");
//            socketChannel.close();
//            return;
//        }
//
//        if(readLen==-1){
//            logger.warn("缓存区没有数据");
//            return;
//        }
//
//        buffer.flip();
//
//        byte[] bytes = buffer.array();
//
//        String messageEncode = new String(bytes,"utf8");
//        String messageDecode = URLDecoder.decode(messageEncode,"utf8");
//
////        表示客户端发送过来over，表示结束了
//        if(messageDecode.indexOf("over")!=-1){
//            buffer.clear();
//            logger.info("端口:"+port+"发送过来请求message"+messageEncode);
//
//            //======================================================
//            //          当然接受完成后，可以在这里正式处理业务了
//            //======================================================
//
//            ByteBuffer sendBuffer = ByteBuffer.wrap(bytes);
//            socketChannel.write(sendBuffer);
//            socketChannel.close();
//        }else{
//            logger.info("端口："+port+"发送过来的请求message:"+messageDecode);
//            buffer.position(readLen);
//            buffer.limit(buffer.capacity());
//        }
//
//
//
//    }

    static{
        BasicConfigurator.configure();
    }

    private static Log logger = LogFactory.getLog(SocketServer.class);
    private static final ConcurrentHashMap<Integer,StringBuffer> MESSAGEHASHCONTEXT = new ConcurrentHashMap();
    private static ExecutorService executor =  Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress("127.0.0.1",8011));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while(true){
            while(selector.select(100)==0){
                /*
                * 这里可以在没有对应的处理事件的时候进行对应的业务逻辑的处理。
                * */
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while(selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();

//                这里如果是acceptable的话，表名刚刚处于可接受状态，你需要对这个事件的通道做初始化，并且配置选择器
                SelectableChannel selectableChannel = selectionKey.channel();
                if(selectionKey.isValid() && selectionKey.isAcceptable()){
                    logger.info("通道已经准备完成，接下来绑定选择器");
                    ServerSocketChannel channel = (ServerSocketChannel) selectableChannel;
                    SocketChannel acceptChanel = channel.accept();
                    register(acceptChanel,selector);
                }else if(selectionKey.isValid() &&selectionKey.isConnectable()){
                    logger.info("连接成功了");
                }else if(selectionKey.isValid() && selectionKey.isReadable()){
                    logger.info("接下啦开始读操作");
                    readSocketChannel(selectionKey);
//                    executor.execute(()->{
//                        try {
//                            readSocketChannel(selectionKey);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
                }
            }
        }
    }

    private static void readSocketChannel(SelectionKey selectionKey) throws IOException {
//        因为开始读操作了，所以将它转化成为客户端的TCP通道,并且这个通道在之前已经配置完成了
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

//        InetSocketAddress address = (InetSocketAddress)socketChannel.getRemoteAddress();
//        int port = address.getPort();

        int readLen = -1;

        ByteBuffer contextBuffer =(ByteBuffer) selectionKey.attachment();
        StringBuffer message = new StringBuffer();
//        当没有读出来任何数据的时候就退出了。
        while((readLen = socketChannel.read(contextBuffer))>0) {
//        将缓冲区转换成为写状态
            contextBuffer.flip();

            int position = contextBuffer.position();
            int capacity = contextBuffer.capacity();
            byte[] bytes = new byte[capacity];

            contextBuffer.get(bytes,position,readLen);


            String messageEncode = new String(bytes,0,readLen,"utf8");
            message.append(messageEncode);

//            最后在切换成为写模式
            contextBuffer.clear();
        }

        if(readLen==-1){
            socketChannel.close();
        }


        if(URLDecoder.decode(message.toString(),"utf8").indexOf("over")!=-1){
            logger.info("客户端发送过来over了，要退出了！");

            int channelUUID = socketChannel.hashCode();
            StringBuffer historyMessage = MESSAGEHASHCONTEXT.remove(channelUUID);
            StringBuffer compeleteMessage;
            if(historyMessage==null){
                compeleteMessage = message;
            }else{
                compeleteMessage = historyMessage.append(message);
            }

            logger.info("从客户端发送过来的完整的message的信息是："+URLDecoder.decode(compeleteMessage.toString(),"utf8"));

            contextBuffer.clear();

                /*
                  ----------------------------------------------------
                * | 这里接受退出完成之后，你就可以处理你退出的业务逻辑了
                * -----------------------------------------------------
                * */

            ByteBuffer sendBuffer = ByteBuffer.wrap("退出了，兄弟".getBytes());
            socketChannel.write(sendBuffer);
            socketChannel.close();
        }else{
            logger.info("客户端发送过来的消息:"+message);
            int channelUUID = socketChannel.hashCode();
            StringBuffer historyMessage = MESSAGEHASHCONTEXT.get(channelUUID);
            if(historyMessage==null){
                historyMessage = new StringBuffer();

            }
//            将对应读取的部分信息存入到HashMap中。
            historyMessage.append(message);

            MESSAGEHASHCONTEXT.put(channelUUID,historyMessage);
        }

    }

    private static void register(SocketChannel acceptChanel, Selector selector) throws IOException {
        acceptChanel.configureBlocking(false);
        acceptChanel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocateDirect(50));
    }

}
