package me.tl0x.ferdieclient.helpers.font.glyph;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.Objects;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11C.*;

public class GlyphRenderer {

    private final int[] colorCode = new int[32];
    private final GlyphPage regularGlyphPage;
    private final GlyphPage boldGlyphPage;
    private final GlyphPage italicGlyphPage;
    private final GlyphPage boldItalicGlyphPage;
    public int size = -1;

    private float posX;
    private float posY;
    private boolean boldStyle;
    private boolean italicStyle;
    private boolean underlineStyle;
    private boolean strikethroughStyle;

    public GlyphRenderer(GlyphPage regularGlyphPage, GlyphPage boldGlyphPage, GlyphPage italicGlyphPage, GlyphPage boldItalicGlyphPage) {
        this.regularGlyphPage = regularGlyphPage;
        this.boldGlyphPage = boldGlyphPage;
        this.italicGlyphPage = italicGlyphPage;
        this.boldItalicGlyphPage = boldItalicGlyphPage;

        for (int i = 0; i < 32; ++i) {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i & 1) * 170 + j;

            if (i == 6) {
                k += 85;
            }

            if (i >= 16) {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }

            this.colorCode[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
        }
    }

    public static GlyphRenderer createFromID(String id, int size, boolean bold, boolean italic, boolean boldItalic) {
        char[] chars = new char[256];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) i;
        }

        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(GlyphRenderer.class.getClassLoader().getResourceAsStream(id))).deriveFont(Font.PLAIN, size);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GlyphPage regularPage;

        regularPage = new GlyphPage(font, true, true);
        regularPage.create(chars);
        regularPage.setUpTexture();

        GlyphPage boldPage = regularPage;
        GlyphPage italicPage = regularPage;
        GlyphPage boldItalicPage = regularPage;

        try {
            if (bold) {
                boldPage = new GlyphPage(Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(GlyphRenderer.class.getClassLoader().getResourceAsStream(id)))
                        .deriveFont(Font.BOLD, size), true, true);

                boldPage.create(chars);
                boldPage.setUpTexture();
            }

            if (italic) {
                italicPage = new GlyphPage(Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(GlyphRenderer.class.getClassLoader().getResourceAsStream(id)))
                        .deriveFont(Font.ITALIC, size), true, true);

                italicPage.create(chars);
                italicPage.setUpTexture();
            }

            if (boldItalic) {
                boldItalicPage = new GlyphPage(Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(GlyphRenderer.class.getClassLoader().getResourceAsStream(id)))
                        .deriveFont(Font.BOLD | Font.ITALIC, size), true, true);

                boldItalicPage.create(chars);
                boldItalicPage.setUpTexture();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        GlyphRenderer gp = new GlyphRenderer(regularPage, boldPage, italicPage, boldItalicPage);
        gp.size = size;
        return gp;
    }

    public int drawCenteredString(MatrixStack matrices, String text, double x, double y, int color) {
        return drawString(matrices, text, (float) x - getStringWidth(text) / 2f, (float) y, color, false);
    }

    public int drawString(MatrixStack matrices, String text, float x, float y, int color, boolean dropShadow) {
        this.resetStyles();
        int i;

        if (dropShadow) {
            i = this.renderString(matrices, text, x + .5F, y + .5F, color, true);
            i = Math.max(i, this.renderString(matrices, text, x, y, color, false));
        } else {
            i = this.renderString(matrices, text, x, y, color, false);
        }

        return i;
    }

    private int renderString(MatrixStack matrices, String text, float x, float y, int color, boolean dropShadow) {
        if (text == null) {
            return 0;
        } else {

            if ((color & 0xfc000000) == 0) {
                color |= 0xff000000;
            }

            if (dropShadow) {
                color = 0xFF323232;
            }
            this.posX = x * 2.0f;
            this.posY = y * 2.0f;
            this.renderStringAtPos(matrices, text, dropShadow, color);
            return (int) (this.posX / 4.0f);
        }
    }

    private void renderStringAtPos(MatrixStack matrices, String text, boolean shadow, int color) {
        GlyphPage glyphPage = getCurrentGlyphPage();
        float alpha = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        matrices.push();

        matrices.scale(0.5F, 0.5F, 0.5F);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableTexture();

        glyphPage.bindTexture();

        GlStateManager._texParameter(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        for (int i = 0; i < text.length(); ++i) {
            char c0 = text.charAt(i);
            if (c0 == '§' && i + 1 < text.length()) {
                int i1 = "0123456789abcdefklmnor".indexOf(text.toLowerCase().charAt(i + 1));
                if (i1 < 16) {
                    resetStyles();
                    if (i1 < 0) {
                        i1 = 15;
                    }

                    if (shadow) {
                        i1 += 16;
                    }

                    int j1 = this.colorCode[i1];

                    r = (float) (j1 >> 16 & 255) / 255.0F;
                    g = (float) (j1 >> 8 & 255) / 255.0F;
                    b = (float) (j1 & 255) / 255.0F;
                } else if (i1 == 17) {
                    this.boldStyle = true;
                } else if (i1 == 18) {
                    this.strikethroughStyle = true;
                } else if (i1 == 19) {
                    this.underlineStyle = true;
                } else if (i1 == 20) {
                    this.italicStyle = true;
                } else if (i1 == 21) {
                    resetStyles();
                    alpha = (float) (color >> 24 & 255) / 255.0F;
                    r = (float) (color >> 16 & 255) / 255.0F;
                    g = (float) (color >> 8 & 255) / 255.0F;
                    b = (float) (color & 255) / 255.0F;
                }

                ++i;
            } else {
                float f = glyphPage.drawChar(matrices, c0, posX, posY, r, b, g, alpha);

                drawUnderlineStrikethrough(f, glyphPage);
                this.posX += f;
            }
        }
        matrices.pop();
    }

    private void drawUnderlineStrikethrough(float f, GlyphPage glyphPage) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        GlStateManager._disableTexture();
        if (this.strikethroughStyle) {
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
            bufferBuilder.vertex(this.posX, this.posY + (float) (glyphPage.getMaxFontHeight() / 2), 0.0D).next();
            bufferBuilder.vertex(this.posX + f, this.posY + (float) (glyphPage.getMaxFontHeight() / 2), 0.0D).next();
            bufferBuilder.vertex(this.posX + f, this.posY + (float) (glyphPage.getMaxFontHeight() / 2) - 1.0F, 0.0D).next();
            bufferBuilder.vertex(this.posX, this.posY + (float) (glyphPage.getMaxFontHeight() / 2) - 1.0F, 0.0D).next();
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            GlStateManager._enableTexture();
        }

        if (this.underlineStyle) {
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
            int l = this.underlineStyle ? -1 : 0;
            bufferBuilder.vertex(this.posX + (float) l, this.posY + (float) glyphPage.getMaxFontHeight(), 0.0D).next();
            bufferBuilder.vertex(this.posX + f, this.posY + (float) glyphPage.getMaxFontHeight(), 0.0D).next();
            bufferBuilder.vertex(this.posX + f, this.posY + (float) glyphPage.getMaxFontHeight() - 1.0F, 0.0D).next();
            bufferBuilder.vertex(this.posX + (float) l, this.posY + (float) glyphPage.getMaxFontHeight() - 1.0F, 0.0D).next();
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            GlStateManager._enableTexture();
        }
    }


    private GlyphPage getCurrentGlyphPage() {
        if (boldStyle && italicStyle) {
            return boldItalicGlyphPage;
        } else if (boldStyle) {
            return boldGlyphPage;
        } else if (italicStyle) {
            return italicGlyphPage;
        } else {
            return regularGlyphPage;
        }
    }

    private void resetStyles() {
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

    public int getStringWidth(String text) {
        if (text == null) {
            return 0;
        }
        int width = 0;

        GlyphPage currentPage;

        int size = text.length();

        boolean on = false;

        for (int i = 0; i < size; i++) {
            char character = text.charAt(i);

            if (character == '§') {
                on = true;
            } else if (on && character >= '0' && character <= 'r') {
                int colorIndex = "0123456789abcdefklmnor".indexOf(character);
                if (colorIndex < 16) {
                    boldStyle = false;
                    italicStyle = false;
                } else if (colorIndex == 17) {
                    boldStyle = true;
                } else if (colorIndex == 20) {
                    italicStyle = true;
                } else if (colorIndex == 21) {
                    boldStyle = false;
                    italicStyle = false;
                }
                on = false;
            } else {
                on = false;
                character = text.charAt(i);

                currentPage = getCurrentGlyphPage();

                width += currentPage.getWidth(character) - 8;
            }
        }

        return width / 2;
    }

    public int getSize() {
        return size;
    }
}
