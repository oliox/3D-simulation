/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

/**
 *
 * @author oliox
 */
public class ProgramState {
    private String programState;
    
    public ProgramState() {
        this.programState = "running";
    }
    
     public String getProgramState() {
        return this.programState;
    }
    
    public void setProgramState(String s) {
        this.programState = s;
    }
}
