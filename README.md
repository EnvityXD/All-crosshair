# ENVITY Crosshair — Fabric 1.21.11

An original client-side implementation of three crosshair features:
- A configurable colored bracket indicator when your targeted player is within attack reach.
- Physical framebuffer centering for the vanilla crosshair, including odd window sizes and GUI scaling.
- Crosshair visibility in rear/front third-person (F5), controlled independently.

The normal crosshair texture and attack cooldown indicator remain rendered by Minecraft.
Resource-pack crosshair textures remain supported. Other crosshair-rendering mods can conflict.

## Install
1. Use Minecraft **1.21.11** with Fabric Loader **0.18.4 or later**, Java 21, and Fabric API for 1.21.11.
2. Put the included envity-crosshair-1.0.0.jar in your instance's mods folder.
3. Remove/disable Crosshair Indicator, Centered Crosshair, and F5 Crosshair to avoid overlapping changes.
4. Start Minecraft and press **Right Shift** for settings.
Do not install this client-only mod on a dedicated server.

## Settings
Separate toggles: master enable, indicator, centering, F5 rear, F5 front, and players-only targeting.
Click the indicator color button to cycle green/red/cyan/yellow/white; a swatch shows the color.
Click indicator size to cycle 4–16 GUI pixels.
Controls → Key Binds allows remapping the settings shortcut and assigning a master toggle (unbound by default).
Settings persist immediately in config/envity-crosshair.json. With Minecraft closed, advanced users may edit
color (signed decimal ARGB) and radius in that file.

The indicator uses vanilla targeting and the player's current entity interaction range, checks line of sight,
and does not wait for a full attack cooldown. It does not extend reach or attack automatically.
It is a client-side estimate, not a guarantee the server will accept a hit.
In third person it only indicates a vanilla crosshair target also within reach of the player's eye.
Front-view crosshairs are visual aids and do not change the player's attack direction.

## Build
Use JDK 21:
- Windows: gradlew.bat build
- Linux/macOS: ./gradlew build
Output: build/libs/envity-crosshair-1.0.0.jar
Pinned dependencies are in build.gradle.

## Verification
Built against the exact 1.21.11 Yarn mappings and Fabric API.
Live Minecraft visual/gameplay validation has not been performed in this workspace.
Recommended checks:
- Toggle each feature independently and verify settings survive a restart.
- Aim at a player just inside/outside normal reach and behind a solid wall.
- Confirm the indicator works while the attack cooldown is recharging.
- Switch first person / rear F5 / front F5, including while aiming at yourself.
- Check several GUI scales, odd/even window sizes, F1, debug view, and spectator mode.
- Confirm vanilla/resource-pack crosshair and cooldown rendering, and no overlays in menus.
- Check alongside Sodium and your usual HUD mods.

## References
Feature inspirations, not bundled dependencies or copied mod code:
https://modrinth.com/mod/crosshair-indicator
https://modrinth.com/project/xUTHly0N
https://modrinth.com/project/EsJaRa1U

Author: ENVITY
