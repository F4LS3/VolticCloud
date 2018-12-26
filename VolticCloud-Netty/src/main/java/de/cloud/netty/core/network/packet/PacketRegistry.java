package de.cloud.netty.core.network.packet;

import de.cloud.netty.core.network.utils.CloudPacketDirection;

import java.util.ArrayList;
import java.util.List;

public class PacketRegistry {

    private List<Class<? extends Packet>> IN_PACKETS = new ArrayList<Class<? extends Packet>>();
    private List<Class<? extends Packet>> OUT_PACKETS = new ArrayList<Class<? extends Packet>>();

    public PacketRegistry() {

    }

    public void addPacket(CloudPacketDirection packetDirection, Class<? extends Packet> packetClass) {
        if(packetDirection.equals(CloudPacketDirection.IN)) {
            IN_PACKETS.add(packetClass);

        } else if(packetDirection.equals(CloudPacketDirection.OUT)) {
            OUT_PACKETS.add(packetClass);

        } else if(packetDirection.equals(CloudPacketDirection.BOTH)) {
            IN_PACKETS.add(packetClass);
            OUT_PACKETS.add(packetClass);

        } else {
            System.out.println("FATAL: PacketRegistry cannot find CloudPacketDirection!");
        }
    }
}
