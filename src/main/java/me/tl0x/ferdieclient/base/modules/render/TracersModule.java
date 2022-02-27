package me.tl0x.ferdieclient.base.modules.render;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.render.Renderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class TracersModule extends Module {

    public TracersModule() {
        super("Tracers", "Draws a line to other players");
        this.setModuleType(ModuleType.MODULETYPE_RENDER);
    }

    @Override
    public void onRender(MatrixStack matrices, float tickDelta) {
        Vec3d crosshairpos = Renderer.getCrosshairVector();
        Iterable<Entity> temp = Objects.requireNonNull(FerdieClient.client.world).getEntities();
        List<Entity> entities = new ArrayList<>(StreamSupport.stream(temp.spliterator(),false).toList());

        for (Entity e: entities) {
            if (e instanceof PlayerEntity && e != FerdieClient.client.player) {
                Renderer.renderLine3d(crosshairpos, e.getPos().add(new Vec3d(0,1,0)), Color.WHITE, matrices);
            }
        }
        super.onRender(matrices, tickDelta);
    }
}
