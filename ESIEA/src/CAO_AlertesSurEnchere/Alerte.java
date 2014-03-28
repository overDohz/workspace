package CAO_AlertesSurEnchere;

import CAO_Encheres.Enchere;
import CAO_UtilisateurDuSysteme.Utilisateur;

public class Alerte {
	private Utilisateur utilisateur;
	private NOTIFICATION notification;
	private Enchere enchere;

	public Alerte(Utilisateur utilisateur, NOTIFICATION notification, Enchere enchere){
		this.utilisateur=utilisateur;
		this.notification=notification;
		this.enchere=enchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public NOTIFICATION getNotification() {
		return notification;
	}

	public void setNotification(NOTIFICATION notification) {
		this.notification = notification;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}
	
//	  public void addObservateur(Observateur obs){
//	  }
//	  public void updateObservateur(){
//	  }
//	  public void delObservateur(){
//	  }
	
	public enum NOTIFICATION {
		OFFREEMISE,OFFRESUP,PRIXDERESERVE,ENCHEREANNULEE
	}

}
