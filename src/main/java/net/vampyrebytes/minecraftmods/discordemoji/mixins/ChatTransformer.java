package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(TranslatableText.class)
public abstract class ChatTransformer {

    @Shadow
    List<StringVisitable> translations;

    @Inject(
            method = "visitSelf",
            at = @At("HEAD")
    )
    public void visitSelfInjection(CallbackInfoReturnable<?> callbackInfo) {
        addEmojisToPlainTexts();
    }
    private void addEmojisToPlainTexts() {
        List<StringVisitable> newTexts = new ArrayList<>();
        EmojiTransformer instance = EmojiTransformer.getInstance();
        for (StringVisitable translation : this.translations) {
            if (!(translation instanceof Text)) {
                newTexts.add(StringVisitable.plain(instance.transformEmojis(translation.getString())));
            } else {
                newTexts.add(translation);
            }
        }
        this.translations.clear();
        this.translations.addAll(newTexts);
    }
}
