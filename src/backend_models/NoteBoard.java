/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author student
 */
public class NoteBoard extends Item {
    public String note;


    public NoteBoard(int xLoc, int yLoc, String img, int width, int height, String note) {
        super(xLoc, yLoc, img, width, height);
        this.note = note;
        this.isNoteBoard = true;
    }

    
    public void removeSelfFromGrid() {
        
    }
    
    @Override
    public String toString() {
        return this.isNoteBoard+" "+this.xLoc+" "+this.yLoc+" "+this.imgPath+" "+this.width+" "+this.height+" "+this.note+" ";
    }
}
