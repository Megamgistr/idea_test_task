# Project Environment Summary

- **Programming Language:** Java (target: Java 8, compatible with Java 8–11 recommended)
- **Primary Package Manager/Build Tool:** Gradle (using Gradle Wrapper, version 6.8)

## Environment Installation & Activation
Run the following commands from the project root to prepare the build and test environment:

```bash
# (adjust JAVA_HOME/JDK path if your installation differs)
export JAVA_HOME="/usr/lib/jvm/temurin-11-jdk-amd64"
export PATH="$JAVA_HOME/bin:$PATH"

# Build, test, and benchmark (runs all steps)
chmod +x ./gradlew
./gradlew clean build --no-daemon --stacktrace
./gradlew test --no-daemon --stacktrace
./gradlew jmh --no-daemon --stacktrace
./gradlew jmhReport --no-daemon --stacktrace
./gradlew saveData --no-daemon --stacktrace
```

## Environment Activation Command
```bash
export JAVA_HOME="/usr/lib/jvm/temurin-11-jdk-amd64"
export PATH="$JAVA_HOME/bin:$PATH"
```

## Run Tests
```bash
./gradlew test --no-daemon --stacktrace
```

**Notes:**
- Java 17+ is NOT supported by Gradle 6.8. Use JDK 8 or 11.
- All dependencies are managed by the Gradle wrapper; no further manual installation required.
- 3 tests in `com.calculator.PositiveCases` are currently failing due to project logic, NOT the environment.
