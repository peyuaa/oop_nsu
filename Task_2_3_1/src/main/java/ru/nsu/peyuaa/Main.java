package ru.nsu.peyuaa;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(20, 20);
        Snake snake = new Snake(game, 10, 10);
        game.setSnake(snake);
        game.getSnake().setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }
}
