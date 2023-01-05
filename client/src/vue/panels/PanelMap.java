package client.src.vue.panels;

import client.src.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelMap extends JPanel
{
    private Controleur ctrl;

    private BufferedImage fond;
    private int x = 0;
    private int y = 0;

    public PanelMap(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(null);

        try{
            this.fond = ImageIO.read(this.ctrl.getFond());
        } catch(Exception e) { e.printStackTrace(); }
    }  
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics g2d = (Graphics) g;

        g.drawImage(this.fond, this.x, this.y, this.fond.getWidth(), this.fond.getHeight(), null);
    }
}
