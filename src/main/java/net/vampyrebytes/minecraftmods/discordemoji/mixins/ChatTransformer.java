package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.minecraft.text.BaseText;
import net.minecraft.text.Text;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BaseText.class)
public abstract class ChatTransformer implements Text {

    @Override
    public String getString() {
        String text = Text.super.getString();
        return EmojiTransformer.getInstance().transformEmojis(text);
    }
}
