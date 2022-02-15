package me.tl0x.ferdieclient.helpers.render;

import com.mojang.blaze3d.systems.RenderSystem;
import me.tl0x.ferdieclient.FerdieClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Renderer {

    public static void fill(MatrixStack matrices, Color color, double x1, double y1, double x2, double y2) {
        RenderSystem.setShaderColor(1,1,1,1);
        int c = color.getRGB();
        double temp = 0;
        if (x1 < x2) temp = x1; x1=x2; x2=temp;
        if (y1 < y2) temp = y1; y1=y2; y2=temp;
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        float f = (float) (c >> 24 & 255) / 255.0F;
        float g = (float) (c >> 16 & 255) / 255.0F;
        float h = (float) (c >> 8 & 255) / 255.0F;
        float k = (float) (c & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, (float) x1, (float) y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float) x2, (float) y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float) x2, (float) y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float) x1, (float) y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static Vec2f renderTooltip(MatrixStack stack, double arrowX, double arrowY, double width, double height, Color color, boolean renderUpsideDown) {
        double centerX = FerdieClient.client.getWindow().getScaledWidth() / 2d;
        double centerY = FerdieClient.client.getWindow().getScaledHeight() / 2d;
            /*
            left:
            *           /\
            * --------------
            * |            |
            * |            |
            * --------------
            right:
            *   /\
            * --------------
            * |            |
            * |            |
            * --------------
            * */
        boolean placeLeft = centerX < arrowX;
            /*
            top:
            *   /\
            * --------------
            * |            |
            * |            |
            * --------------
            bottom:
            * --------------
            * |            |
            * |            |
            * --------------
            *   V
            * */
        double arrowDimX = 10;
        double arrowDimY = 5;
        double roundStartX = placeLeft ? arrowX + arrowDimX / 2d + 10 - width : arrowX - arrowDimX / 2d - 10;
        double roundStartY = renderUpsideDown ? arrowY - arrowDimY - height : arrowY + arrowDimY;
        Matrix4f mat = stack.peek().getPositionMatrix();

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.disableCull();

        renderRoundedQuadInternal(mat, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f, roundStartX, roundStartY, roundStartX + width, roundStartY + height, 5, 20);
//            RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Tessellator t = Tessellator.getInstance();
        BufferBuilder bb = t.getBuffer();
        bb.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        if (renderUpsideDown) {
            bb.vertex(mat, (float) arrowX, (float) arrowY - .5f, 0).color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f).next();
            bb.vertex(mat, (float) (arrowX - arrowDimX / 2f), (float) (arrowY - arrowDimY - .5), 0).color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f).next();
            bb.vertex(mat, (float) (arrowX + arrowDimX / 2f), (float) (arrowY - arrowDimY - .5), 0).color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f).next();
        } else {
            bb.vertex(mat, (float) arrowX, (float) arrowY + .5f, 0).color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f).next();
            bb.vertex(mat, (float) (arrowX - arrowDimX / 2f), (float) (arrowY + arrowDimY + .5), 0).color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f).next();
            bb.vertex(mat, (float) (arrowX + arrowDimX / 2f), (float) (arrowY + arrowDimY + .5), 0).color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f).next();
        }
        t.draw();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        return new Vec2f((float) roundStartX, (float) roundStartY);
    }

    public static void renderRoundedQuadInternal(Matrix4f matrix, float cr, float cg, float cb, float ca, double fromX, double fromY, double toX, double toY, double rad, double samples) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);

        double toX1 = toX - rad;
        double toY1 = toY - rad;
        double fromX1 = fromX + rad;
        double fromY1 = fromY + rad;
        double[][] map = new double[][]{new double[]{toX1, toY1}, new double[]{toX1, fromY1}, new double[]{fromX1, fromY1}, new double[]{fromX1, toY1}};
        for (int i = 0; i < 4; i++) {
            double[] current = map[i];
            for (double r = i * 90d; r < (360 / 4d + i * 90d); r += (90 / samples)) {
                float rad1 = (float) Math.toRadians(r);
                float sin = (float) (Math.sin(rad1) * rad);
                float cos = (float) (Math.cos(rad1) * rad);
                bufferBuilder.vertex(matrix, (float) current[0] + sin, (float) current[1] + cos, 0.0F).color(cr, cg, cb, ca).next();
            }
        }
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
    }

    public static void renderRoundedQuad(MatrixStack matrices, Color c, double fromX, double fromY, double toX, double toY, double rad, double samples) {
//            RenderSystem.defaultBlendFunc();

        int color = c.getRGB();
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        float f = (float) (color >> 24 & 255) / 255.0F;
        float g = (float) (color >> 16 & 255) / 255.0F;
        float h = (float) (color >> 8 & 255) / 255.0F;
        float k = (float) (color & 255) / 255.0F;
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        renderRoundedQuadInternal(matrix, g, h, k, f, fromX, fromY, toX, toY, rad, samples);

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }


    public static void renderBlockOutline(Vec3d bpos, Vec3d dimensions, int r, int g, int b, int a) {
        BlockEntityRenderDispatcher BERD = FerdieClient.client.getBlockEntityRenderDispatcher();
        Camera c = BERD.camera;
        Vec3d s = bpos.subtract(c.getPos());
        Vec3d e = s.add(dimensions);
        double f = s.x;
        double g1 = s.y;
        double h = s.z;
        double i = e.x;
        double j = e.y;
        double k = e.z;
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(2);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glRotated(MathHelper.wrapDegrees(c.getPitch()), 1, 0, 0);
        GL11.glRotated(MathHelper.wrapDegrees(c.getYaw() + 180.0), 0, 1, 0);
        GL11.glColor4f(r / 255F, g / 255F, b / 255F, a / 255F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(f, g1, h);
        GL11.glVertex3d(i, g1, h);
        GL11.glVertex3d(f, g1, h);
        GL11.glVertex3d(f, j, h);
        GL11.glVertex3d(f, g1, h);
        GL11.glVertex3d(f, g1, k);
        GL11.glVertex3d(i, g1, h);
        GL11.glVertex3d(i, j, h);
        GL11.glVertex3d(i, j, h);
        GL11.glVertex3d(f, j, h);
        GL11.glVertex3d(f, j, h);
        GL11.glVertex3d(f, j, k);
        GL11.glVertex3d(f, j, k);
        GL11.glVertex3d(f, g1, k);
        GL11.glVertex3d(f, g1, k);
        GL11.glVertex3d(i, g1, k);
        GL11.glVertex3d(i, g1, k);
        GL11.glVertex3d(i, g1, h);
        GL11.glVertex3d(f, j, k);
        GL11.glVertex3d(i, j, k);
        GL11.glVertex3d(i, g1, k);
        GL11.glVertex3d(i, j, k);
        GL11.glVertex3d(i, j, h);
        GL11.glVertex3d(i, j, k);
        GL11.glEnd();
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glPopMatrix();
    }


}
