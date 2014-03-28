package CAO_AlertesSurEnchere;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import CAO_Encheres.Enchere;

public class AlerteObserver implements Observer {
	
	private HashSet<Alerte> listeAlerte;
	
	public AlerteObserver(HashSet<Alerte> listeAlerte) {
		this.listeAlerte=listeAlerte;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Enchere && arg1.equals(Enchere.ETAT.ANNULEE))
		{
			for (Alerte alerte : listeAlerte)
			{
				if(alerte.getEnchere() == arg0 && alerte.getNotification().equals(Alerte.NOTIFICATION.ENCHEREANNULEE))
				{
					alerte.getUtilisateur().listeNotification.add("notif");
				}
			}	
		}	
	}
}
