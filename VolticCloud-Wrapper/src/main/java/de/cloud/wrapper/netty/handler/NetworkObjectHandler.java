package de.cloud.wrapper.netty.handler;

import de.cloud.wrapper.core.ServerGroup;
import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NetworkObjectHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object obj) throws Exception {
        if(obj instanceof ServerGroup) {
            ServerGroup group = (ServerGroup) obj;

            new logger(loglevel.INFO,      group.getGroupName() + "\n" +
                                                        group.getRam() + "\n" +
                                                        group.getWrapper() + "\n" +
                                                        group.getMaxPlayers() + "\n" +
                                                        group.getGroupMode().name() + "\n");
        }
    }
}
