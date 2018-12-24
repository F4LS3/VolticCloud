package de.cloud.master.netty.handler;

import de.cloud.master.core.GroupMode;
import de.cloud.master.core.ServerGroup;
import de.cloud.master.manager.WrapperManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NetworkObjectHandler extends SimpleChannelInboundHandler<Object> {

    public static Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.setChannel(ctx.channel());
        ServerGroup group = new ServerGroup();
        group.setGroupName("Test-Gruppe");
        group.setRam(4096);
        group.setMaxPlayers(150);
        group.setGroupMode(GroupMode.GAMESERVER);
        group.setWrapper(WrapperManager.getWrapperManager().getWrapper("wrapper-1"));
        this.getChannel().writeAndFlush(group);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object obj) throws Exception {
        if(obj instanceof ServerGroup) {
            ServerGroup group = (ServerGroup) obj;
        }
    }

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        NetworkObjectHandler.channel = channel;
    }
}
