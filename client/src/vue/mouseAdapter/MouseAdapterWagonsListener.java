package client.src.vue.mouseAdapter;

import client.src.Controleur;
import client.src.metier.common.Wagon;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterWagonsListener extends MouseAdapter
{
    private Controleur ctrl;

    private JLabel[] lblCartesWagon;

    public MouseAdapterWagonsListener(Controleur ctrl, JLabel[] lblCartesWagon)
    {
        this.ctrl = ctrl;
        this.lblCartesWagon = lblCartesWagon;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {

        Wagon[] wagons = ctrl.getPiocheVisible();
        int indexLabel = 0;

        

        for(int i = 0; i < this.lblCartesWagon.length; i++)
        {
            if((this.lblCartesWagon[i] == e.getSource())){ indexLabel = i; }
        }

        Wagon wagonCorrespondant = wagons[indexLabel];
        if(ctrl.ajouterWagonAJoueur(wagonCorrespondant))
        {
            if(!ctrl.secondWagon())
            {
                ctrl.genererInteractionWagon(wagonCorrespondant);
            }
            
            ctrl.majPioche();
        }
    }    
}