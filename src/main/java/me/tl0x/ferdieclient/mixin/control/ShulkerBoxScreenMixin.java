package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.modules.DupeModule;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ShulkerBoxScreen.class)
public abstract class ShulkerBoxScreenMixin extends HandledScreen<ShulkerBoxScreenHandler> {
    public ShulkerBoxScreenMixin(ShulkerBoxScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        Module dm = ModuleReg.getModulebyClass(DupeModule.class);

        ButtonWidget e = new ButtonWidget(150, 20, 50, 20, Text.of("Dupe"), button -> {
            ((DupeModule) dm).startDuping = true;
        });
        if (dm.isEnabled) {
            this.addDrawableChild(e);
        }

        super.init();
    }
}