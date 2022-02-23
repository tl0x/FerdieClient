package me.tl0x.ferdieclient.base.modules.render;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;

public class FullBrightModule extends Module {

    double normGamma;

    public FullBrightModule() {
        super("FullBright", "Makes everything bright");
        this.setModuleType(ModuleType.MODULETYPE_RENDER);
    }

    @Override
    public void onEnable() {
        normGamma = FerdieClient.client.options.gamma;
        FerdieClient.client.options.gamma = 16;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        FerdieClient.client.options.gamma = normGamma;
        super.onDisable();
    }
}
