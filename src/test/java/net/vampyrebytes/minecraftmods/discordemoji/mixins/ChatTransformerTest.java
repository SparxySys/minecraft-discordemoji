package net.vampyrebytes.minecraftmods.discordemoji.mixins;

import net.vampyrebytes.minecraftmods.discordemoji.EmojiTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatTransformerTest {

    @Mock
    private EmojiTransformer emojiTransformer;

    @Mock
    private ChatTransformer chatTransformer;

    @Test
    public void delegatesToEmojiTransformerCorrectly() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> instanceHolder = Class.forName(EmojiTransformer.class.getCanonicalName() + "$InstanceHolder");
        Field instanceField = instanceHolder.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(null, emojiTransformer);

        when(chatTransformer.getFormattedText()).thenCallRealMethod();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        chatTransformer.getFormattedText();
        verify(emojiTransformer).transformEmojis(captor.capture());
    }
}
