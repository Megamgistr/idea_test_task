# Project Environment & Test Guide

- **Programming language:** Java 1.8 (Java 8)
- **Primary package manager/build tool:** Gradle (via wrapper script, Gradle 6.8)

## Environment Setup

**Script to install the environment & run tests and benchmarks:**
```bash
# Ensure JAVA_HOME is set to a JDK 8 (Java 1.8.x) installation only!
export JAVA_HOME=/path/to/your/jdk8
export PATH="$JAVA_HOME/bin:$PATH"

cd /home/runner/work/idea_test_task/idea_test_task
chmod +x ./gradlew
export GRADLE_USER_HOME="$(pwd)/.gradle"
./gradlew --no-daemon --console=plain build --dry-run
./gradlew --no-daemon --console=plain test
./gradlew --no-daemon --console=plain jmh
```

**Environment activation:**
```bash
export JAVA_HOME=/path/to/your/jdk8
export PATH="$JAVA_HOME/bin:$PATH"
```
If you are using multiple JDKs, tools like `sdkman`, `jenv`, or `update-alternatives` can help switch to JDK 8.

**Command to run tests:**
```bash
./gradlew --no-daemon --console=plain test
```

**Note:**
- All Java dependencies are resolved by Gradle.
- Java 8 (JDK 1.8.x) is strictly required. Newer Java versions are NOT supported by this project's Gradle/plugins.
- Benchmarks can be run with: `./gradlew --no-daemon --console=plain jmh`
