package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Wall;

import java.util.ArrayDeque;
import java.util.Deque;

public class History {

    private final Deque<Memento> queue = new ArrayDeque<>();

    public void restore() {
        Memento pop = queue.pop();
        pop.restore();
    }

    public void makeBackUp(Wall wall) {
        Memento memento = wall.makeBackUp();
        queue.push(memento);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
