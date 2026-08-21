# PolyglotYT - Immersive Translation Xposed Module for YouTube

Fork of [Dr-TSNG/PolyglotYT](https://github.com/Dr-TSNG/PolyglotYT) with a second-development (二改) that makes YouTube captions display **one original line + one translation line**, just like immersive-translate on the web.

## Branches

| Branch | Version | Description |
|--------|---------|-------------|
| `master` | 0.3.0 | Upstream code from the original author |
| `v0.6.11` | 0.6.11 | **Latest** - full Kotlin source with immersive sentence-splitting bilingual captions |
| `v0.4.3-fixed-lsp` | 0.4.3 | Second-development base - fixed LSPosed API 102 compatibility |

## v0.6.11 - What changed (vs upstream 0.3.0)

The core rendering pipeline was rebuilt so that **every caption shows exactly one original line followed by one translation line**, without clipping, duplication, or layout overflow.

### 1. Caption rendering (CaptionHook.kt)
- Rewrote the render-text hook: every render call maps the rendered fragment to its full cue, then injects the complete bilingual block (`original` + `translation`).
- Added a **fragment fallback**: when a rendered piece is a cross-cue splice that no single observed cue contains, its own translation (requested by the render path) is used, so the caption never shows untranslated text while waiting.
- Added an **exact formatted-text re-entry guard** (`isExactlyFormattedText`) that stops the refresh loop: inject -> re-render -> re-inject. This removed ~2500 redundant refreshes per minute (measured before/after).
- Removed the legacy `refreshVisibleRenderers` background refresh (replaced by synchronous injection in the render hook).

### 2. Bilingual formatting (BilingualFormatter.kt)
- **Removed the character-width `wrapText`** that previously split long originals into 2+ lines, causing the translation line to be clipped by the fixed-height caption window.
- Replaced it with **immersive-translate style sentence splitting** (`SentenceSplitter.kt`): long text breaks per sentence (each sentence on its own line), so phrasing stays readable and the block fits the two-line caption window.
- Single over-long sentences are additionally bounded at word/punctuation boundaries (`MAX_SINGLE_LINE_WIDTH`) so they never overflow the screen.

### 3. Translation pipeline (TranslationManager.kt, providers)
- Kept per-cue translation requests (batching risks misaligned translations when the provider reorders lines).
- Translation cache, prefetch of upcoming cues, and per-cue failure retry TTL are preserved.

### 4. Settings & UI
- Added settings entries for subtitle line width / scale / translation color with proper host integration.

### 5. Logging
- Replaced the removed `Logger.kt` with `ModuleLogger.kt` that mirrors log output to a file in the module directory (`/data/user/0/com.google.android.youtube/files/polyglotyt/logs/polyglotyt.log`), readable via adb.

## v0.4.3-fixed-lsp - What changed
- Fixed LSPosed API 102 compatibility (hooks install correctly on the tested YouTube 21.25.530 / LSPosed API 102 environment).
- Kept the simple `original\ntranslation` two-line caption block (no width wrapping), which is the baseline this project's immersive behavior is built upon.
- See the `v0.4.3-fixed-lsp` branch for the decompiled/reference sources.

## Build
```bash
./gradlew assembleDebug
```
Requires JDK 17+, Android SDK 37, Gradle 9.x (wrapper included).

## License
GPL-3.0 (same as upstream).
