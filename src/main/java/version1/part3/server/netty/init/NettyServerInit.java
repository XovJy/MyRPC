package version1.part3.server.netty.init;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AllArgsConstructor;
import version1.part3.server.netty.handler.NettyRpcServerHandler;
import version1.part3.server.provider.ServiceProvider;


/**
 * @author xuehao
 */
@AllArgsConstructor
public class NettyServerInit extends ChannelInitializer<SocketChannel> {


    private ServiceProvider serviceProvider;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                0 ,4,0,4));

        pipeline.addLast(new LengthFieldPrepender(4));

        pipeline.addLast(new ObjectEncoder());

        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException {
                return Class.forName(className);
            }
        }));

        pipeline.addLast(new NettyRpcServerHandler(serviceProvider));
    }
}
