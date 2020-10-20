package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Point;
import com.in_deal_pro.task.model.Wall;

import java.util.*;

public class Validator {

    private final History history;
    private final ListIterator<Brick> listIterator;
    private final Wall wall;

    public Validator(Set<Brick> bricks, Wall wall) {
        this.history = new History();
        this.wall = wall;
        this.listIterator = new ArrayList<>(bricks).listIterator();
    }

    public boolean check() {
        while (listIterator.hasNext()) {

            Brick brick = listIterator.next();

            if (brick.isThereNOBricks()) {
                if (listIterator.hasNext())
                    brick = listIterator.next();
                else {
                    if (history.isEmpty())
                        return false;
                    rollBack(brick);
                }
            }

            Point pointOnAreaForBrick = wall.getPointOnAreaForBrick(brick);
            if (pointOnAreaForBrick != null) {
                brick.setLastPoint(pointOnAreaForBrick);
                makeStep(brick);
            }

            if (wall.isWallFilled())
                return true;

            if (wall.getPointOnAreaForBrick(brick) != null && !brick.isThereNOBricks())
                listIterator.previous();

            if (!listIterator.hasNext() && (brick.isThereNOBricks() || wall.getPointOnAreaForBrick(brick) == null)) {
                if (history.isEmpty())
                    return false;
                rollBack(brick);
            }
        }

        return false;
    }

    private void rollBack(Brick brick) {
        brick.increaseCountOfSimilarBricksByOne();
        history.restore();
        setToZeroLastPointsInAllLowerBricks();
        listIterator.previous();
        listIterator.previous();
    }

    private void setToZeroLastPointsInAllLowerBricks() {
        int start = 0;
        while (listIterator.hasNext()) {
            Brick next = listIterator.next();
            next.setLastPoint(new Point());
            start++;
        }
        while (start > 0) {
            listIterator.previous();
            start--;
        }
    }

    private void makeStep(Brick brick) {
        history.makeBackUp(wall);
        brick.putIn(wall);
        brick.decreaseBreaksByOne();
    }

}

