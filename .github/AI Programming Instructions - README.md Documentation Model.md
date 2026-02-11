# AI Instructions - README.md Documentation Model

When updating README.md, follow this structure:

## Top-Level Sections
1. **Project Description** - Brief overview of what the project is
2. **Current Project State** - What's currently implemented and working
3. **Architecture** - MVC pattern explanation and package structure
4. **Component Details** - Detailed breakdown by package/folder
5. **Design Patterns Used** - Patterns applied in the project
6. **Code Conventions** - Summary reference to this file
7. **Current Application Flow** - Step-by-step execution flow
8. **Error Handling** - How errors are managed
9. **Build and Execution** - Directory structure and build process
10. **Dependencies** - External libraries and Java version
11. **Testing and Quality** - Testing status
12. **Repository Information** - Git details

## Component Documentation Format

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

## Example Component Entry

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
