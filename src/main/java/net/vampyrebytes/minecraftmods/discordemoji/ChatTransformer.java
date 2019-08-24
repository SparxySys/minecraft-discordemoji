package net.vampyrebytes.minecraftmods.discordemoji;

import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ITextComponent.class)
public interface ChatTransformer {

    @Inject(method = "getFormattedText", at = @At("RETURN"))
    default void addEmoji(CallbackInfoReturnable<String> cir) {
        String text = cir.getReturnValue();
        text = EmojiTransformer.getInstance().transformEmojis(text);
        cir.setReturnValue(text);
    }
}
