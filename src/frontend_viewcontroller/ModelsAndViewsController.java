package frontend_viewcontroller;

import backend_models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;

public class ModelsAndViewsController {

    public BackendModelSetup theBackendModel;
    public MainViewDisplay theMainViewDisplay;

//    private class pauseButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            
//        }
//    }
    private class wKeyListener implements KeyListener {

//        public BackendModelSetup theBackendModel;
//        public MainViewDisplay theMainViewDisplay;
        int i = 0;

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                theBackendModel.theGrid.getUser().move();
                theMainViewDisplay.updateDisplay();
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                theBackendModel.theGrid.getUser().moveBack();
                theMainViewDisplay.updateDisplay();
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println(theBackendModel.theState.getProgramState());
            if (theBackendModel.theState.getProgramState() == "running") {
                i++;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    theBackendModel.theGrid.getUser().move();
                }

                if (e.getKeyCode() == KeyEvent.VK_A) {
                    theBackendModel.theGrid.getUser().changeDirection(0.1);
                    theMainViewDisplay.turnRight(false);
                }

                if (e.getKeyCode() == KeyEvent.VK_D) {
                    theBackendModel.theGrid.getUser().changeDirection(-0.1);
                    theMainViewDisplay.turnRight(true);
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    theBackendModel.theGrid.getUser().moveBack();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    String s = theBackendModel.theGrid.user.nearSign();
                    if (theBackendModel.theGrid.user.nearSign() != null) {
                        theMainViewDisplay.modeChange("note");
                        theMainViewDisplay.updateSign(theBackendModel.theGrid.user.nearSign());

                        //System.out.println("ok");
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_F) {
                    theMainViewDisplay.modeChange("input");
                }
                
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    try {
                        SaveMaker.saveState(theBackendModel.theGrid);
                    } catch (IOException ex) {
                        Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_V) {
                    try {
                        theMainViewDisplay.clear();
                        SaveMaker.resetSave();
                        theBackendModel.theGrid.loadSave();
                        theMainViewDisplay.reInitComps();
                    } catch (IOException ex) {
                        Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (i == 1) { //10
                    theMainViewDisplay.updateDisplay();
                    i = 0;
                }
            } else if (theBackendModel.theState.getProgramState() == "note") {
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    theMainViewDisplay.modeChange("running");
                }

            } else if (theBackendModel.theState.getProgramState() == "input") {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { //doesnt happen because enter gets typed into inputfield 
                    theMainViewDisplay.addSign(new NoteBoard(theBackendModel.theGrid.getUser().getX(), theBackendModel.theGrid.getUser().getY(), "pictures/sign.png", 500, 500, theMainViewDisplay.inputField.getText()));
                    theBackendModel.theGrid.getUser().moveBack();
                    theMainViewDisplay.modeChange("running");
                    theMainViewDisplay.updateDisplay();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private class resizeEvent implements ComponentListener {

        @Override
        public void componentHidden(ComponentEvent arg0) {
        }

        public void componentMoved(ComponentEvent arg0) {
        }

        public void componentShown(ComponentEvent arg0) {

        }

        public void componentResized(ComponentEvent componentEvent) {
            theMainViewDisplay.updateDisplay();
            theMainViewDisplay.updateBackground();
        }
    }

    private class InputButtonPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (theBackendModel.theState.getProgramState() == "input") {
                theMainViewDisplay.addSign(new NoteBoard(theBackendModel.theGrid.getUser().getX(), theBackendModel.theGrid.getUser().getY(), "pictures/sign.png", 200, 200, theMainViewDisplay.inputField.getText()));
                theBackendModel.theGrid.getUser().moveBack();
                theMainViewDisplay.modeChange("running");
                theMainViewDisplay.updateDisplay();
            }
        }
    }

    private class enterKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) { //doesnt happen because enter gets typed into inputfield
                theMainViewDisplay.addSign(new NoteBoard(theBackendModel.theGrid.getUser().getX(), theBackendModel.theGrid.getUser().getY(), "pictures/sign.png", 500, 500, theMainViewDisplay.inputField.getText()));
                theBackendModel.theGrid.getUser().moveBack();
                theMainViewDisplay.modeChange("running");
                theMainViewDisplay.updateDisplay();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

//
//    private class saveButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//
//        }
//    }
//
//    private class loadButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//
//        }
//    }
    public ModelsAndViewsController(BackendModelSetup aBackend, MainViewDisplay aMainViewDisplay) {
        this.theBackendModel = aBackend;
        this.theMainViewDisplay = aMainViewDisplay;
        this.initController();

    }

    private void initController() {
        this.theMainViewDisplay.addKeyListener(new wKeyListener());
        this.theMainViewDisplay.addComponentListener(new resizeEvent());
        this.theMainViewDisplay.inputButton.addActionListener(new InputButtonPressed());
        this.theMainViewDisplay.inputField.addKeyListener(new enterKeyListener());
    }
}
