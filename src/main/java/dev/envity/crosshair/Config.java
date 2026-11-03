package dev.envity.crosshair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.*;
import java.io.IOException;
import org.slf4j.LoggerFactory;

public final class Config {
    public boolean enabled = true;
    public boolean indicator = true;
    public boolean centered = true;
    public boolean rearView = true;
    public boolean frontView = true;
    public boolean playersOnly = true;
    public int color = 0xFF55FF55;
    public int radius = 7;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("envity-crosshair.json");
    public static Config load() {
        try {
            if (Files.exists(FILE)) {
                Config config = GSON.fromJson(Files.readString(FILE), Config.class);
                if (config != null) { config.radius = Math.clamp(config.radius, 4, 16); return config; }
            }
        } catch (IOException | RuntimeException e) { LoggerFactory.getLogger("envity_crosshair").warn("Cannot read settings; using defaults", e); }
        return new Config();
    }
    public void save() {
        try { Files.createDirectories(FILE.getParent()); Files.writeString(FILE, GSON.toJson(this)); }
        catch (IOException e) { LoggerFactory.getLogger("envity_crosshair").error("Cannot save settings", e); }
    }
}
