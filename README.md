# Demo project for Android code coverage
This project demonstrates how to create a unified Jacoco code coverage report.
This includes coverage from both instrumented and unit tests. 

These specific issues are handled:

* Coverage from instrumented test running on emulator or physical device
* Fixes issue where classes annotated with Hilt annotation @AndroidEntryPoint was not being included
* Fixes issue with missing coverage from Robolectric tests