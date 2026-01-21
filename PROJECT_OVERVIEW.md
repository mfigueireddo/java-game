# Java 2D Game - Project Overview

## Project Description

This is a Java-based 2D game application built using Swing for the graphical user interface. The project follows object-oriented design principles with a clear separation of concerns across different packages.

---

## Current Project State

The project is in its **initial development phase**, with the foundational architecture established. Currently, the application can:
- Launch a game window
- Display a basic menu screen (white background with menu buttons)
- Handle minimum screen size requirements
- Use the singleton pattern for key components

---

## Architecture

The project follows a **Model-View-Controller (MVC)** pattern with the following package structure:

### 📦 Package Structure

```
src/
├── controller/          # Application control flow
│   └── GameRunner.java
├── management/          # Utility and management classes
│   ├── ErrorStatus.java
│   └── Wrappers.java
└── view/               # User interface components
    ├── Window.java
    ├── Panel.java
    └── Menu.java
```

---

## Component Details

### Controller Package

#### `GameRunner.java`
- **Purpose**: Main entry point of the application
- **Responsibilities**:
  - Initialize the game window
  - Start the application by displaying the menu
- **Key Method**: `main(String[] args)` - Creates window instance and shows menu

---

### View Package

#### `Window.java`
- **Pattern**: Singleton
- **Purpose**: Main game window (JFrame)
- **Responsibilities**:
  - Manage the application window lifecycle
  - Handle panel switching
  - Configure window properties (title, close operation, resizing)
- **Key Methods**:
  - `GetInstance()` - Returns singleton instance
  - `ShowMenu()` - Displays the menu panel
  - `ChangePanel(JPanel panel)` - Switches between different panels/screens
- **Window Configuration**:
  - Title: "Java 2D Game"
  - Resizable: Yes
  - Default state: Maximized
  - Close operation: Exit on close

#### `Panel.java`
- **Type**: Abstract base class extending JPanel
- **Purpose**: Base class for all game panels/screens
- **Responsibilities**:
  - Define minimum screen size requirements
  - Validate screen dimensions
  - Handle screen size errors
- **Constants**:
  - `MIN_WIDTH`: 800 pixels
  - `MIN_HEIGHT`: 600 pixels
- **Validation**: Checks if the user's screen meets minimum requirements at initialization

#### `Menu.java`
- **Pattern**: Singleton
- **Purpose**: Main menu screen
- **Current State**: Functional implementation with white background and three menu buttons (New Game, Load Game, Settings) with hover effects
- **Inherits**: Panel (provides screen size validation)
- **Future Enhancement**: Button actions need to be implemented (currently disabled)

---

### Management Package

#### `ErrorStatus.java`
- **Type**: Enum
- **Purpose**: Define application error states and exit codes
- **Current States**:
  - `SUCCESS` (exit code: 0)
  - `MIN_SCREEN_SIZE` (exit code: 1)
- **Key Method**: `GetExitCode()` - Returns the ordinal value as exit code
- **Usage**: Standardized error handling across the application

#### `Wrappers.java`
- **Type**: Utility class
- **Purpose**: Provide validation helper methods
- **Methods**:
  - `VerifyMinValue(int value, int min_value)` - Validates minimum value constraint
  - `VerifyMaxValue(int value, int max_value)` - Validates maximum value constraint
- **Error Handling**: Throws `IllegalArgumentException` when validation fails
- **Usage**: Currently used for screen size validation in Panel class

---

## Design Patterns Used

### 1. **Singleton Pattern**
Applied to:
- `Window` - Ensures only one game window exists
- `Menu` - Ensures only one menu instance exists

**Benefits**:
- Centralized instance management
- Memory efficiency
- Easy access to shared components

### 2. **Template Method Pattern**
- `Panel` serves as an abstract base class
- Concrete panels (like `Menu`) extend `Panel` and inherit validation logic

### 3. **MVC Pattern**
- **Model**: Not yet implemented (will contain game state and logic)
- **View**: Window, Panel, Menu (UI components)
- **Controller**: GameRunner (application flow control)

---

## Code Conventions

The project follows specific naming conventions as defined in `.github/copilot-instructions.md`:

### Method Names
- **Format**: PascalCase
- **Examples**: `GetInstance()`, `ShowMenu()`, `ChangePanel()`, `VerifyMinValue()`

### Variable and Field Names
- **Format**: snake_case
- **Examples**: `screen_size`, `screen_width`, `screen_height`, `min_value`

### Singleton Pattern Structure
- Static instance field declared first
- `GetInstance()` method placed immediately after
- Constructor and other methods follow

---

## Current Application Flow

```
1. User runs GameRunner.main()
2. GameRunner creates Window singleton instance
3. Window initializes with default settings
4. GameRunner calls window.ShowMenu()
5. Window creates and displays Menu panel
6. Menu panel validates screen size (via Panel constructor)
   - If screen too small: Application exits with error code 1
   - If screen valid: White menu screen with three menu buttons (New Game, Load Game, Settings) displays
7. Window maximizes and becomes visible
```

---

## Error Handling

### Screen Size Validation
- **Minimum Requirements**: 800x600 pixels
- **Validation Point**: Panel constructor
- **Error Behavior**:
  - Prints error message to stderr
  - Exits application with code `ErrorStatus.MIN_SCREEN_SIZE` (1)

### Validation Flow
```
Panel Constructor
  → Gets screen dimensions from Toolkit
  → Calls Wrappers.VerifyMinValue() for width
  → Calls Wrappers.VerifyMaxValue() for height
  → If validation fails: Catch exception → Print error → Exit
```

---

## Build and Execution

### Compilation
```bash
javac -d bin -sourcepath src src\controller\GameRunner.java
```

### Execution
```bash
java -cp bin controller.GameRunner
```

### Directory Structure
- **Source**: `src/` - Contains all .java source files
- **Binaries**: `bin/` - Contains compiled .class files
- **Documentation**: `tutorials/` - Contains learning materials

---

## Future Development Areas

Based on the current architecture, the following areas are ready for expansion:

### 1. **Game State Management**
- Create a model package for game state
- Implement game logic classes
- Add player/entity management

### 2. **Menu Functionality**
- ✓ Add buttons to Menu panel (completed: New Game, Load Game, Settings)
- Implement button actions and navigation between screens
- Implement settings functionality

### 3. **Additional Panels/Screens**
- GamePanel for actual gameplay
- SettingsPanel for game configuration
- PausePanel for in-game pause menu

### 4. **Game Loop**
- Implement game update/render cycle
- Add frame rate management
- Handle user input

### 5. **Graphics and Rendering**
- Sprite loading and rendering
- Animation system
- Camera/viewport management

### 6. **Input Handling**
- Keyboard input system
- Mouse input handling
- Controller support

---

## Dependencies

### External Libraries
- **Java Swing** (javax.swing): For GUI components
- **AWT** (java.awt): For graphics and event handling

### Java Version
- Requires Java Development Kit (JDK)
- Compatible with modern Java versions (JDK 8+)

---

## Testing and Quality

### Current State
- No unit tests implemented yet
- Manual testing of window display and screen validation

### Planned Testing
According to Definition of Done:
- Unit tests for all components
- Linter approval
- Acceptance criteria verification

---

## Repository Information

- **Repository**: mfigueireddo/java-2d-game
- **Branch**: main
- **Platform**: GitHub
- **License**: Not specified

---

## Getting Started for New Contributors

1. **Clone the repository**
2. **Review this overview** to understand the architecture
3. **Check [ISSUE_TEMPLATE.md](.github/ISSUE_TEMPLATE.md)** for issue creation guidelines
4. **Read [copilot-instructions.md](.github/copilot-instructions.md)** for coding conventions
5. **Build and run** the application following [build.md](build.md)
6. **Start with simple issues** labeled for beginners

---

## Summary

This is a well-structured foundation for a 2D game in Java. The singleton pattern ensures efficient resource management, the MVC architecture provides clear separation of concerns, and the validation system ensures the application runs in appropriate environments. The project is ready for feature expansion while maintaining clean architecture principles.
