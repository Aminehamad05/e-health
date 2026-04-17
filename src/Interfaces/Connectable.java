package src.Interfaces;
import src.Exceptions.*;

public interface Connectable {
    void connecter(String reseau) throws RobotException;
    void deconnecter();
    void envoyerDonnees(String donnees) throws RobotException;
}