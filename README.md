Tic Tac Toe - Android (Java)
A polished, native Android Tic-Tac-Toe game featuring a modern UI, persistent score tracking, and an optimized codebase. This project demonstrates the implementation of a grid-based game engine using the MVC (Model-View-Controller) pattern.

🚀 Features
Dynamic Gameplay: Interactive 3x3 grid with real-time turn tracking and win/draw detection.

Persistent Scoreboard: Tracks wins for Player X and Player O across multiple rounds within a session.

Polished UI/UX:

Custom-styled scorecards with rounded corners and distinct player branding.

Responsive design using a GridLayout and reusable XML styles.

Clear visual feedback with color-coded "X" (Crimson) and "O" (Deep Navy) symbols.

Clean Architecture: Efficient button mapping using dynamic resource identifiers to minimize boilerplate code.

🛠 Tech Stack
Language: Java

UI Layout: XML (LinearLayout, GridLayout)

Styling: Material Components & Custom XML Drawables

Minimum SDK: Compatible with modern Android versions using AppCompat

🏗 Key Logic Implementation
The game logic is centralized in MainActivity.java, utilizing:

Win Detection: An array-based lookup of the 8 possible winning combinations (rows, columns, and diagonals).

State Management: An integer array board (0=empty, 1=X, 2=O) handles the internal state, while roundCount ensures accurate draw detection.

Resource Management: A unified CellButton style ensures consistent button sizing (100dp) and elevation across all devices.
