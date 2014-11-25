package io.lee;

import io.lee.handler.LoginToGameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer extends AbstractServer {

	public NettyServer() throws Exception {

		start0();
	}

	/**
	 * 初始化 channel
	 * 
	 * @param port
	 * @param channelInitializer
	 * @return
	 * @throws Exception
	 */
	private Channel initChannel(int port, ChannelInitializer<?> channelInitializer) throws Exception {

		ServerBootstrap b = new ServerBootstrap();
		b.group(getBossGroup(), getWorkerGroup()).channel(NioServerSocketChannel.class);
		b.option(ChannelOption.SO_BACKLOG, 100);

		b.handler(new LoggingHandler(LogLevel.INFO)).childHandler(channelInitializer);

		return b.bind(port).sync().channel();
	}

	@Override
	public void start0() throws Exception {

		Channel gameChannel = initChannel(8888, new LoginToGameHandler());
		Channel chatChannel = initChannel(8080, new LoginToGameHandler());

		ALL_CHANNELS.add(gameChannel);
		ALL_CHANNELS.add(chatChannel);

	}

	public static void main(String[] args) {

		try {
			new NettyServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
