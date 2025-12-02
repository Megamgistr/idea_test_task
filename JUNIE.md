# Project Environment Setup

- **Programming Language:** Java 8 (compatible with Java 8 up to Java 15; does NOT support Java 17+)
- **Primary Package Manager/Build Tool:** Gradle (via wrapper, version 6.8)

## Install Environment & Dependencies
```
# Make sure JAVA_HOME points to Java 8, 11, or 15:
export JAVA_HOME="/path/to/compatible/java"

# Prepare environment and run build
./gradlew --no-daemon build
```

## Command to Activate Environment
- (*Not required; just ensure JAVA_HOME is set to the supported JDK*)

## Command to Run Tests
```
./gradlew --no-daemon test
```

---
**Note:**
- The project will NOT work with Java 17+ ("Unsupported class file major version 61" error). Use Java 8, 11, or 15.
- All dependency installation is handled by the Gradle wrapper and does not require system-wide changes.