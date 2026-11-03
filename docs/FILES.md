# All-crosshair file reference

Every tracked file and its purpose. Generated Gradle Wrapper files are kept intact.

| File | Description |
| --- | --- |
| `.gitignore` | Excludes Gradle caches, compiled output, run directories and IDE-local files from commits. |
| `LICENSE` | MIT license with ENVITY attribution for this original mod. |
| `README.md` | Feature overview, installation instructions, settings, build commands and verification limits. |
| `CHANGELOG.md` | Versioned release history and upgrade notes. |
| `build.gradle` | Pins Fabric Loom, Minecraft 1.21.11, Yarn mappings, Fabric Loader and Fabric API; builds/remaps the mod and sources JARs. |
| `settings.gradle` | Names the Gradle project and configures the plugin-resolution repositories. |
| `gradle.properties` | Sets the memory limit for the Gradle build process. It does not change Minecraft memory allocation. |
| `gradlew` | Generated executable Gradle Wrapper launcher for Linux and macOS. |
| `gradlew.bat` | Generated Gradle Wrapper launcher for Windows Command Prompt and PowerShell. |
| `gradle/wrapper/gradle-wrapper.jar` | Standard Gradle bootstrap binary. Downloads/starts the pinned distribution; not the Minecraft mod. |
| `gradle/wrapper/gradle-wrapper.properties` | Pins Gradle 9.2.1 and configures its distribution download. |
| `src/main/java/dev/envity/allcrosshair/Config.java` | Defines defaults, validates size, migrates the old appearance and loads/saves config/all-crosshair.json. |
| `src/main/java/dev/envity/allcrosshair/CrosshairClient.java` | Fabric client entry point. Registers Right Shift settings and the optional master-toggle key binding. |
| `src/main/java/dev/envity/allcrosshair/SettingsScreen.java` | In-game settings UI with independent feature switches, color selection, size control and immediate persistence. |
| `src/main/java/dev/envity/allcrosshair/mixin/HudMixin.java` | Keeps vanilla HUD behavior while correcting alignment, permitting F5 rendering and checking reach/visibility before drawing the indicator. |
| `src/main/resources/fabric.mod.json` | Fabric metadata: mod ID, name, author, version, client entry point and dependency requirements. |
| `src/main/resources/all_crosshair.mixins.json` | Registers the required client HUD mixin and Java 21 compatibility. |
| `src/main/resources/assets/all_crosshair/lang/en_us.json` | English label for the All-crosshair key-binding category. |
| `src/main/resources/assets/all_crosshair/textures/gui/sprites/hud/indicator_corners.png` | Exact CC0-licensed 15×15 Crosshair Indicator sprite with the original corner geometry; overlays the crosshair for a valid target. |
| `docs/FILES.md` | This complete file-by-file reference. |
| `src/main/java/dev/envity/allcrosshair/compat/AllCrosshairModMenu.java` | Optional Mod Menu API entry point opening SettingsScreen with the correct parent. |
| `src/main/resources/assets/all_crosshair/icon.png` | Rough pencil-sketch crosshair logo, shown in Mod Menu and the README. |
| `THIRD_PARTY_NOTICES.md` | Upstream sprite source, creators and licensing details. |
| `licenses/Crosshair-Indicator-CC0.txt` | Complete CC0-1.0 license retained from the upstream artifact. |
