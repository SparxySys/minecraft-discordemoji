package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TextComponentString.class)
public abstract class ChatTransformer implements ITextComponent {

    @Override
    public String getFormattedText() {
        String text = ITextComponent.super.getFormattedText();
        return EmojiTransformer.getInstance().transformEmojis(text);
    }
}
