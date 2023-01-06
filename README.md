# Demo project for Android code coverage
This project demonstrates how to create a unified Jacoco code coverage report.
This includes coverage from both instrumented and unit tests. 

These specific issues are handled:

* Coverage from instrumented test running on emulator or physical device
* Fixes issue where classes annotated with Hilt annotation @AndroidEntryPoint was not being included
* Fixes issue with missing coverage from Robolectric tests

## Command line
To generate Jacoco html report for this project in app/build/reports/jacoco/testDebugCoverage/html folder:
```
./gradlew app:testDebugCoverage
```

To generate report in project where the asm_instrumented_project_classes folder is not created (for example if not using Hilt):
```
./gradlew app:testDebugCoverage -PignoreAsmInstrumentedClasses=true
```