package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.minecraft.text.LiteralText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LiteralText.class)
public abstract class LiteralTextTransformer {

    @Shadow
    String string;

    @Overwrite
    public String getRawString() {
        return EmojiTransformer.getInstance().transformEmojis(this.string);
    }

    @Overwrite
    public String asString() {
        return EmojiTransformer.getInstance().transformEmojis(this.string);
    }
}
