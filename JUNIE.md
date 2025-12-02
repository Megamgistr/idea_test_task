# Project Environment Summary

- **Programming language:** Java 8
- **Primary package manager/build tool:** Gradle (wrapper, version 6.8)

## Environment setup

To install all dependencies and prepare the environment, run:

```bash
./setup_env_and_test.sh
```

## Environment activation

No special activation required. If you have multiple Java versions, you must ensure Java 8 is in use:

```bash
export JAVA_HOME=/path/to/java8
export PATH="$JAVA_HOME/bin:$PATH"
```

## Run tests

The install script will automatically run all tests and benchmarks. To re-run functional tests only:

```bash
./gradlew test --no-daemon --stacktrace
```
