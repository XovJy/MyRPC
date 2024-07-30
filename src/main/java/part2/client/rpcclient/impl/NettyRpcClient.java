package part2.client.rpcclient.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import part2.client.netty.init.NettyClientInit;
import part2.client.rpcclient.RpcClient;
import part2.common.message.RpcRequest;
import part2.common.message.RpcResponse;
import part2.server.netty.init.NettyServerInit;

/**
 * @author xuehao
 */

public class NettyRpcClient implements RpcClient {
    private String host;
    private int port;
    private static final Bootstrap BOOTSTRAP;
    private static final EventLoopGroup EVENT_LOOP_GROUP;

    public NettyRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    static {
        EVENT_LOOP_GROUP = new NioEventLoopGroup();
        BOOTSTRAP = new Bootstrap();
        BOOTSTRAP.group(EVENT_LOOP_GROUP).channel(NioSocketChannel.class)
                .handler(new NettyClientInit());
    }

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            ChannelFuture channelFuture = BOOTSTRAP.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush(request);
            channel.closeFuture().sync();

            AttributeKey<RpcResponse> key = AttributeKey.valueOf("RPCResponse");
            RpcResponse response = channel.attr(key).get();

            System.out.println(response);
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
