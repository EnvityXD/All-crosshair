package dev.envity.crosshair;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class CrosshairClient implements ClientModInitializer {
    public static Config config = Config.load();
    @Override public void onInitializeClient() {
        var category = KeyBinding.Category.create(Identifier.of("envity_crosshair", "settings"));
        var menu = KeyBindingHelper.registerKeyBinding(new KeyBinding("ENVITY Crosshair settings", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, category));
        var toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle ENVITY Crosshair", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, category));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menu.wasPressed()) client.setScreen(new SettingsScreen(client.currentScreen));
            while (toggle.wasPressed()) {
                config.enabled = !config.enabled; config.save();
                if (client.player != null) client.player.sendMessage(Text.literal("ENVITY Crosshair: " + (config.enabled ? "ON" : "OFF")), true);
            }
        });
    }
}
