package de.cloud.wrapper.netty.handler;

import de.cloud.wrapper.Manager;
import de.cloud.wrapper.core.ServerGroup;
import de.cloud.wrapper.core.config.configuration.MasterConfig;
import de.cloud.wrapper.netty.Client;
import de.cloud.wrapper.utils.logger;
import de.cloud.wrapper.utils.loglevel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NetworkStringHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //String[] ip = ctx.channel().remoteAddress().toString().split("/");
        //new logger(loglevel.INFO, "&greenMASTER["+ctx.channel().remoteAddress()+"]> Connected");
        ctx.channel().writeAndFlush(MasterConfig.getMasterConfig().getWrapperKey());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        String[] args = msg.toString().split(" ");

        new logger(loglevel.INFO, "MASTER["+ctx.channel().remoteAddress()+"]> "+msg);

        if(args[0].equalsIgnoreCase("close")) {
            Manager.getManager().stop();

        } else if (args[0].equalsIgnoreCase("create")) {
            if (args[1].equalsIgnoreCase("servergroup")) {
                new logger(loglevel.INFO, "Creating Servergroup '" + args[2].toUpperCase() + "' ...");
                Client.getChannel().writeAndFlush("Created Servergroup '"+args[2].toUpperCase() + "' !");

            } else if (args[1].equalsIgnoreCase("proxygroup")) {

            }
        }
    }
}
