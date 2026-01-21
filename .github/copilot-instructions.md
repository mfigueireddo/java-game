# Copilot Instructions - Code Style & Convention Guide

## Document Overview

This document defines the **coding standards, conventions, and best practices** for the Java 2D Game project. All AI-assisted code generation and human-written code should adhere to these guidelines to maintain consistency, readability, and maintainability across the codebase.

---

## Purpose and Scope

### Primary Goals
- Establish consistent naming conventions across the entire codebase
- Define structural patterns for common scenarios (Singleton, initialization, etc.)
- Prevent common code quality issues (magic numbers, exception misuse)
- Ensure code readability and maintainability
- Support non-native English speakers with linguistic corrections

### Target Audience
- **GitHub Copilot**: Primary consumer of these instructions
- **AI Code Assistants**: Any AI tool working on this codebase
- **Human Developers**: Reference guide for manual coding
- **Code Reviewers**: Standard for evaluating pull requests

---

## Naming Conventions

### Method Names
- Use PascalCase for all method names
- Examples: `GetGamePanel()`, `UpdatePlayer()`, `RenderGraphics()`

### Variable and Field Names
- Use snake_case for all variable and field names
- Examples: `game_panel`, `player_position`, `enemy_count`

### Constants
- Use UPPER_SNAKE_CASE for all constants (final static variables)
- Examples: `MAX_HEALTH`, `DEFAULT_SPEED`, `MIN_WIDTH`

### Descriptive Names
- Avoid abbreviations - use full, descriptive names
- Examples:
  - ✅ `enemy_health`, `maximum_velocity`
  - ❌ `en_hlth`, `max_vel`
- Avoid single-letter names, even in loops
- Examples:
  - ✅ `for (int row = 0; row < height; row++)`
  - ✅ `for (int index = 0; index < list.size(); index++)`
  - ✅ `for (int column = 0; column < width; column++)`
  - ❌ `for (int i = 0; i < n; i++)`

## Code Style Guidelines

- Follow the established naming conventions consistently throughout the codebase
- Method names should be descriptive and use PascalCase
- Local variables, instance variables, and parameters should use snake_case
- Constants should use UPPER_SNAKE_CASE

## Singleton Pattern

- When creating a singleton class, place the GetInstance() method first in the class
- Order: static instance field, GetInstance() method, then constructor and other methods

## Variable Initialization

- Initialize member variables at declaration, not in the constructor
- Example: `private final JButton new_game = new JButton("New Game");`
- Keep constructors focused on setup and configuration logic

## Magic Numbers

- Never use magic numbers directly in code
- Always create final variables with descriptive names for all numeric values
- This includes: dimensions, sizes, margins, padding, thickness, colors, etc.
- Examples:
  - `final int buttons_width = 200;`
  - `final int buttons_height = 40;`
  - `final int buttons_vbox_border_thickness = 10;`
  - `final int buttons_vbox_margin = 10;`
- Group related constants together in the code for clarity

## Exception Handling

- Use exceptions ONLY for extreme cases when the program is at risk of improper behavior
- Exceptions should be reserved for situations that cannot be handled through normal control flow
- For expected conditions and validation, prefer explicit checks and error handling without exceptions

## Language Corrections

- Always correct any linguistic mistakes (grammar, spelling, etc.) found in prompts and code
- The user is not a native English speaker and welcomes corrections
- Provide corrections politely while fulfilling the main request

---

## README.md Documentation Model

When updating README.md, follow this structure:

### Top-Level Sections
1. **Project Description** - Brief overview of what the project is
2. **Current Project State** - What's currently implemented and working
3. **Architecture** - MVC pattern explanation and package structure
4. **Component Details** - Detailed breakdown by package
5. **Design Patterns Used** - Patterns applied in the project
6. **Code Conventions** - Summary reference to this file
7. **Current Application Flow** - Step-by-step execution flow
8. **Error Handling** - How errors are managed
9. **Build and Execution** - Directory structure and build process
10. **Dependencies** - External libraries and Java version
11. **Testing and Quality** - Testing status
12. **Repository Information** - Git details

### Component Documentation Format

For each package, document each file/class with:

- **Pattern**: Design pattern used (if applicable)
  - Examples: Singleton, Abstract base class, Enum, Utility class
- **Purpose**: High-level description of what the component does
- **Responsibilities**: Bulleted list of what this component is responsible for
- **Key Methods**: List important public methods with brief descriptions
- **Constants**: Important constant values (if applicable)
- **Configuration**: Settings and properties (if applicable)
- **Components**: UI elements or sub-components (if applicable)
- **Styling**: Visual appearance details (if applicable)
- **Current State**: Implementation status
- **Inherits**: Parent class (if applicable)
- **Layout**: UI layout approach (if applicable)
- **Error Handling**: How this component handles errors (if applicable)
- **Usage**: How other components use this one (if applicable)
- **Future Enhancement**: Planned improvements (if applicable)

### Example Component Entry

```markdown
#### `ClassName.java`
- **Pattern**: Singleton
- **Purpose**: Main game window (JFrame)
- **Responsibilities**:
  - Manage the application window lifecycle
  - Handle panel switching
  - Configure window properties
- **Key Methods**:
  - `GetInstance()` - Returns singleton instance
  - `ShowMenu()` - Displays the menu panel
- **Configuration**:
  - Title: "Java 2D Game"
  - Resizable: Yes
```

---

## Version History

**Version 1.2** (January 20, 2026)
- Added README.md documentation model section
- Added Constants naming convention (UPPER_SNAKE_CASE)
- Added descriptive naming rules (avoid abbreviations and single-letter names)

**Version 1.1** (January 20, 2026)
- Expanded documentation with comprehensive sections and examples
- Added rationale and benefits for each convention
- Included real-world examples from the project
- Created detailed sub-sections for each guideline

**Version 1.0** (Initial)
- Basic naming conventions and guidelines established