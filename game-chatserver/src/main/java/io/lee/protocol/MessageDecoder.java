package io.lee.protocol;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * byte 转 message
 * 
 * @author <a href="mailto:junqing.li@qiyun.com">junqing.li</a>
 * @version 1.0
 * @since 1.0
 */
public class MessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

		System.out.println("byte to message");
	}

}
