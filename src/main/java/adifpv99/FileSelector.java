package adifpv99;

import javax.swing.*;
import java.io.File;

public class FileSelector {
    public static String selectPath(JButton btn){
        JFileChooser fc = new JFileChooser();
        int fileChooserOutput = fc.showOpenDialog(btn);

        File file;
        String path = "";

        if(fileChooserOutput == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();

            String fileStr = file.toString();
            if(!(file.isFile()))path = "";
            if(!(fileStr.startsWith(".c", fileStr.length() - 2)))path = "";
            else path = file.getAbsolutePath();
        }

        return path;
    }
}
