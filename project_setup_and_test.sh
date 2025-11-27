#!/bin/bash
set -e
export GRADLE_USER_HOME="$(pwd)/.gradle"
chmod +x ./gradlew
./gradlew --no-daemon --console=plain --quiet build --dry-run
./gradlew --no-daemon --console=plain test
