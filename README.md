# All-crosshair — Fabric 1.21.11

> **Disclosure:** This mod was created with AI assistance and reviewed by a human.  
> This statement describes the maintainer's authorship process; it is **not** an independent audit, security review, or guarantee of correctness.

Client-side crosshair controls for Minecraft 1.21.11, with optional [Mod Menu](https://modrinth.com/mod/modmenu) integration.

**All-crosshair** combines three crosshair features into a single lightweight client-side mod:

- **Reach indicator** — Displays the familiar four-corner indicator around the crosshair when the targeted player is within normal attack reach.
- **Physical crosshair centering** — Keeps the vanilla crosshair physically centered in the framebuffer, including odd-sized windows and different GUI scales.
- **F5 crosshair visibility** — Independently control whether the crosshair is visible in rear and front third-person views.

The vanilla crosshair texture and attack cooldown indicator are still rendered by Minecraft. Resource-pack crosshair textures remain supported.

> **Compatibility:** Other mods that modify crosshair rendering or HUD placement may conflict with All-crosshair.

![All-crosshair sketch logo](src/main/resources/assets/all_crosshair/icon.png)

---

## Features

### Reach Indicator

The indicator uses Minecraft's normal targeting system and the player's current entity interaction range.

It:

- Checks whether the targeted player is within normal reach.
- Checks line of sight.
- Does **not** require a full attack cooldown.
- Does **not** extend attack reach.
- Does **not** automatically attack.
- Acts only as a client-side visual estimate and cannot guarantee that a server will accept an attack.

In third person, the indicator is shown only when the vanilla crosshair has a valid target that is also within the player's eye-based interaction range.

### Original 1:1 Indicator Size

For the original **1:1 Crosshair Indicator appearance**, use:

**Indicator size: `7` GUI pixels**

The default indicator uses the vanilla inverted-color style and the original four-corner appearance.

The color button cycles through:

- White — vanilla-style inversion
- Green
- Red
- Cyan
- Yellow

A color swatch displays the currently selected color.

The indicator size can be cycled from **4–16 GUI pixels**.

---

## Crosshair Centering

All-crosshair performs physical framebuffer centering of the vanilla crosshair rather than relying solely on GUI-coordinate positioning.

This is intended to keep the crosshair centered correctly across:

- Odd and even window dimensions
- Different GUI scales
- Resizing
- Non-standard aspect ratios

The vanilla crosshair rendering pipeline remains responsible for the actual crosshair texture and attack cooldown indicator.

---

## F5 Crosshair

Crosshair visibility in third person can be controlled independently for:

- **Rear third person**
- **Front third person**

Front-view crosshairs are visual aids only. They do **not** change the player's actual attack direction.

---

## Settings

All-crosshair provides separate toggles for:

- Master enable
- Reach indicator
- Crosshair centering
- F5 rear-view crosshair
- F5 front-view crosshair
- Players-only targeting

Settings are saved immediately to:

```text
config/all-crosshair.json
```

### Configuration

With [Mod Menu](https://modrinth.com/mod/modmenu) 17.x installed:

**Mods → All-crosshair → Configure**

Mod Menu is optional. You can also open the settings screen using the default:

**Right Shift**

The settings shortcut can be remapped under:

**Options → Controls → Key Binds**

A separate keybind can also be assigned to the master toggle. It is unbound by default.

### Advanced Configuration

Advanced users can edit `config/all-crosshair.json` directly while Minecraft is closed.

The configuration supports values such as:

- Indicator color — signed decimal ARGB
- Indicator radius/size
- Individual feature toggles

Settings persist automatically.

---

## Installation

### Requirements

- Minecraft **1.21.11**
- Fabric Loader **0.18.4 or newer**
- Java **21**
- Fabric API for Minecraft 1.21.11

### Install

1. Download the latest release from [GitHub Releases](https://github.com/EnvityXD/All-crosshair/releases/latest).
2. Place the JAR in your Minecraft instance's `mods` folder.
3. Remove or disable these older/overlapping mods:
   - Crosshair Indicator
   - Centered Crosshair
   - F5 Crosshair
4. Start Minecraft.
5. Open **Mods → All-crosshair → Configure** if Mod Menu is installed, or press **Right Shift** in-game.

### Upgrading from Envity Crosshair

When upgrading from version **1.0.0**, the old configuration is automatically migrated:

```text
config/envity-crosshair.json
        ↓
config/all-crosshair.json
```

Feature toggles and custom colors are retained. The previous green default is migrated to the new white vanilla-style default.

> **Important:** Remove the previous Envity Crosshair JAR when upgrading to All-crosshair.

### Dedicated Servers

All-crosshair is a **client-side mod**.

**Do not install it on a dedicated server.**

---

## Compatibility

All-crosshair is designed to work alongside vanilla Minecraft's existing crosshair and cooldown rendering.

Resource-pack crosshair textures remain supported.

Potential conflicts may occur with other mods that modify:

- Crosshair rendering
- HUD rendering
- Crosshair positioning
- Third-person crosshair visibility

Testing alongside **Sodium** and your normal HUD mods is recommended.

---

## Build

All-crosshair is built using **JDK 21**.

### Windows

```text
gradlew.bat build
```

### Linux / macOS

```text
./gradlew build
```

The compiled JAR is produced at:

```text
build/libs/all-crosshair-1.1.2.jar
```

Dependencies are pinned in `build.gradle`.

---

## Verification

The project has been built against the exact **Minecraft 1.21.11 Yarn mappings** and **Fabric API**.

The following checks have passed:

- Project build
- Mod Menu API integration compilation
- Configuration migration
- Configuration persistence

### Not yet live-tested

Live Minecraft visual and gameplay validation has **not** been performed in the current development workspace.

Recommended in-game verification includes:

- Toggle every feature independently.
- Restart Minecraft and verify settings persist.
- Test players just inside and outside normal attack reach.
- Test targets behind solid blocks/walls.
- Verify the indicator while the attack cooldown is recharging.
- Switch between first person, rear F5, and front F5.
- Test front F5 while aiming at yourself.
- Test multiple GUI scales.
- Test odd and even window dimensions.
- Test F1 / HUD-hidden mode.
- Test debug view.
- Test spectator mode.
- Verify vanilla and resource-pack crosshair textures.
- Verify the attack cooldown indicator.
- Confirm no crosshair overlays appear in menus.
- Test alongside Sodium and commonly used HUD mods.

---

## References

Feature references:

- [Crosshair Indicator](https://modrinth.com/mod/crosshair-indicator)
- [Centered Crosshair](https://modrinth.com/project/xUTHly0N)
- [F5 Crosshair](https://modrinth.com/project/EsJaRa1U)

---

## Third-Party Notices

Version **1.1.1** uses the original Crosshair Indicator sprite and its native **15×15 placement**.

See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) for the applicable **CC0 license** information.

---

## Release History

### 1.1.2

- Current release.
- Includes the combined All-crosshair feature set.
- Configuration and migration support.
- Mod Menu integration.
- Adjustable indicator size and color.

### 1.1.1

- Uses the original Crosshair Indicator sprite.
- Restores native **15×15 placement**.
- See `THIRD_PARTY_NOTICES.md` for CC0 licensing information.

### 1.1.0

- Rebranded the project to **All-crosshair**.
- Added the sketch logo.
- Added proper Mod Menu configuration integration.

### 1.0.1

- Fixed the reach indicator appearance.
- Replaced the previous side brackets with four separate corner marks.
- White uses the vanilla crosshair blending pipeline.
- Optional colored indicators remain configurable.

See [CHANGELOG.md](CHANGELOG.md) for detailed changes.

---

## File Guide

GitHub's file listing shows the latest commit message, rather than a description of each file.

The complete file-by-file reference is available in [docs/FILES.md](docs/FILES.md).

| Area | Purpose |
| --- | --- |
| `src/main/java/dev/envity/allcrosshair` | Client initialization, configuration, persistence, and the in-game settings screen |
| `src/main/java/dev/envity/allcrosshair/mixin` | Vanilla HUD hooks for F5 visibility, physical centering, and reach indication |
| `src/main/resources` | Mod metadata, mixin configuration, translations, and the corner indicator sprite |
| `gradle/wrapper` | Pinned Gradle launcher for reproducible builds |
| `docs` | File reference and release documentation |

---

## Authors

**Envity and AI**

AI was used during development with human review of the resulting code.

---

## License

See the repository's license and `THIRD_PARTY_NOTICES.md` for licensing information.
