package adifpv99.graphics;

import javax.swing.*;
import java.awt.*;

public class Window {

    private static JFrame frame;
    private static JPanel panel = new JPanel();

    public static void Init()  {
        //create new frame
        frame = new JFrame("C");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(200, 75);
        frame.setResizable(false);
        frame.setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);


        //wait for frame to init
        try {
            for (long start = System.currentTimeMillis(); System.currentTimeMillis() < start + 10000; )
                if (frame != null) break;
                else Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("ERROR [2]: Couldn't create Window! InterruptedException: " + e.getMessage());
            System.exit(2);
        }
        if(frame == null){
            System.out.println("ERROR [1]: Couldn't create Window! JFrame init timed out(>10s)");
            System.exit(1);
        }


        //create panel
        panel.setBackground(Color.BLACK);
        panel.setLayout(new FlowLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public static JPanel getPanel(){
        return panel;
    }

    public static JFrame getFrame(){
        return frame;
    }

    public static void addComponent(Component component){
        panel.add(component);
    }

    public static void removeComponent(Component component){
        panel.remove(component);
    }
}
