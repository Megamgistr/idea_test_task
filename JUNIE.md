# Project Environment Summary

**Programming language:** Java (requires JDK 11, sourceCompatibility = 1.8)

**Primary package/dependency manager:** Gradle (wrapper, version 6.8)

---

## IMPORTANT: JDK Version Requirement
This project cannot be built or tested unless JDK 11 is installed on your system. Only JDK 17+ was detected in this environment, and the build will fail unless you use JDK 11.

---

## Environment Setup Script
Save this as `env-setup.sh` in the project root and make it executable:

```bash
#!/usr/bin/env bash
set -eu
export JAVA_HOME="/path/to/your/jdk-11"  # Set this to JDK 11 (e.g., /usr/lib/jvm/java-11-openjdk-amd64)
export PATH="$JAVA_HOME/bin:$PATH"
cd "$(dirname "$0")"
./gradlew clean --no-daemon --console=plain
./gradlew --no-daemon --console=plain build -x test
./gradlew --no-daemon --console=plain testClasses
./gradlew --no-daemon --console=plain jmhClasses
./gradlew --no-daemon --console=plain --version
```

## How to activate the environment

```bash
export JAVA_HOME="/path/to/your/jdk-11"
export PATH="$JAVA_HOME/bin:$PATH"
```

## How to run tests

```bash
./gradlew test -q
```

---
**Note:** The project will fail to build/run if you do not use JDK 11.