# Project Environment Guide (JUNIE.md)

- **Programming language:** Java 8
- **Primary package manager/build tool:** Gradle (via Gradle Wrapper)

## Environment setup script
```sh
#!/usr/bin/env bash
set -eu
export JAVA_HOME="/usr/lib/jvm/temurin-8-jdk-amd64"
export PATH="$JAVA_HOME/bin:$PATH"
cd /home/runner/work/idea_test_task/idea_test_task
./gradlew --no-daemon --console=plain clean build
```

## How to activate the environment
Java/Gradle projects do not require special activation, but you must set JAVA_HOME to point to a JDK 8 installation. Example:
```sh
export JAVA_HOME="/usr/lib/jvm/temurin-8-jdk-amd64"
export PATH="$JAVA_HOME/bin:$PATH"
```

## How to run tests
```sh
./gradlew --no-daemon test
```
