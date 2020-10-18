package com.in_deal_pro.task.input;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;

import java.util.Set;

public interface Input {
    int getShapeWidth();
    int getShapeHeight();
    int getBricksCount();
    Wall getWall();
    Set<Brick> getAllBricks();
}
