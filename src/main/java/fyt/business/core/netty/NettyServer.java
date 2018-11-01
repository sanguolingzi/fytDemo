package fyt.business.core.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.timeout.IdleStateHandler;
import fyt.business.core.netty.handler.HttpHandler;


public class NettyServer extends Thread{

    private int port;

    private ChannelFuture channelFuture;

    public NettyServer(int port){
         this.port = port;
    }

    public void init(){

        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        try{
            this.startServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            b.group(group,workGroup)
                    .channel(NioServerSocketChannel.class)//ServerSocketChannel以NIO的selector为基础进行实现的 用来接收新的连接 这里告诉channel如何获得新连接
                    //添加过滤
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            System.out.println("initChannel ch:" + ch);
                            ch.pipeline()
                                    .addLast(new IdleStateHandler(5, 10, 0))
                                    //.addLast("decoder", new StringDecoder())   // 1
                                    //.addLast("encoder", new StringEncoder())  // 2
                                    .addLast("decoder",new ByteArrayDecoder())
                                    .addLast("encoder",new ByteArrayEncoder())
                                    .addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3
                                    .addLast("handler", new HttpHandler());// 4

                        }
                    })
                    /**
                     * 这里可以设置指定通道channel的配置参数 请参考ChannelOption的详细的ChannelConfig实现的接口文档
                     */
                    .option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
                    /**
                     *  option()是提供给ServerSocketChannel
                     *  childOption()是提供给父管道ServerChannel接收到的连接
                     */
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

            channelFuture = b.bind(port).sync();
            if(channelFuture.isSuccess()){
                System.out.println("netty start success");
            }

            /**
             * 这里一直等待直到连接被关闭
             */
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            System.out.println("netty start error");
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public void close(){
        channelFuture.channel().close();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
