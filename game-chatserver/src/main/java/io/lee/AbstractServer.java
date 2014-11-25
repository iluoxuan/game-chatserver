package io.lee;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;

public abstract class AbstractServer implements Server {

	public static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup("NADRON-CHANNELS", GlobalEventExecutor.INSTANCE);

	@Getter
	private EventLoopGroup bossGroup = new NioEventLoopGroup(1);

	@Getter
	private EventLoopGroup workerGroup = new NioEventLoopGroup();

	public AbstractServer() {
	}

	public void start() throws Exception {

		start0();
	}

	public abstract void start0() throws Exception;

	public void stop() {
		ChannelGroupFuture future = ALL_CHANNELS.close();
		try {
			future.await();
		} catch (InterruptedException e) {

		} finally {
			if (null != bossGroup) {
				bossGroup.shutdownGracefully();
			}
			if (null != workerGroup) {
				workerGroup.shutdownGracefully();
			}
		}

	}

}
