package me.tl0x.ferdieclient.helpers.auth;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.mixin.control.MinecraftClientAccessor;
import net.minecraft.client.util.Session;

import java.util.Optional;

public class Account {

    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean login() {
        YggdrasilUserAuthentication auth = getAuth();
        try {
            auth.logIn();
            ((MinecraftClientAccessor) FerdieClient.client).setSession(new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), Optional.empty(), Optional.empty(), Session.AccountType.MOJANG));
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }

    }

    public YggdrasilUserAuthentication getAuth() {
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(((MinecraftClientAccessor) FerdieClient.client).getProxy(), "").createUserAuthentication(Agent.MINECRAFT);
        auth.setPassword(password);
        auth.setUsername(name);

        return auth;
    }

}
