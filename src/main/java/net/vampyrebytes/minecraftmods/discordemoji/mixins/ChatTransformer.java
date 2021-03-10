package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import java.util.Optional;

import net.minecraft.text.BaseText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BaseText.class)
public abstract class ChatTransformer implements Text {

    @Override
    public <T> Optional<T> visitSelf(StyledVisitor<T> visitor, Style style) {
        return visitor.accept(style, asTransformedString());
    }

    @Override
    public <T> Optional<T> visitSelf(Visitor<T> visitor) {
        return visitor.accept(asTransformedString());
    }

    String asTransformedString() {
        return EmojiTransformer.getInstance().transformEmojis(asString());
    }
}
