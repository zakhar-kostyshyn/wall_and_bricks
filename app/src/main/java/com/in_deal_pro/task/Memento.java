package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Wall;

public class Memento {

    private Wall wall;
    private int [][] backUp;

    public Memento(Wall wall, int [][] backUp) {
        this.wall = wall;
        this.backUp = backUp;
    }

    public void restore() {
        wall.restore(backUp);
    }

}
