package io.lee.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 链接到游戏服务器
 * 
 * @author <a href="mailto:junqing.li@qiyun.com">junqing.li</a>
 * @version 1.0
 * @since 1.0
 */
public class LoginToGameHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		System.out.println("----init LoginToGameHandler -----");

		ChannelPipeline p = ch.pipeline();

		// length | content
		p.addLast("idleStateCheck", new IdleStateHandler(60, 60, 60));
		p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
	}

}
