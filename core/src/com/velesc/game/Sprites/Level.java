package com.velesc.game.Sprites;

import java.util.Random;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Level {

//    private CarroControlado car;
//    private Timer timer;
//    private Array<LimitsArea> limitsAreas;
//    private FinishedLine finishLine;
//    private int startPosX;
//    private int startPosY;
//    private int screenWidth;
//    private int roadWidth;
//    private int roadOffset;
//    private int rowHeight;
//    private int levelRows; // number of rows in level
//    private int[] levelRowRoadOffset; // keep track of roadOffset for each row
//    private Random randomGenerator = new Random();
//
//    public Level(int screenWidth, int roadWidth, int roadOffset, int levelLength, int rowHeight) {
//        this.screenWidth = screenWidth;
//        this.roadWidth = roadWidth;
//        this.roadOffset = roadOffset;
//        this.levelRows = levelLength;
//        this.rowHeight = rowHeight;
//        this.levelRowRoadOffset = new int[levelLength];
//        this.limitsAreas = new Array<LimitsArea>();
////        this.startPosX = (screenWidth/2)-(Car.WIDTH/2);
//        this.startPosY = 20;
////        this.car = new CarroControlado(new Vector2(startPosX, startPosY));
//        this.timer = new Timer();
//        generateOffsets();
//        generateLimitsAreas();
//        generateFinishLine();
//        timer.start();
//    }
//
//    // TODO Generate new layout on restart for variety?
//    public void restartLevel() {
////        car.stopCar();
////        car.setPosition(new Vector2(startPosX, startPosY));
//        timer.reset();
//        timer.start();
//    }
//
//    private void generateOffsets() {
//        for (int row=0; row<levelRows; row++) { // for each row in the level
//            levelRowRoadOffset[row] = roadOffset;
//            // generate a random integer (between -4 and +4)
//            int modifier = (randomGenerator.nextInt(9)) - 4;
//            // apply modifier offset provided road would remain in bounds
//            if ((roadOffset + modifier >= 1) && (roadOffset + modifier + roadWidth <= screenWidth)) {
//                roadOffset += modifier;
//            }
//        }
//    }
//
//    private void generateLimitsAreas() {
//        for (int row=0; row<levelRows; row++) { // for each row in the level
//            LimitsArea leftLimit = new LimitsArea(0, row * rowHeight, levelRowRoadOffset[row], rowHeight);
//            limitsAreas.add(leftLimit);
//            LimitsArea rightLimit = new LimitsArea(levelRowRoadOffset[row] + roadWidth, row * rowHeight, screenWidth - (levelRowRoadOffset[row] + roadWidth), rowHeight);
//            limitsAreas.add(rightLimit);
//        }
//    }
//
//    private void generateFinishLine() {
//        finishLine = new FinishedLine(0, levelRows * rowHeight, screenWidth, 40);
//    }
//
////    public Car getCar() {
////        return car;
////    }
//
//    public Timer getTimer() {
//        return timer;
//    }
//
//    public Array<LimitsArea> getLimitsAreas() {
//        return limitsAreas;
//    }
//
//    public FinishedLine getFinishLine() {
//        return finishLine;
//    }
//
//    public int getProgress(int minPosX, int maxPosX) {
//        int range = maxPosX - minPosX;
//        int posX = (int)(minPosX + (range * getProgressDecimalFraction()));
//        return posX;
//    }
//
//    public float getProgressDecimalFraction() {
////        float carNose = getCar().getBounds().getY() + getCar().getBounds().getHeight();
//        float finishLine = getFinishLine().getBounds().getY();
//        return 0;//(carNose/finishLine);
//    }
//
//    public int getRowHeight() {
//        return rowHeight;
//    }
//
//    public int getLevelLength() {
//        return levelRows;
//    }
//
//    public int getScreenWidth() {
//        return screenWidth;
//    }
}