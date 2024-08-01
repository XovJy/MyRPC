package version1.part3.client.rpcclient.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import version1.part3.client.netty.init.NettyClientInit;
import version1.part3.client.rpcclient.RpcClient;
import version1.part3.client.servicecenter.ServiceCenter;
import version1.part3.client.servicecenter.impl.ZkServiceCenter;
import version1.part3.common.message.RpcRequest;
import version1.part3.common.message.RpcResponse;

import java.net.InetSocketAddress;

/**
 * @author xuehao
 */

public class NettyRpcClient implements RpcClient {

    private static final Bootstrap BOOTSTRAP;
    private static final EventLoopGroup EVENT_LOOP_GROUP;

    private ServiceCenter serviceCenter;
    public NettyRpcClient(String host, int port) {
        this.serviceCenter = new ZkServiceCenter();
    }

    static {
        EVENT_LOOP_GROUP = new NioEventLoopGroup();
        BOOTSTRAP = new Bootstrap();
        BOOTSTRAP.group(EVENT_LOOP_GROUP).channel(NioSocketChannel.class)
                .handler(new NettyClientInit());
    }

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        InetSocketAddress address = serviceCenter.serviceDiscovery(request.getInterfaceName());
        String host = address.getHostName();
        int port = address.getPort();

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
