package CAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import CAO.Enchere.ETAT;

public class Utilisateur implements Acheteur,Vendeur{
	
	private String nom;
	private String prenom;
	private String login;
	private String role;
	private Enchere enchere;
	private Date dateActuelle;
	private GregorianCalendar calendrier;
	ArrayList<Enchere> listeEnchere;
	ArrayList<Offre> listeOffre;
	
	public static void main(String Args[]){
		
		
	}
	
	public Utilisateur(String login, ROLE role) {
		this.login=login;
		this.role=role.toString();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Utilisateur(String login, ROLE role,String nom, String prenom) {
		this.login=login;
		this.role=role.toString();
		this.nom=nom;
		this.prenom=prenom;
	}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - -	
//	public boolean estVendeur(){
//		return this.role=="VENDEUR";
//	}
	
	public String creerEnchere(Date dateLimite, boolean estPubliee,int prixMinimum,int prixDeReserve, String identifiant, String description){
		//check si vendeur
		calendrier = new GregorianCalendar(2014, Calendar.JANUARY, 01);
		dateActuelle=calendrier.getTime();
//		
		if(dateLimite.compareTo(dateActuelle)<=0 && prixMinimum>0 && prixDeReserve>=prixMinimum){
					if(!listeEnchere.isEmpty()){
						this.enchere=new Enchere(listeEnchere,ETAT.CREEE,this,null,dateLimite,prixMinimum,prixDeReserve,identifiant,description);
						listeEnchere.add(enchere);
					}else{
						listeEnchere = new ArrayList<Enchere>();
						this.enchere=new Enchere(listeEnchere,ETAT.CREEE,this,null,dateLimite,prixMinimum,prixDeReserve,identifiant,description);
						listeEnchere.add(enchere);
						System.out.println("enchere cree");
					}
					
		}
		System.out.println("enchere non cree");
		
		return "";
	}
	
	public void publierEnchere(Enchere enchere){
		//check si vendeur
		if (enchere.getEtat()==ETAT.PUBLIEE){
			enchere.setEtat(ETAT.PUBLIEE);
		}
		
	}
	
	public void annulerEnchere(Enchere enchere){
		//checker si offre avec prix de reserve existe
		if (enchere.getEtat()==ETAT.PUBLIEE && enchere.PrixDeReserveNonAtteint()){
			enchere.setEtat(ETAT.ANNULEE);
		}
	}
	
	public void afficherEncheres(){
	
	}
	
//- - - - - - - - - - - - - - - - - - - - - - - - - - - -	
//	public boolean estAcheteur(){
//		return this.role=="ACHETEUR";
//	}
	
	public void emettreOffre(double prix, Enchere enchere){
		//check si acheteur et enchere publiée
		if(this!=enchere.getCreateur() && enchere.getEtat()==ETAT.PUBLIEE){
			if(!listeEnchere.isEmpty()){
				Offre offre = new Offre(this, prix, enchere,listeOffre);
				listeOffre.add(offre);
			}else{
				listeOffre = new ArrayList<Offre>();
				Offre offre = new Offre(this, prix, enchere,listeOffre);
				listeOffre.add(offre);
			}		
		}
	}
	public void listeOffre(){
		Iterator<Offre> it = listeOffre.iterator();
		 while(it.hasNext()){
			 System.out.println(it.next());
//		 	return it.next();
		 }
//		 return null;
	}
	public double rechercheOffre(){
		double prixLePlusEleve=listeOffre.get(0).getPrix();
		for(int i = 1 ; i < listeOffre.size(); i++){
			if(listeOffre.get(i).getPrix()>listeOffre.get(i-1).getPrix()){
				prixLePlusEleve=listeOffre.get(i).getPrix();
			}
		}
		return prixLePlusEleve;
	}
	public boolean existeEnchere(){
		return false;
	}
	public boolean existeOffre(){
		return false;
	}
	public String creerAlerte(){
		//checker si acheteur
		return "";
	}
	
//	public String desactiverTouteAlerte(){
//		//checker si offre avec prix de reserve existe
//		return "";
//	}
	
	public String desactiverAlerte(int i){
		switch(i){
		case 1:
			//supp alerte sur prix de reserve
			break;
		case 2:
			//supp alerte sur annulerEnchere
			break;
		case 3:
			//supp alerte sur offre supérieure concurrente
			break;
		default:
			//supprimer toutes les alertes
			break;
		}
		return "";
	}
	
//	public String supprimerEnchere(){
//		return "";
//	}
	
	public enum ROLE {
		ACHETEUR, VENDEUR
	}

	public static Date date(){
		Date aujourdhui=new Date();
		
		return aujourdhui;
	}
	
	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", role=" + role + "]";
	}
	
	
}
