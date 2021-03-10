package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatTransformerTest {

    @Mock
    private EmojiTransformer emojiTransformer;

    @Test
    public void delegatesToEmojiTransformerCorrectly() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> instanceHolder = Class.forName(EmojiTransformer.class.getCanonicalName() + "$InstanceHolder");
        Field instanceField = instanceHolder.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, emojiTransformer);

        final String input = "Hello this is some text";
        DummyChatTransformer chatTransformer = new DummyChatTransformer(input);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        chatTransformer.getString();
        verify(emojiTransformer).transformEmojis(captor.capture());
        assertThat(captor.getValue()).isEqualTo(input);
    }

    private static class DummyChatTransformer extends ChatTransformer {
        private final String input;

        private DummyChatTransformer(String input) {
            this.input = input;
        }

        @Override public Style getStyle() {
            return null;
        }

        @Override public String asString() {
            return input;
        }

        @Override public List<Text> getSiblings() {
            return Collections.emptyList();
        }

        @Override public MutableText copy() {
            return null;
        }

        @Override public MutableText shallowCopy() {
            return null;
        }

        @Override public OrderedText asOrderedText() {
            return null;
        }
    }
}
