# Discord emoji minecraft mod for MC 1.13.2
This mod will replace text such as `:smile:` with the relevant Discord emoji.

Please keep in mind this mod is currently exclusively available for minecraft 1.13.2.

## Installation
Install Rift and put the mod in your mods folder.

## Depends on [Chocohead Rift for 1.13.2](https://github.com/Chocohead/Rift)
Add to minecraft/launcher_profiles.json:
```json
{
  "profiles": {
    "rift" : {
      "icon" : "Furnace",
      "lastUsed" : "2019-08-20T19:13:29.939Z",
      "lastVersionId" : "1.13.2-rift-48a6e15",
      "name" : "Rift 1.13.2",
      "type" : "custom"
    }
  }
}
```

Add to minecraft/versions/1.13.2-rift-48a6e15/1.13.2-rift-48a6e15.json:
```json
{
  "id": "1.13.2-rift-48a6e15",
  "inheritsFrom": "1.13.2",
  "time": "2018-11-29T09:50:00+00:00",
  "releaseTime": "2018-11-29T09:45:00+00:00",
  "type": "release",
  "libraries": [
    {
      "name": "com.github.Chocohead:Rift:48a6e15",
      "url": "https://www.jitpack.io/"
    },
    {
      "name": "org.dimdev:mixin:0.7.11-SNAPSHOT",
      "url": "https://www.dimdev.org/maven/"
    },
    {
      "name": "org.ow2.asm:asm:6.2",
      "url": "http://repo1.maven.org/maven2/"
    },
    {
      "name": "org.ow2.asm:asm-commons:6.2",
      "url": "http://repo1.maven.org/maven2/"
    },
    {
      "name": "org.ow2.asm:asm-tree:6.2",
      "url": "http://repo1.maven.org/maven2/"
    },
    {
      "name": "net.minecraft:launchwrapper:1.12"
    }
  ],
  "mainClass": "net.minecraft.launchwrapper.Launch",
  "arguments": {
    "game": [
      "--tweakClass",
      "org.dimdev.riftloader.launch.RiftLoaderClientTweaker"
    ]
  }
}
```

## Special thanks to [EmojiChat](https://github.com/RadBuilder/EmojiChat)
Who kindly created the resource pack used in this mod.
