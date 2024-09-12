#include "tetris.h"

#include "../gui/cli.h"

int main() {
  struct timespec sp_start, sp_end = {0, 0};
  srand(time(NULL));
  initGui();
  initGame();

  while (tetg->state != GAMEOVER) {
    clock_gettime(CLOCK_MONOTONIC, &sp_start);
    userInput(getAction(), 0);

    GameInfo_t game_info = updateCurrentState();

    if (tetg->state == GAMEOVER) {
      freeGui(game_info, tetg->figurest->size, tetg->field->height);
      continue;
    } else
      printGame(game_info, sp_start, sp_end);
  };
  freeGame(tetg);

  endwin();

  return 0;
}

/**
 * @mainpage BrickGame v1.0 aka Tetris
 *
 * ##Introduction
 * BrickGame v1.0 is a console-based Tetris game developed in C using the
ncurses library. It features classic Tetris gameplay with additional mechanics
such as score counting, level progression, and high score tracking.
* @image html game.png width=300px
 *
 * ## Features
 *- Classic Tetris gameplay with the following mechanics:
    - Rotation of tetrominoes
    - Horizontal movement of tetrominoes
    - Accelerated tetromino falling (by holding the down arrow key)
    - Display of the next tetromino
    - Line clearing
    - Game over when the tetromino reaches the top of the game field
 *- Support for all physical buttons on the console:
    - Start new game - Enter
    - Pause - 'p'
    - End game - 'q'
    - Move left -  left arrow
    - Move right -  right arrow
    - Move down - down arrow
    - Rotate - Space
 *- Matrix-based game field with dimensions corresponding to the console's
size (10x20 pixels)
 *- Proper stopping of tetrominoes after reaching the bottom or colliding with
another tetromino
 *- Unit tests for the game logic library, achieving at least 90% code
coverage
 *
 * --
 * ## Additional mechanics
 *
 * ### Score Calculation

- 1 line cleared: 100 points
- 2 lines cleared: 300 points
- 3 lines cleared: 700 points
- 4 lines cleared: 1500 points

 * ### High Score Tracking

- Display of the player's current score and highest score achieved
- Highest score is stored in a file or embedded database and persists between
game sessions
 *
 * ### Level Progression

- Progression of levels every 600 points
- Increasing level increases the speed of tetromino falling
 *
 --
 * ## Installation

*1. Clone the repository.
*2. Navigate to the `src/` directory.
*3. To install the project run @code make install @endcode
*4. To start playing run @code make run @endcode
 *
 *
 * ## Usage
 *
- Use the arrow keys to move and rotate tetrominoes.
- Press the down arrow key to make tetrominoes fall faster.
- Press the up arrow key to rotate tetrominoes.
 *
 * ## Fsm Finite State Machine (FSM) Diagram
 * A diagram showing the FSM used in the game logic.
 * @image html fsm.png width=900px
 */