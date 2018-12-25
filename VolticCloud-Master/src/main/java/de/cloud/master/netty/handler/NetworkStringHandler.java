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
import java.util.HashMap;
import java.util.List;

public class NetworkStringHandler extends SimpleChannelInboundHandler<String> {

    private static NetworkStringHandler networkHandlerInstance;

    public static Channel channel;
    public static boolean isConnected = false;
    private static List<SocketAddress> connectedIps = new ArrayList<>();
    public HashMap<SocketAddress, String> connectedWrappers = new HashMap<>();

    public static void init() {
        NetworkStringHandler.networkHandlerInstance = new NetworkStringHandler();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.setChannel(ctx.channel());
        this.isConnected = true;
        connectedIps.add(ctx.channel().remoteAddress());
        connectedWrappers.put(ctx.channel().remoteAddress(), "NEW_WRAPPER");
        new logger(loglevel.INFO, connectedWrappers.get(ctx.channel().remoteAddress())+"["+ctx.channel().remoteAddress()+"]> Connected...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if(connectedIps.contains(ctx.channel().remoteAddress())) {
            connectedIps.remove(ctx.channel().remoteAddress());
            connectedWrappers.remove(ctx.channel().remoteAddress());
            new logger(loglevel.WARNING, connectedWrappers.get(ctx.channel().remoteAddress())+"["+ctx.channel().remoteAddress()+"]> Disconnected...");
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        String[] args = msg.split(" ");
        if(!Manager.isAuthenticated) {
            if (args[0].equalsIgnoreCase(WrapperManager.getWrapperManager().getWrapperKey())) {
                this.channel.writeAndFlush("Authentication with Master was successfully!");
                Manager.isAuthenticated = true;
            } else {
                connectedIps.remove(this.channel.remoteAddress());
                this.channel.writeAndFlush("Authentication with Master failed!");
                this.channel.close();
            }
        } else {
            if(args[0].equalsIgnoreCase("WRAPPER-NAME:")) {
                connectedWrappers.put(ctx.channel().remoteAddress(), args[1]);
            }
        }

        new logger(loglevel.INFO, connectedWrappers.get(ctx.channel().remoteAddress())+"["+ctx.channel().remoteAddress()+"]> "+msg);
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
