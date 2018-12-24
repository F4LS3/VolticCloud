package de.cloud.master.netty;

import de.cloud.master.Manager;
import de.cloud.master.commands.CreateCommand;
import de.cloud.master.commands.HelpCommand;
import de.cloud.master.commands.ModuleCommand;
import de.cloud.master.commands.StopCommand;
import de.cloud.master.manager.Command;
import de.cloud.master.netty.handler.NetworkObjectHandler;
import de.cloud.master.netty.handler.NetworkStringHandler;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Server {

    public static final boolean EPOLL = Epoll.isAvailable();

    private static Server serverInstance;
    public EventLoopGroup eventLoopGroup;

    public static void init() {
        Server.serverInstance = new Server();
    }

    public Server() {

    }

    public void runServer(int port) {
        eventLoopGroup = EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {

            new ServerBootstrap()
                    .group(eventLoopGroup)
                    .channel(EPOLL ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) {
                            channel.pipeline()
                                    .addLast(new StringDecoder(StandardCharsets.UTF_8))
                                    .addLast(new StringEncoder(StandardCharsets.UTF_8))
                                    .addLast(new NetworkStringHandler());
                        }
                    }).bind(port).sync().channel();

            Manager.getManager().addCommand(new StopCommand());
            Manager.getManager().addCommand(new HelpCommand());
            Manager.getManager().addCommand(new CreateCommand());
            Manager.getManager().addCommand(new ModuleCommand());

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
                String line;
                while((line = reader.readLine()) != null) {
                    if(line.length() != 0) {
                        for(Command command : Manager.getManager().commands) {
                            command.execute(line.split(" "));
                        }
                    }
                }

            } catch (Exception e) {
                new logger(loglevel.ERROR, e.getMessage() + " at " + e.getClass().getName());
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static Server getServer() {
        return serverInstance;
    }
}
