package adifpv99;

import adifpv99.graphics.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static JButton selectSourceBtn = decorateButton(new JButton("Select source"));
    private static JButton runBtn = decorateButton(new JButton("Run"));

    private static String filePath = "";

    static void main() {
        Window.Init();

        Window.addComponent(selectSourceBtn);
        Window.addComponent(runBtn);

        selectSourceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selectFile();
            }
        });
        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                run();
            }
        });
    }

    private static JButton decorateButton(JButton btn){
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    private static void selectFile(){
        filePath = FileSelector.selectPath(selectSourceBtn);
    }

    private static void run(){
        if(filePath.isEmpty()) return;

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "start", "/wait", "cmd.exe", "/c", "\"echo Compiling: && gcc " + filePath + " -o .\\program.exe && cls && .\\program.exe & pause\"");

            processBuilder.inheritIO();

            Process process = processBuilder.start();

            process.waitFor();

        } catch (IOException e) {
            IO.println("ERROR [3]: Couldn't execute a command! IOException: " + e.getMessage());
            System.exit(3);
        } catch (InterruptedException e) {
            IO.println("ERROR [4]: Couldn't execute a command! InterruptedException: " + e.getMessage());
            System.exit(4);
        }
    }
}
