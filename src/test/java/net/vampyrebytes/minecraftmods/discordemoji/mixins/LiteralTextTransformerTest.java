package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LiteralTextTransformerTest {

    @Mock
    private EmojiTransformer emojiTransformer;
    private EmojiTransformer realEmojiTransformer;

    private LiteralTextTransformer textTransformer;

    @BeforeEach
    public void setUp() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> instanceHolder = Class.forName(EmojiTransformer.class.getCanonicalName() + "$InstanceHolder");
        Field instanceField = instanceHolder.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        realEmojiTransformer = (EmojiTransformer) instanceField.get(null);
        instanceField.set(null, emojiTransformer);
        when(emojiTransformer.transformEmojis(any())).thenAnswer(answer -> answer.getArgument(0) + " transformed");
    }

    @AfterEach
    public void tearDown() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> instanceHolder = Class.forName(EmojiTransformer.class.getCanonicalName() + "$InstanceHolder");
        Field instanceField = instanceHolder.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, realEmojiTransformer);
    }

    @Test
    public void delegatesToEmojiTransformerCorrectlyForAsString() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> instanceHolder = Class.forName(EmojiTransformer.class.getCanonicalName() + "$InstanceHolder");
        Field instanceField = instanceHolder.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, emojiTransformer);

        final String input = "Hello this is some text";
        textTransformer = new DummyLiteralTextTransformer(input);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        assertThat(textTransformer.asString()).isEqualTo(input + " transformed");
        verify(emojiTransformer).transformEmojis(captor.capture());
        assertThat(captor.getValue()).isEqualTo(input);
    }

    @Test
    public void delegatesToEmojiTransformerCorrectlyForGetRawString() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> instanceHolder = Class.forName(EmojiTransformer.class.getCanonicalName() + "$InstanceHolder");
        Field instanceField = instanceHolder.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, emojiTransformer);

        final String input = "Hello this is some text";
        textTransformer = new DummyLiteralTextTransformer(input);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        assertThat(textTransformer.getRawString()).isEqualTo(input + " transformed");
        verify(emojiTransformer).transformEmojis(captor.capture());
        assertThat(captor.getValue()).isEqualTo(input);
    }

    private static class DummyLiteralTextTransformer extends LiteralTextTransformer {
        private DummyLiteralTextTransformer(String string) {
            this.string = string;
        }
    }
}
