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
