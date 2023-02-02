package frontend_viewcontroller;

import backend_models.*;

public class BackendModelSetup {
    GridMap theGrid;
    ProgramState theState;
    //User theUser;
    
    public BackendModelSetup() {
        this.theState = new ProgramState();
        this.theGrid = new GridMap();
        
        //this.theUser = new User(this.theGrid);
    }
    
    
    
}
            