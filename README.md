# Java 2D Game - Project Overview

## Project Description

This is a Java-based 2D game application built using Swing for the graphical user interface. The project follows object-oriented design principles with a clear separation of concerns across different packages. It features a resource management system for images and text files, an observer-based event system, and a tile-based map rendering engine.

---

## Current Project State

The project is in its **early development phase**, with the foundational architecture and rendering pipeline established. Currently, the application can:
- Launch a game window with a main menu
- Load and manage game resources (images and text files) at startup
- Display a basic menu screen with "New Game", "Load Game", and "Settings" buttons
- Transition from the menu to the game screen via the "New Game" button
- Parse and render a tile-based map from a text file
- Handle minimum screen size requirements
- Use the singleton pattern for key components
- Communicate between components via an observer/notification system

---

## Architecture

The project follows a **Model-View-Controller (MVC)** pattern with the following package structure:

### 📦 Package Structure

```
src/
├── controller/                  # Application control flow and resource management
│   ├── GameRunner.java
│   ├── files_controllers/       # File identity tracking (Controller, FolderController, ImageController, TextFileController)
│   ├── files_managers/          # File registration and lookup (Manager, ImageManager, TextFileManager)
│   ├── loaders/                 # File loading from disk (Loader, ImageLoader, TextFileLoader)
│   └── utils/                   # Utilities (ErrorStatus, GameExit, Notification, Observer)
├── images/                      # Image assets
│   ├── main_character/          # Character sprites (back, front, left, right)
│   └── world/                   # World tiles (grass)
├── persistance/                 # Persistent data
│   └── maps/                    # Map text files (main.txt)
└── view/                        # User interface components
    ├── ViewAPI.java
    ├── Window.java
    ├── panels/                  # Screen panels (Panel, Menu, Game)
    └── renderers/               # Rendering logic (Renderer, Map, Character)
```

---

## Component Details

### Controller Package

#### `GameRunner.java`
- **Purpose**: Main entry point of the application
- **Responsibilities**:
  - Orchestrate the startup sequence (load resources, then run the UI)
  - Load all images and text files before displaying the window
  - Initialize the view and show the menu
- **Key Methods**:
  - `main(String[] args)` - Calls `StartUp()`, `Run()`, and `ShutDown()` in sequence
  - `StartUp()` - Loads images and text files via their respective loaders
  - `Run()` - Creates the ViewAPI instance and shows the menu
  - `ShutDown()` - Placeholder for future cleanup logic

---

### Controller - Files Controllers Sub-package

#### `Controller.java`
- **Type**: Abstract base class
- **Purpose**: Safe abstraction for tracking files and folders by ID and name
- **Responsibilities**:
  - Store a unique integer ID and a string name for each resource
- **Key Methods**:
  - `GetID()` - Returns the resource's unique ID
  - `GetName()` - Returns the resource's name

#### `FolderController.java`
- **Inherits**: Controller
- **Purpose**: Represents a folder in the resource system
- **Key Detail**: Auto-increments a static ID tracker for each new instance

#### `ImageController.java`
- **Inherits**: Controller
- **Purpose**: Represents an image file in the resource system
- **Key Detail**: Auto-increments a static ID tracker for each new instance

#### `TextFileController.java`
- **Inherits**: Controller
- **Purpose**: Represents a text file in the resource system
- **Key Detail**: Auto-increments a static ID tracker for each new instance

---

### Controller - Files Managers Sub-package

#### `Manager.java`
- **Type**: Abstract base class
- **Purpose**: Abstraction to simplify file management, organizing resources by folder
- **Responsibilities**:
  - Store resources grouped by `FolderController`
  - Look up resources by ID or name
  - Resolve file paths and full file names
- **Key Methods**:
  - `RegisterControllers(FolderController, ArrayList<Controller>)` - Registers a group of resources under a folder
  - `GetController(int id)` - Finds a resource by ID
  - `GetController(String name)` - Finds a resource by name
  - `GetControllers()` - Returns all registered resources
  - `GetFilePath(Controller)` - Resolves the full file path for a resource
  - `GetFileFullName(Controller)` - Returns the file name with extension

#### `ImageManager.java`
- **Pattern**: Singleton
- **Inherits**: Manager
- **Purpose**: Manages all registered image resources
- **Configuration**:
  - Directory: `src/images/`
  - Extension: `.png`
- **Registered Resources**:
  - World images: `grass`
  - Main character images: `back`, `front`, `left`, `right`
- **Key Methods**:
  - `GetImage(int id)` - Returns an `ImageController` by ID
  - `GetImage(String name)` - Returns an `ImageController` by name
  - `GetImages()` - Returns all registered `ImageController` instances

#### `TextFileManager.java`
- **Pattern**: Singleton
- **Inherits**: Manager
- **Purpose**: Manages all registered text file resources
- **Configuration**:
  - Directory: `src/persistance/`
  - Extension: `.txt`
- **Registered Resources**:
  - Maps: `main`
- **Key Methods**:
  - `GetTextFile(int id)` - Returns a `TextFileController` by ID
  - `GetTextFile(String name)` - Returns a `TextFileController` by name
  - `GetTextFiles()` - Returns all registered `TextFileController` instances

---

### Controller - Loaders Sub-package

#### `Loader.java`
- **Type**: Abstract generic base class (`Loader<Data>`)
- **Purpose**: Abstraction to simplify file loading, using a template method pattern
- **Responsibilities**:
  - Iterate over all registered controllers from the corresponding manager
  - Load each resource from disk and store it in a map
  - Provide access to loaded data by controller
- **Key Methods**:
  - `Load()` - Loads all resources registered in the corresponding manager
  - `GetData(Controller)` - Returns the loaded data for a given controller
- **Abstract Methods**:
  - `GetManager()` - Returns the corresponding Manager instance
  - `LoadData(Controller, String)` - Loads the data from the given file path
  - `HandleMissingResource(Controller)` - Handles the case when a resource is not found

#### `ImageLoader.java`
- **Pattern**: Singleton
- **Inherits**: Loader\<BufferedImage\>
- **Purpose**: Loads image files from disk using `ImageIO`
- **Error Handling**:
  - Exits with `IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT` if the file doesn't exist or returns null
  - Exits with `FAILED_LOADING_IMAGE` on `IOException`
  - Exits with `IMAGE_NOT_FOUND` if a loaded image is requested but not found
- **Key Methods**:
  - `GetImage(ImageController)` - Returns a loaded `BufferedImage`

#### `TextFileLoader.java`
- **Pattern**: Singleton
- **Inherits**: Loader\<String\>
- **Purpose**: Loads text files from disk using `BufferedReader`
- **Error Handling**:
  - Exits with `TEXTFILE_NOT_FOUND_OR_UNSUPPORTED_FORMAT` if the file doesn't exist
  - Exits with `FAILED_LOADING_TEXTFILE` on `IOException`
  - Exits with `TEXTFILE_NOT_FOUND` if a loaded text file is requested but not found
- **Key Methods**:
  - `GetTextFile(TextFileController)` - Returns the loaded file content as a `String`

---

### Controller - Utils Sub-package

#### `ErrorStatus.java`
- **Type**: Enum
- **Purpose**: Define application error states with descriptive messages and exit codes
- **Current States**:
  - `SUCCESS` (exit code: 0) - Success
  - `MIN_SCREEN_SIZE` (exit code: 1) - User's screen doesn't meet minimum requirements
  - `IMAGE_NOT_FOUND_OR_UNSUPPORTED_FORMAT` (exit code: 2) - Image not found or unsupported format
  - `FAILED_LOADING_IMAGE` (exit code: 3) - Image file is corrupted
  - `IMAGE_NOT_FOUND` (exit code: 4) - Image wasn't loaded
  - `TEXTFILE_NOT_FOUND_OR_UNSUPPORTED_FORMAT` (exit code: 5) - Text file not found or unsupported format
  - `FAILED_LOADING_TEXTFILE` (exit code: 6) - Text file is corrupted
  - `TEXTFILE_NOT_FOUND` (exit code: 7) - Text file wasn't loaded
  - `EMPTY_TEXTFILE` (exit code: 8) - A necessary text file is empty
  - `UNRECOGNIZED_MAP_ELEMENT` (exit code: 9) - Unrecognized element found during map loading
- **Key Methods**:
  - `GetExitCode()` - Returns the ordinal value as exit code
  - `GetErrorMessage()` - Returns the descriptive error message

#### `GameExit.java`
- **Type**: Utility class
- **Purpose**: Centralized error-based application exit
- **Responsibilities**:
  - Print formatted error messages to stderr
  - Exit the application with the corresponding error code
- **Key Methods**:
  - `Exit(ErrorStatus)` - Exits with error message
  - `Exit(ErrorStatus, String)` - Exits with error message and additional info

#### `Notification.java`
- **Type**: Enum
- **Purpose**: Define notification types for the observer system
- **Current Notifications**:
  - `NEW_GAME` - Triggered when the user clicks "New Game"
  - `LOAD_GAME` - Triggered when the user clicks "Load Game"
  - `SETTINGS` - Triggered when the user clicks "Settings"
  - `IMAGE_LOADING_FAILED` - Image loading failure notification
  - `IMAGE_NOT_FOUND` - Image not found notification
  - `WINDOW_RESIZED` - Triggered when the window is resized (panel change)
- **Key Methods**:
  - `GetID()` - Returns the ordinal value as notification ID

#### `Observer.java`
- **Pattern**: Singleton
- **Purpose**: Central event/notification system for decoupled component communication
- **Responsibilities**:
  - Register callback functions for specific notifications
  - Unregister previously registered callbacks
  - Notify all registered listeners when an event occurs
- **Key Methods**:
  - `Register(Notification, Runnable)` - Registers a callback for a notification, returns `false` if already registered
  - `Unregister(Notification, Runnable)` - Removes a callback, returns `false` if not found
  - `Notify(Notification)` - Invokes all callbacks registered for the given notification

---

### View Package

#### `ViewAPI.java`
- **Pattern**: Singleton
- **Purpose**: Public API facade for the view layer
- **Responsibilities**:
  - Provide a simplified interface for the controller to interact with the view
  - Delegate calls to the `Window` singleton
- **Key Methods**:
  - `ShowMenu()` - Delegates to `Window.ShowMenu()`

#### `Window.java`
- **Pattern**: Singleton
- **Purpose**: Main game window (JFrame)
- **Responsibilities**:
  - Manage the application window lifecycle
  - Handle panel switching between Menu and Game
  - Configure window properties (title, close operation, resizing)
  - Register observer callbacks for panel transitions
- **Key Methods**:
  - `GetInstance()` - Returns singleton instance
  - `ShowMenu()` - Displays the menu panel
  - `ShowGame()` - Displays the game panel
- **Observer Registrations**:
  - `NEW_GAME` → `ShowGame()` - Transitions to the game screen when "New Game" is clicked
- **Configuration**:
  - Title: "Java 2D Game"
  - Resizable: Yes
  - Default state: Maximized
  - Close operation: Exit on close

---

### View - Panels Sub-package

#### `Panel.java`
- **Type**: Abstract base class extending JPanel
- **Purpose**: Base class for all game panels/screens
- **Responsibilities**:
  - Define minimum screen size requirements
  - Validate screen dimensions (excluding Windows taskbar and window toolbar)
  - Set the panel's preferred size to the maximum usable window bounds
- **Constants**:
  - `MIN_WIDTH`: 800 pixels
  - `MIN_HEIGHT`: 600 pixels
- **Error Handling**: Exits with `ErrorStatus.MIN_SCREEN_SIZE` if the screen is too small

#### `Menu.java`
- **Pattern**: Singleton
- **Inherits**: Panel
- **Purpose**: Main menu screen
- **Current State**: Functional menu with three buttons; "New Game" is enabled, "Load Game" and "Settings" are disabled
- **Layout**: GridBagLayout with centered vertical button box
- **Components**:
  - Three buttons: "New Game", "Load Game", "Settings"
  - "New Game" is enabled and triggers the `NEW_GAME` notification
  - "Load Game" and "Settings" are disabled (temporary state)
  - Custom hover effects (light gray to light blue)
  - Fixed button dimensions: 200x40 pixels
  - Bordered box container with padding
- **Styling**:
  - White background
  - Light gray buttons with light blue hover effect
  - Black border around button container
- **Observer Notifications**:
  - `OnNewGame()` → notifies `NEW_GAME`
  - `OnLoadGame()` → notifies `LOAD_GAME`
  - `OnSettings()` → notifies `SETTINGS`

#### `Game.java`
- **Pattern**: Singleton
- **Inherits**: Panel
- **Purpose**: Main game screen where gameplay rendering occurs
- **Responsibilities**:
  - Render the map and character via `paintComponent()`
  - React to `NEW_GAME` and `WINDOW_RESIZED` notifications
- **Observer Registrations**:
  - `NEW_GAME` → `OnNewGame()` - Loads the main map
  - `WINDOW_RESIZED` → `OnWindowResize()` - Updates screen size and recalculates block dimensions
- **Rendering Pipeline**: Delegates rendering to `Map.Render()` and `Character.Render()` via `Graphics2D`

---

### View - Renderers Sub-package

#### `Renderer.java`
- **Type**: Abstract base class
- **Purpose**: Base class for all game renderers
- **Responsibilities**:
  - Store screen dimensions for rendering calculations
  - Cache frequently used `BufferedImage` references in a local map for performance
  - Provide access to resource managers and loaders
- **Key Methods**:
  - `SetScreenSize(int, int)` - Updates the renderer's screen dimensions
  - `Render(Graphics2D, Panel)` - Abstract method for rendering logic

#### `Map.java`
- **Pattern**: Singleton
- **Inherits**: Renderer
- **Purpose**: Parses and renders tile-based maps from text files
- **Responsibilities**:
  - Load map data from text files via `TextFileLoader`
  - Parse map elements (numeric tile IDs) and resolve their corresponding images
  - Calculate tile (block) dimensions based on screen size and map dimensions
  - Render the full map as a grid of tiles
- **Key Methods**:
  - `LoadMainMap()` - Loads the "main" map
  - `CalculateBlockDimensions()` - Recalculates tile sizes based on screen and map dimensions
  - `Render(Graphics2D, Panel)` - Renders all map tiles to the screen
- **Map Format**: Space-separated integer IDs in a grid layout (e.g., `1 1 1 1`)
- **Current State**: Renders a 16x16 grass tile map that fills the entire screen
- **Error Handling**:
  - Exits with `EMPTY_TEXTFILE` if the map file is empty
  - Exits with `UNRECOGNIZED_MAP_ELEMENT` if a non-numeric element is found

#### `Character.java`
- **Pattern**: Singleton
- **Inherits**: Renderer
- **Purpose**: Loads and renders the player character
- **Current State**: Loads character sprite images (front, back, left, right) but rendering logic is not yet implemented
- **Key Methods**:
  - `LoadMainCharacter()` - Loads the "main" character sprites
  - `LoadCharacter(String)` - Loads a named character's directional sprites
  - `Render(Graphics2D, Panel)` - Not yet implemented
- **Future Enhancement**: Implement character rendering and movement

---

## Design Patterns Used

### 1. **Singleton Pattern**
Applied to:
- `Window` - Ensures only one game window exists
- `ViewAPI` - Single entry point for view operations
- `Menu` - Ensures only one menu instance exists
- `Game` - Ensures only one game panel exists
- `Observer` - Central event system
- `ImageManager` - Single image resource registry
- `TextFileManager` - Single text file resource registry
- `ImageLoader` - Single image loader
- `TextFileLoader` - Single text file loader
- `Map` (renderer) - Single map renderer
- `Character` (renderer) - Single character renderer

### 2. **Template Method Pattern**
- `Panel` serves as an abstract base class; concrete panels (`Menu`, `Game`) inherit screen validation logic
- `Renderer` serves as an abstract base class; concrete renderers (`Map`, `Character`) implement `Render()`
- `Manager` serves as an abstract base class; concrete managers (`ImageManager`, `TextFileManager`) register their resources
- `Loader<Data>` serves as an abstract generic base class; concrete loaders (`ImageLoader`, `TextFileLoader`) implement `LoadData()`, `GetManager()`, and `HandleMissingResource()`
- `Controller` serves as an abstract base class; concrete controllers (`FolderController`, `ImageController`, `TextFileController`) provide auto-incrementing IDs

### 3. **Observer Pattern**
- `Observer` manages a map of `Notification` → `List<Runnable>` callbacks
- Components register interest in specific notifications and react accordingly
- Enables decoupled communication between the menu, window, game panel, and renderers

### 4. **Facade Pattern**
- `ViewAPI` acts as a simplified interface for the controller to interact with the view layer

### 5. **MVC Pattern**
- **Model**: Persistance layer (map data in text files, image assets)
- **View**: Window, ViewAPI, Panels, Renderers (UI components and rendering)
- **Controller**: GameRunner, Files Controllers, Files Managers, Loaders, Utils (application flow, resource management, event system)

---

## Code Conventions

The project follows specific naming conventions as defined in the VS Code Copilot instructions:

### Method Names
- **Format**: PascalCase
- **Examples**: `GetInstance()`, `ShowMenu()`, `ChangePanel()`, `LoadMainMap()`

### Variable and Field Names
- **Format**: snake_case
- **Examples**: `screen_width`, `screen_height`, `current_map_name`, `block_width`

### Singleton Pattern Structure
- Static instance field declared first
- `GetInstance()` method placed immediately after
- Constructor and other methods follow

---

## Current Application Flow

```
1.  User runs GameRunner.main()
2.  GameRunner.StartUp() begins:
    a. ImageLoader loads all registered images (world tiles, character sprites) from disk
    b. TextFileLoader loads all registered text files (map data) from disk
3.  GameRunner.Run() begins:
    a. ViewAPI singleton is created, which creates the Window singleton
    b. Window registers observer: NEW_GAME → ShowGame()
    c. Menu and Game panels are instantiated (screen size validated in Panel constructor)
    d. Game panel registers observers: NEW_GAME → OnNewGame(), WINDOW_RESIZED → OnWindowResize()
    e. ViewAPI.ShowMenu() is called → Window displays the Menu panel
    f. Window maximizes and becomes visible
4.  User clicks "New Game" button:
    a. Menu notifies NEW_GAME via Observer
    b. Window.ShowGame() switches to the Game panel
    c. Game.OnNewGame() loads the main map via Map renderer
    d. Map parses the text file, resolves tile images, calculates block dimensions
    e. Window notifies WINDOW_RESIZED → Map updates screen size and recalculates blocks
    f. Game.paintComponent() renders the map (and character placeholder) via Graphics2D
```

---

## Error Handling

### Screen Size Validation
- **Minimum Requirements**: 800x600 pixels
- **Validation Point**: Panel constructor
- **Screen Calculation**: Uses `GraphicsEnvironment.getMaximumWindowBounds()` to exclude Windows taskbar and window toolbar
- **Error Behavior**:
  - Prints error message to stderr
  - Exits application with code `ErrorStatus.MIN_SCREEN_SIZE` (1)

### Resource Loading Errors
- **Image Loading**: Exits with descriptive error codes if images are missing, corrupted, or unsupported
- **Text File Loading**: Exits with descriptive error codes if text files are missing or corrupted
- **Map Parsing**: Exits if the map file is empty or contains unrecognized (non-numeric) elements

### Centralized Exit
- `GameExit.Exit()` provides a standardized way to print error messages and exit with the appropriate code
- All error exits go through this utility to ensure consistent formatting

---

## Build and Execution

### Directory Structure
- **Source**: `src/` - Contains all .java source files
- **Binaries**: `bin/` - Contains compiled .class files
- **Assets**: `src/images/` - Image files (.png), `src/persistance/` - Data files (.txt)

### Compilation

```bash
javac -d bin -sourcepath src src\controller\GameRunner.java
```

### Execution

```bash
java -cp bin controller.GameRunner
```

---

## Dependencies

### External Libraries
- **Java Swing** (javax.swing): For GUI components
- **AWT** (java.awt): For graphics, event handling, and 2D rendering
- **ImageIO** (javax.imageio): For image file loading

### Java Version
- Requires Java Development Kit (JDK)
- Compatible with modern Java versions (JDK 8+)

---

## Testing and Quality

### Current State
- No unit tests implemented yet
- Manual testing of window display, menu interaction, and map rendering

---

## Repository Information

- **Repository**: mfigueireddo/java-game
- **Default Branch**: main
- **Current Branch**: game_rendering
- **Platform**: GitHub
- **License**: Not specified