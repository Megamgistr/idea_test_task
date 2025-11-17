# Project Environment Setup

- **Programming Language:** Java 8 (sourceCompatibility = 1.8)
- **Primary Package Manager:** Gradle (via project wrapper, version 6.8)

## How to install dependencies and prepare the environment
```bash
# (Ensure JAVA_HOME points to Java 8 or Java 11, required for Gradle 6.8 compatibility)
chmod +x ./gradlew
./gradlew --no-daemon --console=plain build --stacktrace
```

## How to activate the environment
No activation step is required beyond ensuring JAVA_HOME is correctly set.

## How to run tests
```bash
./gradlew --no-daemon --console=plain test --stacktrace
```

---
> ⚠️ If you see a Gradle error regarding "Unsupported class file major version 61", set JAVA_HOME to Java 8 or Java 11 and re-run the script, as Gradle 6.8 does not support Java 17+.
