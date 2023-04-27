package com.allStack.aio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import javax.annotation.processing.Completion;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wang
 * @create 2023-2023-23-15:08
 */
public class AsynchronousIOServer {

    static{
        BasicConfigurator.configure();
    }

    public static final Object waitObj = new Object();

    private static Log logger = LogFactory.getLog(AsynchronousIOServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * 对于使用的线程池技术，我一定要多说几句
         * 1、Executors是线程池生成工具，通过这个工具我们可以很轻松的生成“固定大小的线程池”、“调度池”、“可伸缩线程数量的池”。具体请看API Doc
         * 2、当然您也可以通过ThreadPoolExecutor直接生成池。
         * 3、这个线程池是用来得到操作系统的“IO事件通知”的，不是用来进行“得到IO数据后的业务处理的”。要进行后者的操作，您可以再使用一个池(最好不要混用)
         * 4、您也可以不使用线程池(不推荐)，如果决定不使用线程池，直接AsynchronousServerSocketChannel.open()就行了。
         * */
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);

        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8012));
//        为AsynchronousServerSocketChanel注册监听器，注意只是跟这个注册，不包括他里面的socket
        serverSocketChannel.accept(null,new ServerSocketChannelHandler(serverSocketChannel));


        synchronized (waitObj){
            waitObj.wait();
        }
    }
}

/*
* 这个处理器类，专门用来响应 ServerSocketChannel 的事件。
* */
class ServerSocketChannelHandler implements CompletionHandler<AsynchronousSocketChannel,Void> {

    static{
        BasicConfigurator.configure();
    }
    private static Log logger = LogFactory.getLog(ServerSocketChannelHandler.class);


    private AsynchronousServerSocketChannel serverSocketChannel;

    public ServerSocketChannelHandler(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }


    @Override
    public void completed(AsynchronousSocketChannel result, Void attachment) {
        logger.info("completed(AsynchronousSocketChannel result, ByteBuffer attachment)");

        serverSocketChannel.accept(attachment,this);

        //=========================================================================
        //          和上篇文章的代码相同，我们以“over”符号作为客户端完整信息的标记
        //=========================================================================
        ByteBuffer readBuffer = ByteBuffer.allocate(50);
//        接下来就进行读操作了。
        result.read(readBuffer,new StringBuffer(),new AsynchronousRead(result,readBuffer));
    }

    @Override
    public void failed(Throwable exc, Void attachment) {

    }
}

class AsynchronousRead implements CompletionHandler<Integer,StringBuffer>{

    static {
        BasicConfigurator.configure();
    }

    private static Log logger = LogFactory.getLog(AsynchronousRead.class);

    private AsynchronousSocketChannel socketChannel;
    private ByteBuffer readBuffer;

    public AsynchronousRead(AsynchronousSocketChannel socketChannel, ByteBuffer readBuffer) {
        this.socketChannel = socketChannel;
        this.readBuffer = readBuffer;
    }


    @Override
    public void completed(Integer result, StringBuffer historyAttachment) {
        if(result==-1){
            try {
                logger.info("TCP已经关闭了");
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("completed(Integer result, Void attachment) : 然后我们来取出通道中准备好的值");

        int readLen = -1;
        readBuffer.flip();
        byte[] bytes = new byte[1024];
        readBuffer.get(bytes,0,result);
        readBuffer.clear();

        String message = new String(bytes);


        historyAttachment.append(message);

        logger.info("收到完整的信息:"+historyAttachment);
        if(historyAttachment.indexOf("over")!=-1){
            logger.info("数据已经准备完成了");
            return;
        }

        historyAttachment = new StringBuffer();
        socketChannel.read(readBuffer,historyAttachment,this);

    }

    @Override
    public void failed(Throwable exc, StringBuffer attachment) {
        logger.error("执行异步IO出错了,服务器将关闭");
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
