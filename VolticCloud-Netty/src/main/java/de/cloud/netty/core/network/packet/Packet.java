package de.cloud.netty.core.network.packet;

import de.cloud.netty.core.network.PacketSerializer;

import java.io.IOException;

public interface Packet {

    void read(PacketSerializer packetSerializer) throws IOException;
    void write(PacketSerializer packetSerializer) throws IOException;

}
