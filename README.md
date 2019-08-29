# Discord emoji minecraft mod for MC 1.14.4
This mod will replace text such as `:smile:` with the relevant Discord emoji.

Please keep in mind this mod is currently exclusively available for minecraft 1.14.4.

## Installation
Install [Fabric loader](https://fabricmc.net) and [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) and put the mod in your mods folder.

## Depends on Fabric Loader
Add to `minecraft/launcher_profiles.json`:
```json
{
  "profiles": {
    "fabric-loader-1.14.4" : {
      "created" : "2019-08-26T20:09:25.000Z",
      "icon" : "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACABAMAAAAxEHz4AAAAGFBMVEUAAAA4NCrb0LTGvKW8spyAem2uppSakn5SsnMLAAAAAXRSTlMAQObYZgAAAJ5JREFUaIHt1MENgCAMRmFWYAVXcAVXcAVXcH3bhCYNkYjcKO8dSf7v1JASUWdZAlgb0PEmDSMAYYBdGkYApgf8ER3SbwRgesAf0BACMD1gB6S9IbkEEBfwY49oNj4lgLhA64C0o9R9RABTAvp4SX5kB2TA5y8EEAK4pRrxB9QcA4QBWkj3GCAMUCO/xwBhAI/kEsCagCHDY4AwAC3VA6t4zTAMj0OJAAAAAElFTkSuQmCC",
      "javaArgs" : "-Xmx5G -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M",
      "lastUsed" : "2019-08-29T20:56:20.665Z",
      "lastVersionId" : "fabric-loader-1.14.4+build.12_yarn-0.6.1+build.164",
      "name" : "fabric-loader-1.14.4",
      "type" : "custom"
    }
  }
}
```

Add to `minecraft/versions/fabric-loader-1.14.4+build.12_yarn-0.6.1+build.164/fabric-loader-1.14.4+build.12_yarn-0.6.1+build.164.json`:
```json
{
  "id": "fabric-loader-1.14.4+build.12_yarn-0.6.1+build.164",
  "inheritsFrom": "1.14.4",
  "releaseTime": "2019-08-26T22:09:25+0200",
  "time": "2019-08-26T22:09:25+0200",
  "type": "release",
  "mainClass": "net.fabricmc.loader.launch.knot.KnotClient",
  "arguments": {
    "game": []
  },
  "libraries": [
    {
        "name": "net.fabricmc:fabric-loader:0.6.1+build.164",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "net.fabricmc:yarn:1.14.4+build.12",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "net.fabricmc:tiny-mappings-parser:0.1.1.8",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "net.fabricmc:sponge-mixin:0.7.11.38",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "net.fabricmc:tiny-remapper:0.1.0.40",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "net.fabricmc:fabric-loader-sat4j:2.3.5.4",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "com.google.jimfs:jimfs:1.1",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "org.ow2.asm:asm:7.1",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "org.ow2.asm:asm-analysis:7.1",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "org.ow2.asm:asm-commons:7.1",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "org.ow2.asm:asm-tree:7.1",
        "url": "https://maven.fabricmc.net/"
    },
    {
        "name": "org.ow2.asm:asm-util:7.1",
        "url": "https://maven.fabricmc.net/"
    }
  ]
}
```
Download into your mods folder: [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api/download/2773269)

## Special thanks to [EmojiChat](https://github.com/RadBuilder/EmojiChat)
Who kindly created the resource pack used in this mod.
