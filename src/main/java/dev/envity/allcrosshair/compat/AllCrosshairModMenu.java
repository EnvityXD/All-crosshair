package dev.envity.allcrosshair.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.envity.allcrosshair.SettingsScreen;

/**
 * Adds the All-crosshair configuration button to Mod Menu.
 * Opens the shared settings screen and returns to its parent on Done.
 */
public final class AllCrosshairModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return SettingsScreen::new;
    }
}
