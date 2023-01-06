package client.src.vue.mouseAdapter;

import client.src.Controleur;
import client.src.metier.common.Wagon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterVersoWagon extends MouseAdapter
{
    private Controleur ctrl;
    
    public MouseAdapterVersoWagon(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        System.out.println("shesh");

        Wagon wagon = ctrl.getWagonVerso();
        if(ctrl.ajouterWagonAJoueur(wagon));
        {
            if(!ctrl.secondWagon())
            {
                ctrl.genererInteractionWagon(wagon);
            }
            
            ctrl.majPioche();
        }
    }
}
