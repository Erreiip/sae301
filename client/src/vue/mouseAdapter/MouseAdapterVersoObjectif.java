package client.src.vue.mouseAdapter;

import client.src.Controleur;
import client.src.metier.common.Objectif;

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

        if(!ctrl.getActionEnCours() && ctrl.actionPossible() && this.ctrl.getTour() != 1 )
        {
            boolean bBon = false;
            for ( Objectif o : this.ctrl.getPiocheVisibleObj())
            {
                if ( o != null ) bBon = true;    
            }

            if ( !bBon ) return;
            
            ctrl.setActionEnCours(true);
            ctrl.genererInteractionObj();
        }
    }
}