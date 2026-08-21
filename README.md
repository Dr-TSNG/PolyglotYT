# PolyglotYT v0.4.3-fixed-lsp (Reference Source)

This branch contains the **decompiled Java reference source** of the `PolyglotYT-v0.4.3-fixed-lsp.apk` — the second-development (二改) baseline that renders **one original line + one translation line** cleanly on YouTube (tested on YouTube 21.25.530, LSPosed API 102).

> **Note**: The original Kotlin sources of this version are not available (only the shipped APK). The `decompiled-sources/` directory holds jadx output of the module's own classes (`icu.nullptr.polyglot.*`, 54 files). Use it as a reference for the caption rendering approach; for a buildable, current codebase see the `v0.6.11` branch.

## Key files to understand

| File | Purpose |
|------|---------|
| `decompiled-sources/icu/nullptr/polyglot/youtube/CaptionHook.java` | Caption hooks. The render hook maps each rendered fragment to its full cue and injects the bilingual block; `replacementForTranslatedCue` returns the block for fragments that equal the cue or are its tail, and `""` otherwise. |
| `decompiled-sources/icu/nullptr/polyglot/captions/BilingualFormatter.java` | Builds the `original\ntranslation` two-line block. **No character-width wrapping** — this is why long originals do not overflow/clip. |
| `decompiled-sources/icu/nullptr/polyglot/captions/CaptionSession.java` | Translation cache + cue observation (`translatedCueContaining`, `isFormattedRenderedText`). |
| `decompiled-sources/icu/nullptr/polyglot/translate/TranslationManager.java` | Translation queue (per-cue requests, prefetch, retry TTL). |

## What was fixed in "fixed-lsp"

- **LSPosed API 102 compatibility**: hooks install and run correctly on the tested environment (YouTube 21.25.530, LSPosed API 102, Xiaomi 15 Pro / Magisk).
- **Stable bilingual captions**: caption window shows exactly `original` then `translation`, no clipping, no duplicated rows.

## Relationship to v0.6.11

The `v0.6.11` branch is the actively developed successor built on top of this approach:
- Keeps the same "one original line + one translation line" block.
- Adds immersive sentence-splitting for long captions.
- Adds a fragment-own-translation fallback and a re-entry guard that eliminates the refresh loop.
- Adds file-based logging (`ModuleLogger`) and more settings.

## Build

The decompiled sources are **reference only** (they do not compile directly). For a buildable project use the `v0.6.11` branch:

```bash
git checkout v0.6.11
./gradlew assembleDebug
```

## License
GPL-3.0 (same as upstream [Dr-TSNG/PolyglotYT](https://github.com/Dr-TSNG/PolyglotYT)).
