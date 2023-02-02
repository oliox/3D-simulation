/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author student
 */
public class Item {

    public int xLoc;
    public int yLoc;
    public String imgPath;
    public int width;
    public int height;
    public double dist;
    public JLabel label;
    public ImageIcon img;
    public boolean isNoteBoard;

    public Item(int xLoc, int yLoc, String img, int width, int height) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.imgPath = img;
        this.height = height;
        this.width = width;
        this.isNoteBoard = false;
        //System.out.println(this.imgPath);
    }

    public int getX() {
        return this.xLoc;
    }

    public int getY() {
        return this.yLoc;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return this.isNoteBoard + " " + this.xLoc + " " + this.yLoc + " " + this.imgPath + " " + this.width + " " + this.height + " ";
    }

}
