package io.lee.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Client {

	public static void main(String[] args) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							p.addLast(new LoggingHandler(LogLevel.INFO));
							p.addLast(new Clienthandler());
						}
					});

			// Start the client.
			ChannelFuture f = b.connect("172.0.0.1", 8080).sync();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}
}
