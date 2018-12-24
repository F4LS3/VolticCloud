package de.cloud.wrapper.netty;

import de.cloud.wrapper.Manager;
import de.cloud.wrapper.commands.HelpCommand;
import de.cloud.wrapper.commands.StopCommand;
import de.cloud.wrapper.core.Command;
import de.cloud.wrapper.core.config.configuration.MasterConfig;
import de.cloud.wrapper.netty.handler.NetworkObjectHandler;
import de.cloud.wrapper.netty.handler.NetworkStringHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Client {

    public static boolean EPOLL = Epoll.isAvailable();

    private static Client clientInstance;
    public EventLoopGroup eventLoopGroup;
    private static Channel channel;

    public static void init() {
        Client.clientInstance = new Client();
    }

    public Client() {

    }

    public void runClient() {
        eventLoopGroup = EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            this.setChannel(new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(EPOLL ? EpollSocketChannel.class : NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new StringDecoder(StandardCharsets.UTF_8))
                                    .addLast(new StringEncoder(StandardCharsets.UTF_8))
                                    .addLast(new NetworkStringHandler());
                        }
                    }).connect(MasterConfig.getMasterConfig().getMasterIp(), MasterConfig.getMasterConfig().getMasterPort()).sync().channel());
            Manager.getManager().addCommand(new StopCommand());
            Manager.getManager().addCommand(new HelpCommand());

            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() != 0) {
                    for (Command command : Manager.getManager().commands) {
                        command.execute(line.split(" "));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public static Client getClient() {
        return clientInstance;
    }
}
