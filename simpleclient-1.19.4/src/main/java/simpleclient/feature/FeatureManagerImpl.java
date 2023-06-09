package simpleclient.feature;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import simpleclient.adapter.ItemRendererAdapter;
import simpleclient.adapter.ItemRendererAdapterImpl;
import simpleclient.adapter.TextRendererAdapter;
import simpleclient.adapter.TextRendererAdapterImpl;
import simpleclient.gui.EditFeaturesScreen;

public class FeatureManagerImpl extends FeatureManager {
    @Override
    public void init() {
        KeyMapping editFeaturesKey = KeyBindingHelper.registerKeyBinding(new KeyMapping("simpleclient.edit_features", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.simpleclient"));
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (editFeaturesKey.consumeClick() && minecraft.screen == null && minecraft.level != null) {
                minecraft.setScreen(new EditFeaturesScreen());
            }
        });
        addFeature(new FPS());
        addFeature(new Motionblur());
        super.init();
    }
}