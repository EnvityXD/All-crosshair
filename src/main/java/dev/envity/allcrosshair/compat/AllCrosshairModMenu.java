package dev.envity.allcrosshair.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.envity.allcrosshair.SettingsScreen;

/** Opens the same persistent settings screen from Mod Menu and returns to its parent on Done. */
public final class AllCrosshairModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return SettingsScreen::new;
    }
}
