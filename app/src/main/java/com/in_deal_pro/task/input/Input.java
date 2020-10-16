package com.in_deal_pro.task.input;

import com.in_deal_pro.task.Brick;
import com.in_deal_pro.task.Wall;

import java.util.List;

public interface Input {
    int getShapeWidth();
    int getShapeHeight();
    Wall getWall();
    int getBricksCount();
    List<Brick> getAllBricks();
}
