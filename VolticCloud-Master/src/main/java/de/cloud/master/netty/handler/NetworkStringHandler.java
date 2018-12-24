package de.cloud.master.netty.handler;

import de.cloud.master.Manager;
import de.cloud.master.manager.WrapperManager;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class NetworkStringHandler extends SimpleChannelInboundHandler<String> {

    private static NetworkStringHandler networkHandlerInstance;

    public static Channel channel;
    public static boolean isConnected = false;
    private static List<SocketAddress> connectedIps = new ArrayList<>();

    public static void init() {
        NetworkStringHandler.networkHandlerInstance = new NetworkStringHandler();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.setChannel(ctx.channel());
        this.isConnected = true;
        connectedIps.add(ctx.channel().remoteAddress());
        new logger(loglevel.INFO, "WRAPPER["+ctx.channel().remoteAddress()+"]> Connected...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if(connectedIps.contains(ctx.channel().remoteAddress())) {
            connectedIps.remove(ctx.channel().remoteAddress());
            new logger(loglevel.WARNING, "WRAPPER["+ctx.channel().remoteAddress()+"]> Disconnected...");
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        if(!Manager.isAuthenticated) {
            if (msg.equalsIgnoreCase(WrapperManager.getWrapperManager().getWrapperKey())) {
                this.channel.writeAndFlush("Authentication with Master was successfully!");
                Manager.isAuthenticated = true;
            } else {
                connectedIps.remove(this.channel.remoteAddress());
                this.channel.writeAndFlush("Authentication with Master failed!");
                this.channel.close();
            }
        }

        new logger(loglevel.INFO, "WRAPPER["+ctx.channel().remoteAddress()+"]> "+msg);
    }

    public static void setChannel(Channel channel) {
        NetworkStringHandler.channel = channel;
    }

    public static Channel getChannel() {
        return channel;
    }

    public static NetworkStringHandler getNetworkHandler() {
        return networkHandlerInstance;
    }
}
