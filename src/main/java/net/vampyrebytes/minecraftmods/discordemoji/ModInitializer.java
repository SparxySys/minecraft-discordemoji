package net.vampyrebytes.minecraftmods.discordemoji;

import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class ModInitializer implements InitializationListener {

    @Override
    public void onInitialization() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.discordemoji.json");
        EmojiTransformer.getInstance(); // load emoji table and compile regex
    }
}
