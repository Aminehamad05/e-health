package src.core;

import src.Exceptions.*;
import src.Interfaces.*;

public abstract class RobotConnecte extends Robot implements Connectable {

    protected boolean connecte;
    protected String reseauConnecte;

    public RobotConnecte(String id, int x, int y) {
        super(id, x, y);
        this.connecte = false;
        this.reseauConnecte = null;
    }

    @Override
    public void connecter(String reseau) throws RobotException {
        verifierEnergie(5);
        this.connecte = true;
        this.reseauConnecte = reseau;
        consommerEnergie(5);
        ajouterHistorique("Connecté à " + reseau);
    }

    @Override
    public void deconnecter() {
        this.connecte = false;
        this.reseauConnecte = null;
        ajouterHistorique("Déconnecté du réseau");
    }

    @Override
    public void envoyerDonnees(String donnees) throws RobotException {
        if (!connecte) {
            throw new RobotException("Robot non connecté");
        }
        verifierEnergie(3);
        consommerEnergie(3);
        ajouterHistorique("Données envoyées: " + donnees);
    }
}