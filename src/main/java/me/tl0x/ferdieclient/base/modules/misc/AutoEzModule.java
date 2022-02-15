package me.tl0x.ferdieclient.base.modules.misc;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;

public class AutoEzModule extends Module{

    public AutoEzModule() {
        super("AutoEz", "Sends 'ez' in chat when you kill someone");
        EventHandler.registerEventHandler(EventType.PACKET_RECIEVE, event -> {
            if (!this.isEnabled) {
                return;
            }

            PacketEvent pe = (PacketEvent) event;
            if (pe.getPacket() instanceof DeathMessageS2CPacket) {
                DeathMessageS2CPacket deathPacket = (DeathMessageS2CPacket) pe.getPacket();
                if (FerdieClient.client.world.getEntityById(deathPacket.getEntityId()) instanceof PlayerEntity && deathPacket.getKillerId() == FerdieClient.client.player.getId()) {
                    FerdieClient.client.player.sendChatMessage("ez");
                }
            }
        });

        this.setModuleType(ModuleType.MODULETYPE_MISC);
    }
}
