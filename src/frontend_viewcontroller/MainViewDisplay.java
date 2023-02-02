package frontend_viewcontroller;

import backend_models.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

public class MainViewDisplay extends JFrame {

    Renderer theRenderer;
    BackendModelSetup theBackendModel;

    JPanel floorPanel;
    JLabel skyPanel;//JPanel skyPanel;
    JPanel notePanel;
    JTextArea noteLabel;
    JTextField inputField;
    JButton inputButton;
    JLabel infoField;
    int oldHeight;
    

    public MainViewDisplay(BackendModelSetup aBackend, Renderer theRenderer) {
        this.theBackendModel = aBackend;
        this.theRenderer = theRenderer;
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setMinimumSize(new Dimension(500, 500));
        this.getContentPane().setPreferredSize(new Dimension(1000, 500));
        oldHeight = 500;
        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(null);
        this.pack();
//        
//        GridBagConstraints c;
//        
//        c= new GridBagConstraints();

        for (int i = 0; i < this.theBackendModel.theGrid.items.size(); i++) {
            Item item = this.theBackendModel.theGrid.items.get(i);
            item.label = new JLabel();

            //item.label.setBackground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
            item.label.setSize(item.getWidth(), item.getHeight());
            item.label.setLocation(getContentPane().getWidth() / 2 - item.label.getWidth() / 2, getContentPane().getHeight() / 2 - item.label.getHeight() / 2);
            URL url = getClass().getResource(item.imgPath);
            item.img = new ImageIcon(url);
//getContentPane().setComponentZOrder(item.label, i);
            mainDisplayPane.add(item.label);
        }

        this.floorPanel = new JPanel();
        this.floorPanel.setSize(getContentPane().getWidth(), getContentPane().getHeight() / 2);
        this.floorPanel.setBackground(new Color(35, 186, 45));
        this.floorPanel.setLocation(0, getContentPane().getHeight() / 2);
        mainDisplayPane.add(this.floorPanel);

        this.skyPanel = new JLabel();
        this.skyPanel.setSize(13552, getContentPane().getHeight() / 2);
        this.skyPanel.setBackground(new Color(135, 206, 235));
        this.skyPanel.setLocation(0, 0);
        URL url = getClass().getResource("pictures/sky.png");
        this.skyPanel.setIcon(new ImageIcon(url));
        mainDisplayPane.add(this.skyPanel);

        this.inputField = new JTextField();
        this.inputField.setLocation(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);
        this.inputField.setSize(getContentPane().getWidth() * 2 / 3, getContentPane().getHeight() * 2 / 3);
        this.inputField.setBackground(new Color(193, 154, 107));
        this.inputField.setVisible(false);
        this.inputField.setFont(new Font("Verdana", 1, 50));
        mainDisplayPane.add(this.inputField);

        this.infoField = new JLabel();
        this.infoField.setSize(1000, 50);
        this.infoField.setBackground(new Color(0, 0, 0, 0));
        this.infoField.setLocation(10, getContentPane().getHeight() - 50);
        this.infoField.setFont(new Font("Verdana", 1, 25));
        this.infoField.setVisible(true);
        mainDisplayPane.add(this.infoField);
        this.getContentPane().setComponentZOrder(this.infoField, 0);

//        this.saveButton = new JButton("Save");
//        this.saveButton.setLocation(getContentPane().getWidth()*6/8, getContentPane().getHeight()*7/8);
//        this.saveButton.setSize(getContentPane().getWidth()/8, getContentPane().getHeight()/8);
//        this.saveButton.setBackground(new Color(193, 154, 107));
//        this.saveButton.setVisible(true);
//        mainDisplayPane.add(this.saveButton);
//        
//        this.loadButton = new JButton("Load");
//        this.loadButton.setLocation(getContentPane().getWidth()*7/8, getContentPane().getHeight()*7/8);
//        this.loadButton.setSize(getContentPane().getWidth()/8, getContentPane().getHeight()/8);
//        this.loadButton.setBackground(new Color(193, 154, 107));
//        this.loadButton.setVisible(true);
//        mainDisplayPane.add(this.loadButton);
        this.inputButton = new JButton("enter");
        this.inputButton.setLocation(getContentPane().getWidth() * 5 / 6, getContentPane().getHeight() * 5 / 6);
        this.inputButton.setSize(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);
        this.inputButton.setBackground(new Color(193, 154, 107));
        this.inputButton.setVisible(false);
        mainDisplayPane.add(this.inputButton);

        this.notePanel = new JPanel();
        this.noteLabel = new JTextArea("");
        this.notePanel.add(this.noteLabel);
        this.notePanel.setSize(getContentPane().getWidth() * 2 / 3, getContentPane().getHeight() * 2 / 3);
        this.notePanel.setBackground(new Color(193, 154, 107));
        this.notePanel.setVisible(false);
        this.notePanel.setLocation(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);
        this.noteLabel.setFont(new Font("Verdana", 1, 30));
        this.noteLabel.setLineWrap(true);
        this.noteLabel.setBackground(new Color(193, 154, 107));
        this.noteLabel.setSize((int) this.notePanel.getSize().getWidth() - 10, (int) this.notePanel.getSize().getWidth() - 10);
        this.noteLabel.setBorder(null);
        this.noteLabel.setWrapStyleWord(true);
        this.noteLabel.setMargin(new Insets(20, 20, 20, 20));
        //this.textLabel.setLocation((int)this.textLabel.getLocation().getX(), getContentPane().getHeight()/2-10);
        mainDisplayPane.add(this.notePanel);
        this.updateDisplay();
        this.updateBackground();

        this.infoField.setText("Use wasd to move, f to create new sign");
    }

//    private Image getScaledImage(Image srcImg, int w, int h){
//    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//    Graphics2D g2 = resizedImg.createGraphics();
//
//    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//    g2.drawImage(srcImg, 0, 0, w, h, null);
//    g2.dispose();
//
//    return resizedImg;
//}
    public void updateDisplay() {
        if (theBackendModel.theState.getProgramState() == "running") {
            // System.out.println(theBackendModel.theGrid.items.get(0).getLoc());
            this.theBackendModel.theGrid.items.sort(new Comparator<Item>() {
                public int compare(Item i1, Item i2) {
                    return Double.compare(i1.dist, i2.dist);
                }
            });

            Image newImg;
            this.getContentPane().setComponentZOrder(this.infoField, 0);
            boolean nearSign = false;

            for (int i = 0; i < this.theBackendModel.theGrid.items.size(); i++) {
                Item item = theBackendModel.theGrid.items.get(i);
                //System.out.println(item.label);    // noteboard is panel not label
                this.getContentPane().setComponentZOrder(item.label, i + 1);
                item.dist = this.theBackendModel.theGrid.user.getDistanceFrom(item.getX(), item.getY());
                if (item.dist <= 500 && item.isNoteBoard) {
                    nearSign = true;
                }
                double angle = this.theRenderer.calcPerspectiveAngle(item.getX(), item.getY());
                //System.out.println(angle);
                if (Math.abs(angle) > 1.309) {
                    item.label.setVisible(false);
                } else {
                    item.label.setVisible(true);
                    double size = this.theRenderer.calcRelativeSize(item, getContentPane().getHeight());

                    item.label.setSize((int) size, (int) size * item.getHeight() / item.getWidth());
                    item.label.setLocation((getContentPane().getWidth() / 2 - item.label.getWidth() / 2) + (int) ((getContentPane().getWidth() / 2 + item.label.getWidth() / 2) * angle / (1.309)), (int) (((getContentPane().getHeight() / 2) + ((getContentPane().getHeight() / 2 + 100) * theRenderer.calcPerspectiveDown(item) / 1.309))) - item.label.getHeight());
                    //System.out.println( Math.cos(theRenderer.calcPerspectiveDown(item)));
                    /*laggy*/newImg = item.img.getImage().getScaledInstance(item.label.getWidth(), item.label.getHeight(), java.awt.Image.SCALE_FAST); // scale it the smooth way  
                    /*laggy*/ImageIcon scaledImg = new ImageIcon(newImg);  // transform it back
                    /*laggy*/item.label.setIcon(scaledImg);
//System.out.println((int)(500*angle/(1.309)));
                    //base location on angle
                }
            }
            if (nearSign) {
                this.infoField.setText("Press e to view note");
            } else {
                this.infoField.setText("");
            }
            nearSign = false;
        } else if (theBackendModel.theState.getProgramState() == "note") {

        }
    }

    public void updateBackground() {
        this.floorPanel.setSize(getContentPane().getWidth(), getContentPane().getHeight() / 2);
        this.floorPanel.setLocation(0, getContentPane().getHeight() / 2);

        this.skyPanel.setSize(this.skyPanel.getWidth()*getContentPane().getHeight() / 2/oldHeight, getContentPane().getHeight() / 2);
        this.skyPanel.setLocation(0, 0);
        Image newImg;
        /*laggy*/newImg = ((ImageIcon)this.skyPanel.getIcon()).getImage().getScaledInstance(this.skyPanel.getIcon().getIconWidth()*this.skyPanel.getHeight()/oldHeight, this.skyPanel.getHeight(), java.awt.Image.SCALE_FAST); // scale it the smooth way  
                    /*laggy*/ImageIcon scaledImg = new ImageIcon(newImg);  // transform it back
                    /*laggy*/this.skyPanel.setIcon(scaledImg);

        this.notePanel.setSize(getContentPane().getWidth() * 2 / 3, getContentPane().getHeight() * 2 / 3);
        this.notePanel.setLocation(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);

        this.inputButton.setLocation(getContentPane().getWidth() * 5 / 6, getContentPane().getHeight() * 5 / 6);
        this.inputButton.setSize(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);

        this.inputField.setLocation(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);
        this.inputField.setSize(getContentPane().getWidth() * 2 / 3, getContentPane().getHeight() * 2 / 3);

        this.noteLabel.setSize(this.notePanel.getSize());

        this.infoField.setLocation(10, getContentPane().getHeight() - 50);
        oldHeight = getContentPane().getHeight() / 2;
    }

    public void displayView() {

    }

    public void addSign(NoteBoard n) {
        theBackendModel.theGrid.addSign(n);
        //add physical rep
        Item item = this.theBackendModel.theGrid.items.get(this.theBackendModel.theGrid.items.size() - 1);
        item.label = new JLabel();

        item.label.setSize(item.getWidth(), item.getHeight());
        item.label.setLocation(getContentPane().getWidth() / 2 - item.label.getWidth() / 2, getContentPane().getHeight() / 2 - item.label.getHeight() / 2);
        URL url = getClass().getResource(item.imgPath);
        item.img = new ImageIcon(url);

        this.getContentPane().add(item.label);
    }

//    public ImageIcon manipulateIamge(double topAngle, double bottomAngle, int width, int height, Image i) {
//        
//    }
    public void updateSign(String note) {
        if (this.theBackendModel.theState.getProgramState() == "note") {
            this.noteLabel.setText(note);
            this.notePanel.setVisible(true);
            this.getContentPane().setComponentZOrder(this.notePanel, 0);
            this.notePanel.setLocation(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);
        }
    }

    public void turnRight(boolean b) {
        int newDest;
        if (b) {
            newDest = this.skyPanel.getX() - (int)(this.getContentPane().getWidth()/21.74);
        } else {
            newDest = this.skyPanel.getX() + (int)(this.getContentPane().getWidth()/21.74);
        }
        if (newDest < -this.skyPanel.getWidth() + getContentPane().getWidth()) {
            newDest = 0;
        }
        if (newDest > 0) {
            newDest =  - this.skyPanel.getWidth() + getContentPane().getWidth();
        }
        this.skyPanel.setLocation(newDest, this.skyPanel.getY());

    }

    public void modeChange(String newMode) {

        if (newMode.equals("running")) {
            this.notePanel.setVisible(false);
            this.inputField.setVisible(false);
            this.inputButton.setVisible(false);
            this.inputField.setEditable(false);
            this.infoField.setText("Use wasd to move, f to create new sign, c to save current map");
            this.requestFocus();
            //this.inputField.enableInputMethods(false);
        } else if (!this.theBackendModel.theState.getProgramState().equals("running")) {
            this.theBackendModel.theState.setProgramState("running");
        } else if (newMode.equals("note")) {
            this.infoField.setText("Press e again to exit");
        } else if (newMode.equals("input")) {
            this.infoField.setText("Type desired message and press enter to create sign");
            this.inputField.setVisible(true);
            this.inputButton.setVisible(true);
            this.inputField.setEditable(true);
            this.getContentPane().setComponentZOrder(this.inputButton, 0);
            this.getContentPane().setComponentZOrder(this.inputField, 0);
            this.inputField.setLocation(getContentPane().getWidth() / 6, getContentPane().getHeight() / 6);
        }
        this.theBackendModel.theState.setProgramState(newMode);

    }

    public void displayMenu() {

    }

    public void reInitComps() {
        this.initComponents();
    }

    public void clear() {
        for (int i = 0; i < this.theBackendModel.theGrid.items.size(); i++) {
            this.getContentPane().remove(this.theBackendModel.theGrid.items.get(i).label);
            this.getContentPane().remove(this.infoField);
            this.getContentPane().remove(this.skyPanel);
            this.getContentPane().remove(this.floorPanel);
        }
    }
}
