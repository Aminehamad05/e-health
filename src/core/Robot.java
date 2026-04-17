package src.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import src.Exceptions.*;
abstract class Robot {

    protected String id;
    protected int x, y;
    protected int energie; // 0 - 100
    protected int heuresUtilisation;
    protected boolean enMarche;
    protected ArrayList<String> historiqueActions;

    public Robot(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.energie = 100;
        this.heuresUtilisation = 0;
        this.enMarche = false;
        this.historiqueActions = new ArrayList<>();

        ajouterHistorique("Robot créé");
    }

    protected void ajouterHistorique(String action) {
        String log = LocalDateTime.now() + " : " + action;
        historiqueActions.add(log);
    }

    protected void verifierEnergie(int requise) throws EnergieInsuffisanteException {
        if (energie < requise) {
            throw new EnergieInsuffisanteException("Énergie insuffisante");
        }
    }

    protected void verifierMaintenance() throws MaintenanceRequiseException {
        if (heuresUtilisation > 100) {
            throw new MaintenanceRequiseException("Maintenance requise");
        }
    }

    public void demarrer() throws RobotException {
        if (energie < 10) {
            throw new RobotException("Pas assez d'énergie pour démarrer");
        }
        enMarche = true;
        ajouterHistorique("Robot démarré");
    }

    public void arreter() {
        enMarche = false;
        ajouterHistorique("Robot arrêté");
    }

    protected void consommerEnergie(int q) {
        energie = Math.max(0, energie - q);
    }

    protected void recharger(int q) {
        energie = Math.min(100, energie + q);
    }

    public String getHistorique() {
        return String.join("\n", historiqueActions);
    }

    public abstract void deplacer(int x, int y) throws RobotException;

    public abstract void effectuerTache() throws RobotException;

    @Override
    public String toString() {
        return "Robot [ID: " + id +
                ", Position: (" + x + "," + y + ")" +
                ", Énergie: " + energie + "%" +
                ", Heures: " + heuresUtilisation + "]";
    }
}