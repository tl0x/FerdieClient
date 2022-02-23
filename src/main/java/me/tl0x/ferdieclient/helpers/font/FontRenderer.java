package me.tl0x.ferdieclient.helpers.font;

import me.tl0x.ferdieclient.helpers.font.glyph.GlyphPage;
import me.tl0x.ferdieclient.helpers.font.glyph.GlyphRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FontRenderer {

//    public Font defaultFont = Font.createFont(Font.TRUETYPE_FONT, FontRenderer.class.getClassLoader().getResourceAsStream("Font.ttf")).deriveFont(Font.PLAIN, 30);
    private static List<GlyphRenderer> glyphRenderers = new ArrayList<>();

    public static GlyphRenderer getRenderer(int size) {
        for (GlyphRenderer g: glyphRenderers) {
            if (g.getSize() == size) {
                return g;
            }
        }
        GlyphRenderer renderer = GlyphRenderer.createFromID("Font.ttf", size, false,false,false);
        glyphRenderers.add(renderer);
        return renderer;
    }
}
