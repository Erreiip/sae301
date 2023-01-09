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
        if(ctrl.actionPossible() && (ctrl.secondWagon() || ! ctrl.getActionEnCours()))
        {
            Wagon[] wagons = ctrl.getPiocheVisible();
            int indexLabel = 0;
    
            for(int i = 0; i < this.lblCartesWagon.length; i++)
            {
                if((this.lblCartesWagon[i] == e.getSource())){ indexLabel = i; }
            }

            System.out.println("en cours d'ajout");
            
            Wagon wagonCorrespondant = wagons[indexLabel];
            if(ctrl.ajouterWagonAJoueur(wagonCorrespondant))
            {
                ctrl.setActionEnCours(true);

                System.out.println("ajouté");

                if(!ctrl.secondWagon())
                {
                    System.out.println("change l'action");
                    ctrl.setActionEnCours(false);
                }
                
                ctrl.genererInteractionWagon(wagonCorrespondant);
                ctrl.majPioche();
            }
        }
    }    
}