package the_app;

import frontend_viewcontroller.*;

public class TheApp implements Runnable {

    @Override
    public void run() {
        BackendModelSetup theBackendModel = new BackendModelSetup();
        Renderer theRenderer = new Renderer(theBackendModel);
        MainViewDisplay theMainViewDisplay = new MainViewDisplay(theBackendModel, theRenderer);
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay);
        theMainViewDisplay.setVisible(true);
    }
}