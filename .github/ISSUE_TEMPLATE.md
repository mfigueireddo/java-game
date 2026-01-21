# Issue Creation Guidelines

## 1. Issue Title Format

```
AS [Who?] I WANT TO [What?] IN ORDER TO [Why?]
```

**Example:**
```
AS a Player I WANT TO see my score IN ORDER TO track my game progress
```

---

## 2. Requirements

### 2.1 Functional Requirements

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

### 2.2 Non-Functional Requirements

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

### 2.3 Enablers

Enablers are technical tasks that support the implementation of features but don't directly deliver user value.

**Examples:**
- Set up unit testing framework for the project
- Refactor collision detection system to support new enemy types
- Create reusable animation component for character sprites
- Implement logging system for debugging game states

---

## 3. Acceptance Criteria Format

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

## 4. Definition of Ready

An issue is ready to be worked on when:

- ✅ **Description Done**: The issue has a clear title, detailed description, and all requirements defined
- ✅ **Acceptance Criteria Defined**: All acceptance criteria are written in GIVEN/WHEN/THEN format

---

## 5. Definition of Done

An issue is complete when:

- ✅ **Unit Tests**: All new model code has corresponding unit tests with passing results
- ✅ **Linter Approval**: Code passes all linting checks (via Copilot in Pull Requests) and follows project conventions
- ✅ **All Acceptance Criteria Achieved**: Every acceptance criterion has been met and verified
- ✅ **Code Review**: Changes have been reviewed and approved (if applicable)
- ✅ **Documentation Updated**: README.md properly updated

---

## Quick Reference Template

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
