package me.tl0x.ferdieclient.helpers.font.glyph;

import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class GlyphPage {

    private final Font font;
    private final boolean antiA;
    private final boolean fracMetrics;
    private int imgSize;
    private int maxFontHeight = -1;
    private BufferedImage bufferedImage;
    private AbstractTexture loadedTexture;
    public HashMap<Character, Glyph> glyphHashMap = new HashMap<>();

    public GlyphPage(Font font, boolean antiA, boolean fracMetrics) {
        this.font = font;
        this.antiA = antiA;
        this.fracMetrics = fracMetrics;
    }

    FontRenderContext frc;
    public void create(char[] chars){
        double maxWidth = -1;
        double maxHeight = -1;

        AffineTransform affineTransform = new AffineTransform();
        FontRenderContext fontRenderContext = new FontRenderContext(affineTransform, antiA, fracMetrics);
        this.frc = fontRenderContext;

        for (char ch: chars) {
            Rectangle2D bounds = font.getStringBounds(Character.toString(ch), fontRenderContext);

            if (maxWidth < bounds.getWidth()) {
                maxWidth = bounds.getWidth();
            }
            if (maxHeight < bounds.getHeight()) {
                maxHeight = bounds.getHeight();
            }
        }

        maxWidth += 2; maxHeight += 2;

        imgSize = (int) Math.ceil(Math.max(Math.ceil(Math.sqrt(maxWidth * maxWidth * chars.length) / maxWidth), Math.ceil(Math.sqrt(maxHeight * maxHeight * chars.length) / maxHeight)) * Math.max(maxWidth, maxHeight)) + 1;
        bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = bufferedImage.createGraphics();

        g.setFont(font);
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0, imgSize, imgSize);

        g.setColor(Color.WHITE);

        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fracMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiA ? RenderingHints.VALUE_ANTIALIAS_OFF : RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiA ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        FontMetrics fontMetrics = g.getFontMetrics();

        int currentCharHeight = 0;
        int posX = 0;
        int posY = 1;

        for(char ch: chars) {
            Glyph glyph = new Glyph();

            Rectangle2D bounds = fontMetrics.getStringBounds(Character.toString(ch), g);

            glyph.width = bounds.getBounds().width + 8; // Leave some additional space
            glyph.height = bounds.getBounds().height;

            if (posX + glyph.width >= imgSize) {
                posX = 0;
                posY += currentCharHeight;
                currentCharHeight = 0;
            }

            glyph.x = posX;
            glyph.y = posY;

            if (glyph.height > maxFontHeight) {
                maxFontHeight = glyph.height;
            }

            if (glyph.height > currentCharHeight) {
                currentCharHeight = glyph.height;
            }

            g.drawString(Character.toString(ch), posX+2, posY + fontMetrics.getAscent());
            posX += glyph.width;

            glyphHashMap.put(ch, glyph);
        }
    }

    public void setUpTexture() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();

            ByteBuffer data = BufferUtils.createByteBuffer(bytes.length).put(bytes);
            data.flip();
            loadedTexture = new NativeImageBackedTexture(NativeImage.read(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bindTexture() {
        RenderSystem.setShaderTexture(0, loadedTexture.getGlId());
    }

    public void unbindTexture() {
        RenderSystem.setShaderTexture(0, 0);
    }

    public float drawChar(MatrixStack stack, char ch, float x, float y, float red, float blue, float green, float alpha) {
        Glyph glyph = glyphHashMap.get(ch);

        if (glyph == null) {
            return 0;
        }

        float pageX = glyph.x / (float) imgSize;
        float pageY = glyph.y / (float) imgSize;

        float pageWidth = glyph.width / (float) imgSize;
        float pageHeight = glyph.height / (float) imgSize;

        float width = glyph.width;
        float height = glyph.height;
        Matrix4f posMat = stack.peek().getPositionMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);

        bufferBuilder.vertex(posMat, x, y + height, 0).color(red, green, blue, alpha).texture(pageX, pageY + pageHeight).next();
        bufferBuilder.vertex(posMat, x + width, y + height, 0).color(red, green, blue, alpha).texture(pageX + pageWidth, pageY + pageHeight).next();
        bufferBuilder.vertex(posMat, x + width, y, 0).color(red, green, blue, alpha).texture(pageX + pageWidth, pageY).next();
        bufferBuilder.vertex(posMat, x, y, 0).color(red, green, blue, alpha).texture(pageX, pageY).next();

        tessellator.draw();

        return width - 8;
    }

    public float getWidth(char ch) {
        Glyph g = glyphHashMap.get(ch);
        return g == null ? 0 : g.width;
    }

    public float getFontHeight(String t) {
        return (float) font.getStringBounds(t,frc).getHeight();
    }

    public int getMaxFontHeight() {
        return maxFontHeight;
    }

    public boolean isAntiAliasingEnabled() {
        return antiA;
    }

    public boolean isFractionalMetricsEnabled() {
        return fracMetrics;
    }






    static class Glyph {
        private int x;
        private int y;
        private int width;
        private int height;

        Glyph() {

        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

    }

}

