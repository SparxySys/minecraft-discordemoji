package net.vampyrebytes.minecraftmods.discordemoji;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmojiTransformer {

    private final Pattern emojiPattern;
    private final Map<String, Character> emojiMap;

    static EmojiTransformer getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private EmojiTransformer() {
        this.emojiPattern = Pattern.compile(":([^\\s]+):");
        this.emojiMap = loadEmojis();
    }

    String transformEmojis(String text) {
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
            e.printStackTrace();
        }
        return new ConcurrentHashMap<>(emojis);
    }

    private static class InstanceHolder {
        private static final EmojiTransformer INSTANCE = new EmojiTransformer();
    }
}
