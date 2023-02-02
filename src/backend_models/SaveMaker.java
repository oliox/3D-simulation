/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 *
 * @author oli_o
 */
public class SaveMaker  {
    public static void saveState(GridMap g) throws IOException {
        String path = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(new File(path+"/src/backend_models/saves/save.txt"));
        PrintWriter pw = new PrintWriter(fw);
        
        for (int i = 0; i < g.items.size(); i ++) {
            pw.println(g.items.get(i).toString());

        }
        
        pw.close();
        fw.close();
    }
    
    public static void resetSave() throws IOException {
        String path = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(new File(path+"/src/backend_models/saves/save.txt"));
        PrintWriter pw = new PrintWriter(fw);
        
        pw.print("true 5000 5000 pictures/sign.png 500 500 Use WASD to move, F to create new sign, C to save current map, V to reset save \n" +
"false 4000 4500 pictures/rock.png 200 100 \n" +
"false 3500 5000 pictures/tree.png 800 800 \n" +
"false 5000 3500 pictures/tree.png 800 800 \n" +
"false 3500 3500 pictures/tree.png 800 800 ");
        
        pw.close();
        fw.close();
    }
}
