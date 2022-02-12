package me.tl0x.ferdieclient.helpers.events.event;

import me.tl0x.ferdieclient.base.Event;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {

    final Packet<?> packet;
    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }


    public Packet<?> getPacket() {
        return packet;
    }
}
