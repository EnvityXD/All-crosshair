package dev.envity.allcrosshair.mixin;
import dev.envity.allcrosshair.CrosshairClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.Identifier;
import net.minecraft.client.gl.RenderPipelines;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Preserves vanilla HUD rendering while adding centering, F5 visibility and a reach indicator. */
@Mixin(InGameHud.class)
public abstract class HudMixin {
    @Unique private static final Identifier allcrosshair$indicatorTexture = Identifier.of("all_crosshair", "hud/indicator_corners");
    @Unique private boolean allcrosshair$matrixPushed;
    @Redirect(method="renderCrosshair", at=@At(value="INVOKE",target="Lnet/minecraft/client/option/Perspective;isFirstPerson()Z"))
    private boolean allcrosshair$perspective(Perspective perspective) {
        var c=CrosshairClient.config;
        return perspective.isFirstPerson() || c.enabled && (perspective==Perspective.THIRD_PERSON_BACK ? c.rearView : c.frontView);
    }
    @Inject(method="renderCrosshair",at=@At("HEAD"))
    private void allcrosshair$center(DrawContext ctx, RenderTickCounter tick, CallbackInfo ci) {
        allcrosshair$matrixPushed = CrosshairClient.config.enabled && CrosshairClient.config.centered;
        if(allcrosshair$matrixPushed) {
            ctx.getMatrices().pushMatrix();
            // Align to the physical framebuffer center, including non-divisible GUI scales.
            var window=MinecraftClient.getInstance().getWindow();
            double scale=window.getScaleFactor();
            float dx=(float)(window.getFramebufferWidth()/(2.0*scale)-((ctx.getScaledWindowWidth()-15)/2+7.5));
            float dy=(float)(window.getFramebufferHeight()/(2.0*scale)-((ctx.getScaledWindowHeight()-15)/2+7.5));
            ctx.getMatrices().translate(dx,dy);
        }
    }
    @Inject(method="renderCrosshair",at=@At("RETURN"))
    private void allcrosshair$indicator(DrawContext ctx, RenderTickCounter tick, CallbackInfo ci) {
        try {
        var c=CrosshairClient.config; var mc=MinecraftClient.getInstance();
        if(!c.enabled || !c.indicator || mc.player==null || mc.world==null || mc.player.isSpectator()) return;
        if(mc.debugHudEntryList.isEntryVisible(DebugHudEntries.THREE_DIMENSIONAL_CROSSHAIR)) return;
        var p=mc.options.getPerspective();
        if(!p.isFirstPerson() && !(p==Perspective.THIRD_PERSON_BACK ? c.rearView : c.frontView)) return;
        if(!(mc.crosshairTarget instanceof EntityHitResult hit)) return;
        var entity=hit.getEntity();
        if(!(entity instanceof LivingEntity living) || !living.isAlive() || entity==mc.player || (c.playersOnly && !(entity instanceof PlayerEntity))) return;
        // Use the player's eye and actual interaction range, never camera distance or an expanded reach.
        var attackRange=mc.player.getActiveOrMainHandStack().get(DataComponentTypes.ATTACK_RANGE);
        if(attackRange!=null) { if(!attackRange.isWithinRange(mc.player,hit.getPos())) return; }
        else {
            double reach = mc.player.getEntityInteractionRange();
            if(mc.player.getEyePos().squaredDistanceTo(hit.getPos()) > reach * reach) return;
        }
        if(!mc.player.canSee(entity)) return;
        int x=(ctx.getScaledWindowWidth()-1)/2, y=(ctx.getScaledWindowHeight()-1)/2, r=c.radius, col=c.color|0xFF000000;
        // Four disconnected L-shaped corners; use vanilla inverse-color blending by default.
        // Keep the original crosshair and cooldown bar untouched underneath.
        ctx.drawGuiTexture(col == 0xFFFFFFFF ? RenderPipelines.CROSSHAIR : RenderPipelines.GUI_TEXTURED,
                allcrosshair$indicatorTexture,
                x-r, y-r, r*2+1, r*2+1, col);
        } finally {
            if (allcrosshair$matrixPushed) {
                ctx.getMatrices().popMatrix();
                allcrosshair$matrixPushed = false;
            }
        }
    }
}
