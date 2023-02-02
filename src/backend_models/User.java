/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

//import backend_models.GridMap;
/**
 *
 * @author student
 */
public class User {

    private int xLoc;
    private int yLoc;
    private double direction;
    private double viewAngle;
    private GridMap grid;
    private double xRatio;
    private double yRatio;
    private double xyComb;
    private int xSteps;
    private int ySteps;

    public User(GridMap g, int xLoc, int yLoc) {
        this.grid = g;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.direction = Math.PI/2;
        xSteps = 1;
        ySteps = 1;
        this.changeDirection(0);
    }

    public void move() {
        int destX = this.xLoc;
        int destY = this.yLoc;

        int ySteps = (int) Math.round(50 * xyComb / (1 + xyComb));
        int xSteps = 50 - ySteps;

        //System.out.print(xSteps + ", " + ySteps);
        if (yRatio < 0) {
            destY -= ySteps;
        } else {
            destY += ySteps;
        }

        if (xRatio < 0) {
            destX -=xSteps;
        } else {
            destX +=xSteps;
        }

        this.setLoc(destX, destY);
    }

    public void moveBack() {
        int destX = this.xLoc;
        int destY = this.yLoc;

        int ySteps = (int) Math.round(50 * xyComb / (1 + xyComb));
        int xSteps = 50 - ySteps;

        //System.out.print(xSteps + ", " + ySteps);
        if (yRatio < 0) {
            destY += ySteps;
        } else {
            destY -= ySteps;
        }

        if (xRatio < 0) {
            destX +=xSteps;
        } else {
            destX -=xSteps;
        }

        this.setLoc(destX, destY);
    }

    public void changeDirection(double angle) {
        this.direction += angle;
        if (this.direction > 2 * Math.PI) {
            this.direction = 0;
        }
        if (this.direction < 0) {
            this.direction = 2 * Math.PI;
        }
        xRatio = Math.cos(this.direction);
        yRatio = Math.sin(this.direction);
        if (xRatio != 0) {
            xyComb = Math.abs(yRatio / xRatio);
        } else {
            xyComb = Integer.MAX_VALUE;
        }
        xSteps = 1;
        ySteps = 1;
    }

//    public Location getLoc() {
//        //System.out.println(this.loc);
//        return this.loc;
//    }
    public void setLoc(int x, int y) {
        this.xLoc = x;
        this.yLoc = y;
    }

    public int getX() {
        return this.xLoc;
    }

    public int getY() {
        return this.yLoc;
    }

    public double getDistanceFrom(int x, int y) {
        double answer;
        answer = Math.sqrt(Math.pow(x - this.xLoc, 2) + Math.pow(y - this.yLoc, 2));
        return answer;
    }

//    public double[] itemDistance(Location loc) { //return {distance to, angle to}
//        return null;
//    }
    public double getDirection() {
        return this.direction;
    }
    
    public String nearSign() {
        double minDist = Integer.MAX_VALUE;
        int minPoint = 0;
        double dist;

        for (int i = 0; i < grid.noteBoards.size(); i ++) {
            dist = this.getDistanceFrom(grid.noteBoards.get(i).getX(), grid.noteBoards.get(i).getY());
            if (dist < minDist && grid.noteBoards.get(i).isNoteBoard) {
                minDist = dist;
                minPoint = i;
            }
        }
        if (minDist <= 500) {
            return grid.noteBoards.get(minPoint).note;
        } else return null;
    }
}

//        //slope = sindirection/cosdirection
//        double angle = this.direction%(Math.PI/2);
//        System.out.println(angle);
//        double xValue = (.5*Math.sin(2*angle)*loc.getY()+Math.pow(Math.sin(angle), 2)*this.xLoc + .5*Math.sin(2*angle)*this.yLoc - loc.getX()*Math.pow(Math.cos(angle), 2))/(-1*Math.cos(2*angle)+.0001);
//        System.out.println((-1*Math.cos(2*angle)));
//        double distanceOffCenter = Math.sqrt(Math.pow(Math.sin(angle)/Math.cos(angle)*(xValue - this.loc.getX()) - this.loc.getY(), 2) + Math.pow(xValue - loc.getX(), 2));
//        double distanceFromUser = this.getDistanceFrom(loc);
//        
//        //System.out.print(Math.asin(distanceOffCenter/distanceFromUser));
//        return Math.asin(distanceOffCenter/distanceFromUser);
