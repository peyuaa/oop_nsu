package ru.nsu.peyuaa;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    private static final String []FOODS_IMAGE = new String[]{
        "apple.png",
        "berry.png",
        "cherry.png",
        "coconut.png",
        "orange.png",
        "peach.png",
        "pomegranate.png",
        "tomato.png",
        "watermelon.png",
    };

    private static final String fontName = "Digital-7";
    private static final String gameTitle = "Snake";
    private static final String gameOverText = "GAME OVER";
    private static final int gameOverFontSize = 70;
    private static final Color snakeColor = Color.web("4674E9");
    private static final Color firstBackgroundColor = Color.web("AAD752");
    private static final Color secondBackgroundColor = Color.web("A2D149");

    private static final Duration frameUpdate = Duration.millis(130);

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private final List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private Image foodImage;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int currentDirection;
    private int score = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(gameTitle);
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if ((code == KeyCode.RIGHT || code == KeyCode.D) && currentDirection != LEFT) {
                currentDirection = RIGHT;
            } else if ((code == KeyCode.LEFT || code == KeyCode.A) && currentDirection != RIGHT) {
                currentDirection = LEFT;
            } else if ((code == KeyCode.UP || code == KeyCode.W) && currentDirection != DOWN) {
                currentDirection = UP;
            } else if ((code == KeyCode.DOWN || code == KeyCode.S) && currentDirection != UP) {
                currentDirection = DOWN;
            }
        });

        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        generateFood();

        Timeline timeline = new Timeline(new KeyFrame(frameUpdate, e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font(fontName, gameOverFontSize));
            gc.fillText(gameOverText, WIDTH / 3.5, HEIGHT / 2.0);
            return;
        }
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore(gc);

        for (int i = snakeBody.size() - 1; i > 0; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

        switch (currentDirection) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }

        gameOver();
        eatFood();
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(firstBackgroundColor);
                } else {
                    gc.setFill(secondBackgroundColor);
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);
            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    private void drawFood(GraphicsContext gc) {
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(snakeColor);
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE,
                SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE,
                    snakeBody.get(i).getY() * SQUARE_SIZE,
                    SQUARE_SIZE - 1, SQUARE_SIZE - 1, 20, 20);
        }
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUp() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }

    public void gameOver() {
        if  (snakeHead.x < 0 || snakeHead.y < 0
                || snakeHead.x * SQUARE_SIZE >= WIDTH ||  snakeHead.y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }

        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.getX() == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            generateFood();
            score += 5;
        }
    }

    private void drawScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(fontName, 35));
        gc.fillText("Score: " + score, 10, 35);
    }

    public static void main(String[] args) {
        launch(args);
    }
}