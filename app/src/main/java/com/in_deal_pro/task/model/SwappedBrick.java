package com.in_deal_pro.task.model;

public class SwappedBrick extends AbstractBrick {

    Brick normalBrick;

    public SwappedBrick(Brick normalBrick) {
        super(normalBrick.getHeight(), normalBrick.getWidth(), normalBrick.getBrickCount());
        this.normalBrick = normalBrick;
    }



}
