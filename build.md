# Build Instructions

## Prerequisites
- Java Development Kit (JDK) installed

## Compilation

To compile the project, run the following command from the project root directory:

```bash
javac -d bin -sourcepath src src\controller\Main.java
```

This compiles all source files from `src` and outputs the compiled classes to the `bin` directory.

## Execution

To run the compiled application:

```bash
java -cp bin controller.Main
```

## Quick Start

From the project root directory, execute:

```bash
javac -d bin -sourcepath src src\controller\Main.java
java -cp bin controller.Main
``` 