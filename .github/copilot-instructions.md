# Copilot Instructions for Java 2D Game Project

## Naming Conventions

### Method Names
- Use PascalCase for all method names
- Examples: `GetGamePanel()`, `UpdatePlayer()`, `RenderGraphics()`

### Variable and Field Names
- Use snake_case for all variable and field names
- Examples: `game_panel`, `player_position`, `enemy_count`

## Code Style Guidelines

- Follow the established naming conventions consistently throughout the codebase
- Method names should be descriptive and use PascalCase
- Local variables, instance variables, and parameters should use snake_case

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
