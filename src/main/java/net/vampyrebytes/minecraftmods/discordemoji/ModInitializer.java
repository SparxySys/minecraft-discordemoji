package net.vampyrebytes.minecraftmods.discordemoji;

public class ModInitializer implements net.fabricmc.api.ModInitializer {

    @Override
    public void onInitialize() {
        EmojiTransformer.getInstance(); // load emoji table and compile regex
    }
}
