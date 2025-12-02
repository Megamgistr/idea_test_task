#!/usr/bin/env bash
set -euo pipefail

# Prepare Gradle (will download dependencies, prepare environment)
./gradlew --no-daemon build

# Run functional tests
./gradlew --no-daemon test
