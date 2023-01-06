package client.src.vue.mouseAdapter;

import client.src.Controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterVersoObjectif extends MouseAdapter
{
    private Controleur ctrl;
   
    public MouseAdapterVersoObjectif(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        System.out.println("shesh");
        ctrl.genererInteractionObj();
    }
}