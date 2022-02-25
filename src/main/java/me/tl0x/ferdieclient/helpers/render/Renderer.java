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





    // 3D

    public static void renderOutlineIntern(Vec3d start, Vec3d dimensions, MatrixStack stack, BufferBuilder buffer) {
        Camera c = FerdieClient.client.gameRenderer.getCamera();
        Vec3d camPos = c.getPos();
        start = start.subtract(camPos);
        Vec3d end = start.add(dimensions);
        Matrix4f matrix = stack.peek().getPositionMatrix();
        float x1 = (float) start.x;
        float y1 = (float) start.y;
        float z1 = (float) start.z;
        float x2 = (float) end.x;
        float y2 = (float) end.y;
        float z2 = (float) end.z;

        buffer.vertex(matrix, x1, y1, z1).next();
        buffer.vertex(matrix, x1, y1, z2).next();
        buffer.vertex(matrix, x1, y1, z2).next();
        buffer.vertex(matrix, x2, y1, z2).next();
        buffer.vertex(matrix, x2, y1, z2).next();
        buffer.vertex(matrix, x2, y1, z1).next();
        buffer.vertex(matrix, x2, y1, z1).next();
        buffer.vertex(matrix, x1, y1, z1).next();

        buffer.vertex(matrix, x1, y2, z1).next();
        buffer.vertex(matrix, x1, y2, z2).next();
        buffer.vertex(matrix, x1, y2, z2).next();
        buffer.vertex(matrix, x2, y2, z2).next();
        buffer.vertex(matrix, x2, y2, z2).next();
        buffer.vertex(matrix, x2, y2, z1).next();
        buffer.vertex(matrix, x2, y2, z1).next();
        buffer.vertex(matrix, x1, y2, z1).next();

        buffer.vertex(matrix, x1, y1, z1).next();
        buffer.vertex(matrix, x1, y2, z1).next();

        buffer.vertex(matrix, x2, y1, z1).next();
        buffer.vertex(matrix, x2, y2, z1).next();

        buffer.vertex(matrix, x2, y1, z2).next();
        buffer.vertex(matrix, x2, y2, z2).next();

        buffer.vertex(matrix, x1, y1, z2).next();
        buffer.vertex(matrix, x1, y2, z2).next();
    }

    public static void renderLine3d(Vec3d start, Vec3d end, Color color, MatrixStack matrices) {
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255f;
        float alpha = color.getAlpha() / 255f;
        Camera c = FerdieClient.client.gameRenderer.getCamera();
        Vec3d camPos = c.getPos();
        start = start.subtract(camPos);
        end = end.subtract(camPos);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        float x1 = (float) start.x;
        float y1 = (float) start.y;
        float z1 = (float) start.z;
        float x2 = (float) end.x;
        float y2 = (float) end.y;
        float z2 = (float) end.z;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        GL11.glDepthFunc(GL11.GL_ALWAYS);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

        buffer.vertex(matrix, x1, y1, z1).color(red, green, blue, alpha).next();
        buffer.vertex(matrix, x2, y2, z2).color(red, green, blue, alpha).next();

        buffer.end();

        BufferRenderer.draw(buffer);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        RenderSystem.disableBlend();
    }

    public static Vec3d getCrosshairVector() {

        Camera camera = FerdieClient.client.gameRenderer.getCamera();

        float vec = 0.017453292F;
        float pi = (float) Math.PI;

        float f1 = MathHelper.cos(-camera.getYaw() * vec - pi);
        float f2 = MathHelper.sin(-camera.getYaw() * vec - pi);
        float f3 = -MathHelper.cos(-camera.getPitch() * vec);
        float f4 = MathHelper.sin(-camera.getPitch() * vec);

        return new Vec3d(f2 * f3, f4, f1 * f3).add(camera.getPos());
    }

    public static void renderOutline(Vec3d start, Vec3d dimensions, Color color, MatrixStack stack) {
        RenderSystem.enableBlend();
        BufferBuilder buffer = renderPrepare(color);

        renderOutlineIntern(start, dimensions, stack, buffer);

        buffer.end();
        BufferRenderer.draw(buffer);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        RenderSystem.disableBlend();
    }

    public static BufferBuilder renderPrepare(Color color) {
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255f;
        float alpha = color.getAlpha() / 255f;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        GL11.glDepthFunc(GL11.GL_ALWAYS);
        RenderSystem.setShaderColor(red, green, blue, alpha);
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION);
        return buffer;
    }


}
