package com.allStack.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

/**
 * @author wang
 * @create 2023-2023-23-17:44
 */
public class NettyClient {
    private static final ByteBuffer readHeader = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
    private static final ByteBuffer writeHeader = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);

    private SocketChannel channel;
    public void sendMessage(byte[] body) throws IOException {
        channel = SocketChannel.open();

        channel.socket().setSoTimeout(60000);
        channel.connect(new InetSocketAddress("127.0.0.1",1088));

        writeWithHeader(channel,body);

        readHeader.clear();

        read(channel,readHeader);

        int bodyLen = readHeader.getInt(0);
        ByteBuffer bodyBuf = ByteBuffer.allocate(bodyLen).order(ByteOrder.BIG_ENDIAN);
        read(channel, bodyBuf);
        System.out.println("<客户端>收到响应内容: " + new String(bodyBuf.array(), "UTF-8") + ",长度:" + bodyLen);


    }

    private void writeWithHeader(SocketChannel channel, byte[] body) throws IOException {
        writeHeader.clear();
        writeHeader.putInt(body.length);
        writeHeader.flip();
        channel.write(ByteBuffer.wrap(body));
    }

    public void read(SocketChannel channel,ByteBuffer buffer) throws IOException {
        while(buffer.hasRemaining()) {
            int readLen = channel.read(buffer);

            if (readLen == -1) {
                throw new IOException("end of the stream while reading");
            }
        }
        buffer.flip();
        buffer.clear();

    }

    public static void main(String[] args) throws IOException {
        String body = "这是客户端发送的请求";
        new NettyClient().sendMessage(body.getBytes());
    }
}
