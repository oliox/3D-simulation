/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend_viewcontroller;

import backend_models.Item;
import backend_models.User;

/**
 *
 * @author studentwww
 */
public class Renderer {
    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;
    
    public Renderer(BackendModelSetup theBackendModel) {
        this.theBackendModel = theBackendModel;
        //this.theMainViewDisplay = theMainViewDisplay;
    }
    
    public double calcPerspectiveAngle(int x, int y) { 
        //System.out.println(theBackendModel.theGrid.getUser().getLoc());
        double angleToLOS = theBackendModel.theGrid.getUser().getDirection();
        double xDist = x - theBackendModel.theGrid.getUser().getX();
        double yDist = y - theBackendModel.theGrid.getUser().getY();
        double angleToObject;
        if (xDist != 0) {
            angleToObject = Math.abs(Math.atan((yDist)/(xDist)));
        } else {
            angleToObject = Math.PI/2;
        }

//System.out.println(Math.atan((yDist)/(xDist)));
        if (yDist < 0) {
            if (xDist > 0) {
                angleToObject = 2*Math.PI - angleToObject;
            } else {
                angleToObject += Math.PI;
            }
        } else if (xDist < 0) {
            angleToObject = Math.PI - angleToObject;
        }
        
        double alt = angleToLOS-2*Math.PI - angleToObject;
        double doubleAlt = 2*Math.PI - angleToObject + angleToLOS;
        double ans = (angleToLOS - angleToObject);
        double lowest;
        
        if (Math.abs(alt) < Math.abs(ans)) {
            //System.out.println("alta");
            lowest = alt;
        } else {
            lowest = ans;  
        }
        if (Math.abs(doubleAlt) < Math.abs(lowest)) {
            //System.out.println(doubleAlt);
            return doubleAlt;
        } else {
            //System.out.println(lowest);
            return lowest; 
        }
    }
    
    public double calcRelativeSize(Item i, int height) {
       double size;
       double angle = Math.atan((double)i.width/i.dist);
       size = (angle/(1.309))*height;
       return size;
    } 
    
    public double calcPerspectiveDown(Item i) {
        //System.out.println( i.dist);
        double angle = Math.atan((double)500/i.dist);
        return angle;
    }
}
