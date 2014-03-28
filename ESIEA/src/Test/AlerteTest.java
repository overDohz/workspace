package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CAO_AlertesSurEnchere.Alerte;
import CAO_AlertesSurEnchere.Alerte.NOTIFICATION;
import CAO_AlertesSurEnchere.AlerteObserver;
import CAO_Encheres.Enchere;
import CAO_Encheres.Enchere.ETAT;
import CAO_UtilisateurDuSysteme.Utilisateur;
import CAO_UtilisateurDuSysteme.Utilisateur.ROLE;

public class AlerteTest {
	private Utilisateur AAch,AVen,BAch,BVen,CAch;
	private Date d;
	private GregorianCalendar calendar;
	private Date d2;
	private GregorianCalendar calendar2;
//	private static ArrayList<Enchere> listeEnchere;
	private Enchere enchere;
	private Alerte alerte,alertePrix,alerteAnnulee,alerteoOffreSup;
	private HashSet<Alerte> listeAlerte;
	private AlerteObserver alerteObserver;
	private ArrayList<String> listeNotification;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		AAch = new Utilisateur("login1",ROLE.ACHETEUR,"nom1","prenom1");
		AVen = new Utilisateur("login1",ROLE.VENDEUR,"nom2","prenom2");
		BAch = new Utilisateur("login2",ROLE.ACHETEUR,"nom1","prenom1");
		BVen = new Utilisateur("login2",ROLE.VENDEUR,"nom2","prenom2");
		CAch = new Utilisateur("login3",ROLE.ACHETEUR,"nom1","prenom1");
		calendar = new GregorianCalendar(2014, Calendar.MARCH, 26);
		d=calendar.getTime();
		calendar2 = new GregorianCalendar(2013, Calendar.MARCH, 26);
		d2=calendar2.getTime();
//		enchere = new Enchere(Utilisateur.listeEnchere,ETAT.CREEE, AVen, d, 1,2, "id","desc");
		Utilisateur.listeEnchere=null;
		Utilisateur.listeOffre=null;
		alerteObserver=new AlerteObserver(listeAlerte);
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere,alerteObserver, listeNotification);
//		alerte=AAch.creerAlerte(Alerte.NOTIFICATION.ENCHEREANNULEE, enchere, listeAlerte);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void alerteAuto() {
		BAch.emettreOffre(1, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertTrue(AVen.listeNotification.isEmpty());
	}
	
	@Test
	public void alertePrixDeReserveAtteint() {
		BAch.creerAlerte(NOTIFICATION.PRIXDERESERVE, enchere, listeAlerte);
//		BAch.emettreOffre(2, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		CAch.emettreOffre(2, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertTrue(CAch.listeNotification.isEmpty());
	}
	
	@Test
	public void alerteOffreSuperieure() {
		BAch.creerAlerte(NOTIFICATION.PRIXDERESERVE, enchere, listeAlerte);
		BAch.emettreOffre(2, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		CAch.emettreOffre(3, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertTrue(BAch.listeNotification.isEmpty());
	}
	
	@Test
	public void alerteEnchereAnnulee() {
		BAch.creerAlerte(NOTIFICATION.ENCHEREANNULEE, enchere, listeAlerte);
		AVen.annulerEnchere(enchere);
		assertTrue(BAch.listeNotification.isEmpty());
	}
	
	@Test
	public void alerteSuppression() {
		BAch.creerAlerte(NOTIFICATION.PRIXDERESERVE, enchere, listeAlerte);
		BAch.desactiverAlerte(1);
	}
	
	@Test
	public void alerteToutSupprimer() {
		BAch.creerAlerte(NOTIFICATION.ENCHEREANNULEE, enchere, listeAlerte);
		BAch.creerAlerte(NOTIFICATION.OFFRESUP, enchere, listeAlerte);
		BAch.creerAlerte(NOTIFICATION.PRIXDERESERVE, enchere, listeAlerte);
		BAch.desactiverAlerte(0);
	}

}
