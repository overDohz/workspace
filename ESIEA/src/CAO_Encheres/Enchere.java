package CAO_Encheres;

import java.util.ArrayList;
import java.util.Date;

import CAO_UtilisateurDuSysteme.Utilisateur;

public class Enchere {

	private Utilisateur createur;
	private Date dateLimite;
	private double prixMinimum;
	private double prixDeReserve;
	private ETAT etat;
	public static ArrayList<Enchere> listeEnchere;
	private Objet objet;
	
	public Enchere(ArrayList<Enchere> listeEnchere,ETAT etat, Utilisateur createur, Date dateLimite, double prixMinimum,double prixDeReserve, String identifiant, String description) {
		// TODO Auto-generated constructor stub
		//modif liste Enchere
		this.etat=etat;
		this.createur=createur;
		this.dateLimite=dateLimite;
		this.prixMinimum=prixMinimum;
		this.prixDeReserve=prixDeReserve;
		objet=new Objet(identifiant,description);
		this.listeEnchere=listeEnchere;
	}
	
	public Utilisateur getCreateur() {
		return this.createur;
	}

	public double getPrixDeReserve(Utilisateur utilisateur) {
		//check si vendeur
		if (utilisateur.getRole()==createur.getRole()){
			return prixDeReserve;
		}else{
			return 0;
		}
	}
	
	public boolean PrixDeReserveNonAtteint(){
//		if (prixDeReserve>createur.rechercheMeilleurOffre()){
//			return true;
//		}else{
//			return false;
//		}
		if(createur.existeOffre()){
			return prixDeReserve>createur.rechercheMeilleurOffre();
		}else{
			return false;
		}
	}

	public double getPrixMinimum() {
		return prixMinimum;
	}

	public ETAT getEtat() {
		return etat;
	}

	public void setEtatPubliee() {
		etat=ETAT.PUBLIEE;
		
	}
	public void setEtat(ETAT etat){
		this.etat=etat;
	}

	public enum ETAT {
		CREEE, PUBLIEE, TERMINEE, ANNULEE
	}
}
