package dev.envity.crosshair;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/** Provides in-game toggles and persistent indicator color and size controls. */
public final class SettingsScreen extends Screen {
    private final Screen parent;
    public SettingsScreen(Screen parent) { super(Text.literal("ENVITY Crosshair")); this.parent = parent; }
    private void toggle(String label, int y, BooleanSupplier get, Consumer<Boolean> set) {
        addDrawableChild(ButtonWidget.builder(Text.literal(label + ": " + (get.getAsBoolean() ? "ON" : "OFF")), b -> {
            set.accept(!get.getAsBoolean()); CrosshairClient.config.save();
            b.setMessage(Text.literal(label + ": " + (get.getAsBoolean() ? "ON" : "OFF")));
        }).dimensions(width/2-120, y, 240, 20).build());
    }
    @Override protected void init() {
        var c = CrosshairClient.config; int y = Math.max(32, height/2-118);
        toggle("Mod enabled", y, ()->c.enabled, v->c.enabled=v);
        toggle("Reach indicator", y+23, ()->c.indicator, v->c.indicator=v);
        toggle("Center crosshair", y+46, ()->c.centered, v->c.centered=v);
        toggle("F5 rear crosshair", y+69, ()->c.rearView, v->c.rearView=v);
        toggle("F5 front crosshair", y+92, ()->c.frontView, v->c.frontView=v);
        toggle("Players only", y+115, ()->c.playersOnly, v->c.playersOnly=v);
        addDrawableChild(ButtonWidget.builder(Text.literal("Indicator size: "+c.radius), b-> {
            c.radius = c.radius >= 16 ? 4 : c.radius+1; c.save(); b.setMessage(Text.literal("Indicator size: "+c.radius));
        }).dimensions(width/2-120,y+138,240,20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Indicator color (click to cycle)"), b-> {
            int[] colors={0xFFFFFFFF,0xFF55FF55,0xFFFF5555,0xFF55FFFF,0xFFFFFF55};
            int next=0; for(int i=0;i<colors.length;i++) if(c.color==colors[i]) next=(i+1)%colors.length;
            c.color=colors[next]; c.save();
        }).dimensions(width/2-120,y+161,240,20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Done"), b->close()).dimensions(width/2-120,y+184,240,20).build());
    }
    @Override public void render(DrawContext ctx,int mouseX,int mouseY,float delta) {
        super.render(ctx,mouseX,mouseY,delta);
        ctx.drawCenteredTextWithShadow(textRenderer,title,width/2,12,0xFFFFFFFF);
        ctx.fill(width/2+125,height/2-5,width/2+135,height/2+5,CrosshairClient.config.color);
    }
    @Override public void close() { CrosshairClient.config.save(); client.setScreen(parent); }
}
