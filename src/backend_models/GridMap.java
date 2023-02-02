/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class GridMap {

    public ArrayList<Item> items;
    public ArrayList<NoteBoard> noteBoards;
    public User user;
    public String programState;

    public GridMap() {
        this.programState = "running";
        items = new ArrayList<Item>();
        noteBoards = new ArrayList<NoteBoard>();
        this.user = new User(this, 5000, 5000);
        //this.user.angleTo(this.getLoc(250, 1));
        //this.addItem(this.getLoc(499, 499));
        //this.addItem(this.getLoc(499, 200));
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                this.addItem(new Item(2000 * j + 1500, 2000 * i + 1500, "pictures/tree.png", 500, 500));
            }
            //this.addItem(this.getLoc(250, 100*i));
        }
        this.addSign(new NoteBoard(5500, 5500, "pictures/sign.png", 500, 500, "hi"));
        this.addSign(new NoteBoard(4500, 5500, "pictures/sign.png", 500, 500, "hi"));
        this.addSign(new NoteBoard(6500, 5500, "pictures/sign.png", 500, 500, "hi"));
        try {
            this.loadSave();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GridMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addItem(Item i) {
        //Item i = new Item(x, y, "pictures/tree.png", 500, 500);
        items.add(i);
        //System.out.println(i.getClass());
    }

    public void addSign(NoteBoard i) {
        //Item i = new Item(x, y, "pictures/tree.png", 500, 500);
        items.add(i);
        noteBoards.add(i);
        //System.out.println(i.getClass());
    }

    public void moveItem() {

    }

    public String convertToSave() {
        return null;
    }

    public String getProgramState() {
        return null;
    }

    public void setProgramState(String s) {

    }
//    public Location getLoc(int x, int y) {
//        if (x >= 0 && x < mapArr.length && y >= 0 && y < mapArr[0].length) {
//            return mapArr[x][y];
//        } else {
//            return null;
//        }
//
//    }

    public User getUser() {
        return this.user;
    }

    public void loadSave() throws FileNotFoundException {
        String path = System.getProperty("user.dir");
        Scanner sc = new Scanner(new File(path + "/src/backend_models/saves/save.txt"));
        sc.useDelimiter(" ");

        this.programState = "running";
        items = new ArrayList<Item>();
        noteBoards = new ArrayList<NoteBoard>();
        this.user = new User(this, 5000, 5000);

        while (sc.hasNext()) {
            if (sc.next().equals("true")) {
                this.addSign(new NoteBoard(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), sc.next(), Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), sc.nextLine().strip()));
            } else {
                this.addItem(new Item(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), sc.next(), Integer.parseInt(sc.next()), Integer.parseInt(sc.next())));
                sc.nextLine();
            }
        }

        sc.close();
    }
}
