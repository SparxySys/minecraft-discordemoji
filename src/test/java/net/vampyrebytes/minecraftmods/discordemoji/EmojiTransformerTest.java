package net.vampyrebytes.minecraftmods.discordemoji;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EmojiTransformerTest {

    private static final char BASE = '\uac00';
    private EmojiTransformer emojiTransformer = EmojiTransformer.getInstance();

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
