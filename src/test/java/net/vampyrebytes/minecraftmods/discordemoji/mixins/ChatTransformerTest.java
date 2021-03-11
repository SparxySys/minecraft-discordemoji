package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.minecraft.text.*;
import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatTransformerTest {

    @Mock
    private EmojiTransformer emojiTransformer;

    private EmojiTransformer realEmojiTransformer;

    @Test
    public void delegatesToEmojiTransformerCorrectly() {
        final String input1 = "Hello this is some text";
        final String input2 = "More text";
        DummyChatTransformer chatTransformer = new DummyChatTransformer();
        chatTransformer.translations = new ArrayList<>();
        UntransformableText untransformable1 = new UntransformableText();
        chatTransformer.translations.add(untransformable1);
        chatTransformer.translations.add(new TransformableText(input1));
        chatTransformer.translations.add(new TransformableText(input2));
        UntransformableText untransformable2 = new UntransformableText();
        chatTransformer.translations.add(untransformable2);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        chatTransformer.visitSelfInjection(null);
        verify(emojiTransformer, times(2)).transformEmojis(captor.capture());
        assertThat(captor.getAllValues()).containsExactly(input1, input2);
        
        assertThat(chatTransformer.translations).hasSize(4);
        assertThat(chatTransformer.translations.get(0)).isSameAs(untransformable1);
        assertThat(chatTransformer.translations.get(1)).isNotInstanceOf(TransformableText.class);
        assertThat(chatTransformer.translations.get(2)).isNotInstanceOf(TransformableText.class);
        assertThat(chatTransformer.translations.get(3)).isSameAs(untransformable2);
        assertThat(chatTransformer.translations.get(1).getString()).isEqualTo(input1 + " transformed");
        assertThat(chatTransformer.translations.get(2).getString()).isEqualTo(input2 + " transformed");
    }

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

    private static class TransformableText implements StringVisitable {
        
        private final String input;

        private TransformableText(String input) {
            this.input = input;
        }

        @Override
        public <T> Optional<T> visit(Visitor<T> visitor) {
            return Optional.empty();
        }

        @Override
        public <T> Optional<T> visit(StyledVisitor<T> styledVisitor, Style style) {
            return Optional.empty();
        }

        @Override
        public String getString() {
            return input;
        }
    }

    private static class UntransformableText implements Text {
        @Override
        public Style getStyle() {
            return null;
        }

        @Override
        public String asString() {
            return null;
        }

        @Override
        public List<Text> getSiblings() {
            return null;
        }

        @Override
        public MutableText copy() {
            return null;
        }

        @Override
        public MutableText shallowCopy() {
            return null;
        }

        @Override
        public OrderedText asOrderedText() {
            return null;
        }
    }

    private static class DummyChatTransformer extends ChatTransformer {
    }
}
