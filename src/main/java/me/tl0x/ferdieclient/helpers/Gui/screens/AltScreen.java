package me.tl0x.ferdieclient.helpers.Gui.screens;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.helpers.auth.Account;
import me.tl0x.ferdieclient.helpers.font.FontRenderer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import me.tl0x.ferdieclient.helpers.Gui.elements.Button;
import org.apache.logging.log4j.Level;

import java.awt.*;

public class AltScreen extends Screen {

    public static AltScreen instance = null;
    TextRenderer textRenderer = FerdieClient.client.textRenderer;
    TextFieldWidget email;
    TextFieldWidget password;
    String emailInp;
    String passInp;

    private AltScreen() {
        super(Text.of(""));
    }

    public static AltScreen getInstance() {
        if(instance == null) {
            instance = new AltScreen();
        }
        return instance;
    }

    @Override
    protected void init() {
        email = new TextFieldWidget(textRenderer, width/2-100, height/2-40, width/2,40, Text.of(""));
        email.setSelectionStart(0);
        email.setText("");

        password = new TextFieldWidget(textRenderer, width/2-100, height/2+40, width/2,40, Text.of(""));
        password.setSelectionStart(0);
        password.setText("");

        Button submit = new Button(0, 0, 50,20, Text.of("Submit"), Color.BLACK, Color.WHITE, () -> {
            emailInp = email.getText().toString();
            passInp = password.getText().toString();
            Account account = new Account(emailInp, passInp);
            if (account.login()) {
                FerdieClient.log(Level.INFO, "Logged in as " + emailInp);
            }

        });

        addDrawableChild(submit);
        addDrawableChild(email);
        addDrawableChild(password);
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices, 0,0, width, height, Color.WHITE.getRGB());


        password.render(matrices, mouseX, mouseY, delta);
        email.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
