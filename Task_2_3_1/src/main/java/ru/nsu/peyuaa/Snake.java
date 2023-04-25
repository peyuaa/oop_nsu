package ru.nsu.peyuaa;

import java.util.ArrayList;

/**
 * Класс змея
 */
public class Snake {
    // Направление движения змеи
    private SnakeDirection direction;
    // Состояние - жива змея или нет.
    private boolean isAlive;
    // Список кусочков змеи.
    private final ArrayList<SnakeSection> sections;

    private Game game;

    public Snake(Game game, int x, int y) {
        this.game = game;
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return sections.get(0).x();
    }

    public int getY() {
        return sections.get(0).y();
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    /**
     * Метод перемещает змею на один ход.
     * Направление перемещения задано переменной direction.
     */
    public void move() {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP)
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT)
            move(1, 0);
        else if (direction == SnakeDirection.DOWN)
            move(0, 1);
        else if (direction == SnakeDirection.LEFT)
            move(-1, 0);
    }

    /**
     * Метод перемещает змею в соседнюю клетку.
     * Координаты клетки заданы относительно текущей головы с помощью переменных (dx, dy).
     */
    private void move(int dx, int dy) {
        // Создаем новую голову - новый "кусочек змеи".
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.x() + dx, head.y() + dy);

        // Проверяем - не вылезла ли голова за границу комнаты
        checkBorders(head);
        if (!isAlive) return;

        // Проверяем - не пересекает ли змея  саму себя
        checkBody(head);
        if (!isAlive) return;

        // Проверяем - не съела ли змея мышь.
        Mouse mouse = game.getMouse();
        if (head.x() == mouse.x() && head.y() == mouse.y()) // съела
        {
            sections.add(0, head);                  // Добавили новую голову
            game.eatMouse();                   // Хвост не удаляем, но создаем новую мышь.
        } else // просто движется
        {
            sections.add(0, head);                  // добавили новую голову
            sections.remove(sections.size() - 1);   // удалили последний элемент с хвоста
        }
    }

    /**
     * Метод проверяет - находится ли новая голова в пределах комнаты
     */
    private void checkBorders(SnakeSection head) {
        if ((head.x() < 0 || head.x() >= game.getWidth()) || head.y() < 0 || head.y() >= game.getHeight()) {
            isAlive = false;
        }
    }

    /**
     * Метод проверяет - не совпадает ли голова с каким-нибудь участком тела змеи.
     */
    private void checkBody(SnakeSection head) {
        if (sections.contains(head)) {
            isAlive = false;
        }
    }
}
