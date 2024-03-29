package net.vampyrebytes.minecraftmods.discordemoji;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiTransformer {

    private static final Logger LOG = LogManager.getLogger(EmojiTransformer.class);

    private final Pattern emojiPattern;
    private final Map<String, Character> emojiMap;

    public static EmojiTransformer getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private EmojiTransformer() {
        this.emojiPattern = Pattern.compile(":([^\\s]+):");
        this.emojiMap = loadEmojis();
    }

    public String transformEmojis(String text) {
        StringBuffer newText = new StringBuffer();
        Matcher matcher = this.emojiPattern.matcher(text);
        while (matcher.find()) {
            String emoji = matcher.group(1);
            if (emoji != null && emoji.length() > 0) {
                matcher.appendReplacement(newText, getEmojiFor(emoji));
            }
        }
        matcher.appendTail(newText);
        return newText.toString();
    }

    private String getEmojiFor(String emoji) {
        Character data = this.emojiMap.get(emoji);
        if (data != null) {
            return data.toString();
        }
        return ":" + emoji + ":";
    }

    private Map<String, Character> loadEmojis() {
         Map<String, Character> emojis = new HashMap<>();
        char emojiChar = '\uac00';
        try {
            InputStream listInput = getClass().getResourceAsStream("emojitable.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(listInput));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                emojis.put(line, emojiChar++);
            }
            bufferedReader.close();
            listInput.close();
        } catch (Exception e) {
            LOG.error("Failed to load emojis", e);
        }
        LOG.debug("Emojis loaded");
        return Collections.unmodifiableMap(new ConcurrentHashMap<>(emojis));
    }

    private static class InstanceHolder {
        private static EmojiTransformer INSTANCE = new EmojiTransformer();
    }
}
