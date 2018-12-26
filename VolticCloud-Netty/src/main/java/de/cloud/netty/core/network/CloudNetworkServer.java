package de.cloud.netty.core.network;

import de.cloud.netty.core.network.packet.PacketRegistry;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;

import java.net.InetSocketAddress;

public class CloudNetworkServer {

    private static final boolean EPOLL = Epoll.isAvailable();

    public CloudNetworkServer(InetSocketAddress bindAddress, PacketRegistry packetRegistry, SimpleChannelInboundHandler inboundHandler) {

    }

    public void tryBind() {

    }

    public void tryUnbind() {

    }
}
