package com.kduda.battleships.models.board;

import com.kduda.battleships.models.units.GroundLevelUnit;
import com.kduda.battleships.models.units.Plane;
import com.kduda.battleships.models.units.Unit;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PlayerBoard extends Board {
    public PlayerBoard(boolean isEnemyBoard,
                       EventHandler<? super MouseEvent> mouseClickHandler,
                       EventHandler<? super MouseEvent> mouseEnteredHandler,
                       EventHandler<? super MouseEvent> mouseExitedHandler) {
        super(isEnemyBoard, mouseClickHandler, mouseEnteredHandler, mouseExitedHandler);
    }

    //region not used
    @Override
    public void showShootingHint() {

    }

    @Override
    public void removeShootingHint() {

    }
    //endregion

    //region showing and removing hints
    public void showPlacementHint(Unit unit, Cell cell) {
        Position cellPosition = new Position(cell.POSITION.getX(), cell.POSITION.getY());
        currentUnitCells.clear();

        if (unit instanceof GroundLevelUnit)
            showGroundUnitHint((GroundLevelUnit) unit, cellPosition);
        else showPlaneHint((Plane) unit, cellPosition);
    }

    private void showGroundUnitHint(GroundLevelUnit unit, Position cellPosition) {
        if (unit.getOrientation() == Orientation.VERTICAL) {
            if (isVerticalLocationDownwardsValid(unit, cellPosition)) {
                isCurrentUnitLocationValid = true;
                changeCurrentUnitColors(Color.GREEN, Color.GREEN);
            } else {
                isCurrentUnitLocationValid = false;
                changeCurrentUnitColors(Color.RED, Color.RED);
            }

        } else {
            if (isHorizontalLocationForwardValid(unit, cellPosition)) {
                isCurrentUnitLocationValid = true;
                changeCurrentUnitColors(Color.GREEN, Color.GREEN);
            } else {
                isCurrentUnitLocationValid = false;
                changeCurrentUnitColors(Color.RED, Color.RED);
            }
        }
    }

    private void showPlaneHint(Plane plane, Position cellPosition) {
        switch (plane.getDirection()) {
            case NORTH:
                if (isNorthLocationValid(plane, cellPosition)) {
                    isCurrentUnitLocationValid = true;
                    changeCurrentUnitColors(Color.GREEN, Color.GREEN);
                    return;
                } else {
                    this.isCurrentUnitLocationValid = false;
                    changeCurrentUnitColors(Color.RED, Color.RED);
                    return;
                }
            case EAST:
                if (isEastLocationValid(plane, cellPosition)) {
                    isCurrentUnitLocationValid = true;
                    changeCurrentUnitColors(Color.GREEN, Color.GREEN);
                    return;
                } else {
                    this.isCurrentUnitLocationValid = false;
                    changeCurrentUnitColors(Color.RED, Color.RED);
                    return;
                }
            case SOUTH:
                if (isSouthLocationValid(plane, cellPosition)) {
                    isCurrentUnitLocationValid = true;
                    changeCurrentUnitColors(Color.GREEN, Color.GREEN);
                    return;
                } else {
                    this.isCurrentUnitLocationValid = false;
                    changeCurrentUnitColors(Color.RED, Color.RED);
                    return;
                }
            case WEST:
                if (isWestLocationValid(plane, cellPosition)) {
                    isCurrentUnitLocationValid = true;
                    changeCurrentUnitColors(Color.GREEN, Color.GREEN);
                } else {
                    isCurrentUnitLocationValid = false;
                    changeCurrentUnitColors(Color.RED, Color.RED);
                }
        }
    }

    public void removePlacementHint() {
        this.currentUnitCells.forEach(Cell::loadSavedColors);
    }
    //endregion
}
