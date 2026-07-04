# PolyglotYT

PolyglotYT is an Xposed module for the YouTube Android app. It provides an immersive bilingual subtitle translation experience.

## Features

- Shows bilingual translations for YouTube captions.
- Supports three subtitle display modes:
  - original above translation
  - translation above original
  - translation only
- Automatically detects the source language from the caption language selected in YouTube.
- Adds a PolyglotYT configuration entry to YouTube settings.
- Adds an immersive translation quick switch next to the player CC button.
- Supports the following translation services:
  - Google Translate
  - Microsoft Translator
  - OpenAI-compatible API

## Supported Versions

- Android: 9.0 and later, minimum SDK 28.
- Xposed Framework: supporting libxposed API 101 and later.
- YouTube: tested on version 21.25.530.
