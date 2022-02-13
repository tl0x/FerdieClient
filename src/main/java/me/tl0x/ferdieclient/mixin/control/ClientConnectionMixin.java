package me.tl0x.ferdieclient.mixin.control;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.modules.DupeModule;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method="send(Lnet/minecraft/network/Packet;)V", at=@At("HEAD"),cancellable = true)
    public void send(Packet<?> packet, CallbackInfo ci) {
        if (EventHandler.fireEvent(EventType.PACKET_SEND, new PacketEvent(packet))) {
            ci.cancel();
        }
    }

    @Inject(method="send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at=@At("TAIL"))
    public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo ci) {
        DupeModule dm = (DupeModule) ModuleReg.getModulebyClass(DupeModule.class);
        if (packet instanceof PlayerActionC2SPacket p && p.getAction() == PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK &&
        dm.startDuping == true) {
            helper.quickMoveAll();
        }
    }

    @Inject(method="handlePacket", at=@At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if (EventHandler.fireEvent(EventType.PACKET_RECIEVE, new PacketEvent(packet))) {
            ci.cancel();
        }
    }
}
