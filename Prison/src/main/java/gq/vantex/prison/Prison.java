package gq.vantex.prison;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import gq.vantex.prison.database.MongoDB;
import gq.vantex.prison.profile.ProfileManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Prison extends JavaPlugin {

    @Getter private static Prison instance;

    public static final Gson GSON = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();

    @Getter private MongoDB mongoDB;
    @Getter private ProfileManager profileManager;

    @Override
    public void onEnable() {
        instance = this;

        this.mongoDB = new MongoDB().load();
        this.profileManager = new ProfileManager();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
