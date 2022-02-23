package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.base.modules.exploit.DupeModule;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.base.bases.Module;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShulkerBoxScreen.class)
public abstract class ShulkerBoxScreenMixin extends HandledScreen<ShulkerBoxScreenHandler> {
    public ShulkerBoxScreenMixin(ShulkerBoxScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        Module dm = ModuleReg.getModulebyClass(DupeModule.class);

        ButtonWidget e = new ButtonWidget((this.width - 50)/3, this.height/8, 50, 20, Text.of("Dupe"), button -> {
            ((DupeModule) dm).startDuping = true;
        });
        if (dm.isEnabled) {
            this.addDrawableChild(e);
        }

        super.init();
    }
}