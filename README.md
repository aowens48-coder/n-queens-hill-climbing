# 8-Queens Solver: Hill Climbing With Random Restarts
An AI agent written in Java that solves the 8-Queens problem using  local search: hill climbing with random restarts.
## The Problem
Place 8 queens on a chessboard so that no two share a row, column, 
or diagonal.

## Approach
- Starts from a random board configuration
- Uses hill climbing to iteratively reduce the number of attacking 
  queen pairs
- Detects local optima (where hill climbing gets stuck) and 
  triggers a random restart to escape them
- Repeats until a conflict-free solution is found
- (no Queens occupy the same row, column, or diagonal) 

## Why random restarts?
Plain hill climbing frequently gets stuck at local optima where no 
single move improves the board, even though a solution exists 
elsewhere in the search space. Random restarts trade a small amount 
of extra computation for a much higher chance of finding the global 
optimum.
