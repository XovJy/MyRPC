package version1.part2.client.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import version1.part2.common.message.RpcResponse;

/**
 * @author xuehao
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse>{


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        AttributeKey<RpcResponse> key = AttributeKey.valueOf("RPCResponse");
        channelHandlerContext.channel().attr(key).set(rpcResponse);
        channelHandlerContext.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable e) throws Exception {
        e.printStackTrace();
        channelHandlerContext.close();
    }
}
