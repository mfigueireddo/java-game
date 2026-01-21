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

## Issue Creation Guidelines

When helping to create, draft, or work on any issue, always follow this structured format:

### 1. Issue Title Format

```
AS [Who?] I WANT TO [What?] IN ORDER TO [Why?]
```

**Example:**
```
AS a Player I WANT TO see my score IN ORDER TO track my game progress
```

---

### 2. Requirements

#### 2.1 Functional Requirements

Functional requirements describe **what** the system should do.

**Format:**
```
[FR1] [Context] + [Responsible for the action] + [Action]
```

**Examples:**
- `[FR1]` When the player collects a coin, the system shall increment the score by 10 points
- `[FR2]` When the game starts, the menu shall display options for New Game, Settings, and Exit
- `[FR3]` When the player character touches an enemy, the system shall reduce health by 1 point

---

#### 2.2 Non-Functional Requirements

Non-functional requirements describe **how** the system should behave in terms of quality characteristics or restrictions.

**Format:**
```
[NFR1] [Description of quality characteristic or restriction]
```

**Key Principles:**
- Must be **measurable** and **verifiable**
- Focus on performance, security, usability, maintainability, etc.

**Examples:**
- `[NFR1]` The game shall maintain at least 60 FPS on systems with 4GB RAM or higher
- `[NFR2]` The game startup time shall not exceed 3 seconds on standard hardware
- `[NFR3]` All code shall follow the project's naming conventions (PascalCase for methods, snake_case for variables)
- `[NFR4]` The collision detection system shall process at least 100 entities without performance degradation

---

#### 2.3 Enablers

Enablers are technical tasks that support the implementation of features but don't directly deliver user value.

**Examples:**
- Set up unit testing framework for the project
- Refactor collision detection system to support new enemy types
- Create reusable animation component for character sprites
- Implement logging system for debugging game states

---

### 3. Acceptance Criteria Format

```
GIVEN [Context]
WHEN [Action]
THEN [Result]
```

**Examples:**
- `GIVEN` the player has collected 100 coins
  `WHEN` the player collects one more coin
  `THEN` the score should be 1010 and a bonus life should be awarded

- `GIVEN` the game is running
  `WHEN` the player presses the ESC key
  `THEN` the pause menu should appear and game action should stop

- `GIVEN` the player's health is at 0
  `WHEN` the player touches an enemy
  `THEN` the game over screen should be displayed

---

### 4. Definition of Ready

An issue is ready to be worked on when:

- ✅ **Description Done**: The issue has a clear title, detailed description, and all requirements defined
- ✅ **Acceptance Criteria Defined**: All acceptance criteria are written in GIVEN/WHEN/THEN format

---

### 5. Definition of Done

An issue is complete when:

- ✅ **Unit Tests**: All new model code has corresponding unit tests with passing results
- ✅ **Linter Approval**: Code passes all linting checks (via Copilot in Pull Requests) and follows project conventions
- ✅ **All Acceptance Criteria Achieved**: Every acceptance criterion has been met and verified
- ✅ **Code Review**: Changes have been reviewed and approved (if applicable)
- ✅ **Documentation Updated**: README.md properly updated

---

### Quick Reference Template

```markdown
## Title
AS [Who?] I WANT TO [What?] IN ORDER TO [Why?]

## Description
[Brief description of the feature or bug]

## Functional Requirements
- [FR1] [Context] + [Responsible] + [Action]
- [FR2] ...

## Non-Functional Requirements
- [NFR1] [Quality characteristic - measurable]
- [NFR2] ...

## Enablers (if applicable)
- [Technical task description]

## Acceptance Criteria
- GIVEN [Context]
  WHEN [Action]
  THEN [Result]

- GIVEN [Context]
  WHEN [Action]
  THEN [Result]

## Additional Notes
[Any other relevant information]
```

---

## Version History

**Version 1.3** (January 20, 2026)
- Added complete Issue Creation Guidelines section
- Removed separate ISSUE_TEMPLATE.md file (merged into this document)

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