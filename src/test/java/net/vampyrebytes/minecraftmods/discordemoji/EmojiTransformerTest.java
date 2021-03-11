package net.vampyrebytes.minecraftmods.discordemoji;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EmojiTransformerTest {

    private static final char BASE = '\uac00';
    private EmojiTransformer emojiTransformer;

    @BeforeEach
    public void setUp() {
        emojiTransformer = EmojiTransformer.getInstance();
    }

    @Test
    public void transformsEmojisCorrectly() {
        String result = emojiTransformer.transformEmojis("test string with :smile: and :computer:");
        int emoji1 = 8;
        int emoji2 = 508;
        assertThat(result).isEqualTo("test string with " + (char)(BASE + emoji1) + " and " + (char)(BASE + emoji2));
    }

    @Test
    public void leavesNonExistentEmojisAlone() {
        String input = "hello there :blub: I am a :fishyfish: who has :nonexistentemojis:";
        String result = emojiTransformer.transformEmojis(input);
        assertThat(result).isEqualTo(input);
    }
}
