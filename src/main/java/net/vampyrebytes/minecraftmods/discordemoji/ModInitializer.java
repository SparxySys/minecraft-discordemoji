package net.vampyrebytes.minecraftmods.discordemoji;

public class ModInitializer implements net.fabricmc.api.ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EmojiTransformer.getInstance(); // load emoji table and compile regex
    }
}
