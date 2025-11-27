# Project Environment & Test Guide

- **Programming language:** Java 1.8
- **Primary package manager/build tool:** Gradle (via wrapper script)

## Environment Setup & Testing

**Install dependencies and run tests:**
```bash
# (Recommended: Ensure JAVA_HOME points to JDK 8; Gradle may fail on newer JDKs)
chmod +x ./gradlew
./gradlew --no-daemon --console=plain test
```

**Environment activation:**
- No activation for isolated Java/Gradle environments (ensure correct JAVA_HOME is set).
- If running multiple JDKs, use tools like `sdkman`, `jenv`, or `update-alternatives` to pick Java 8. Example:
    ```bash
    export JAVA_HOME=/path/to/your/jdk8
    export PATH="$JAVA_HOME/bin:$PATH"
    ```

**Run tests:**
```bash
./gradlew --no-daemon --console=plain test
```

---
- All dependencies are handled by Gradle; manual installation is not required.
- Java 8 is required. Newer Java runtimes are NOT supported by this project's Gradle/plugins.
