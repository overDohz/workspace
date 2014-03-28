package Test;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CAO_AlertesSurEnchere.AlerteObserver;
import CAO_Encheres.Enchere;
import CAO_Encheres.Enchere.ETAT;
import CAO_UtilisateurDuSysteme.Utilisateur;
import CAO_UtilisateurDuSysteme.Utilisateur.ROLE;

public class AcheteurTest {
	
	private Utilisateur AAch,AVen,BAch,BVen,CAch;
	private Date d;
	private GregorianCalendar calendar;
	private Date d2;
	private GregorianCalendar calendar2;
//	private static ArrayList<Enchere> listeEnchere;
	private Enchere enchere;
//	private ArrayList<Offre> listeOffre;
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
		alerteObserver=null;
		listeNotification=null;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmissionsOffres() {
		Utilisateur.listeEnchere=null;
		Utilisateur.listeOffre=null;
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere, alerteObserver, listeNotification);
		AVen.publierEnchere(Utilisateur.listeEnchere.get(0));
		BAch.emettreOffre(1, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		CAch.emettreOffre(2, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertFalse(Utilisateur.listeOffre.isEmpty());
	}
	@Test
	public void testEmissionOffreMauvaisPrix() {
		Utilisateur.listeEnchere=null;
		Utilisateur.listeOffre=null;
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere,alerteObserver, listeNotification);
		AVen.publierEnchere(Utilisateur.listeEnchere.get(0));
		CAch.emettreOffre(1, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertTrue(Utilisateur.listeOffre==null);
	}
	
	@Test
	public void testEmissionOffreMauvaiseUtilisateur() {
		Utilisateur.listeEnchere=null;
		Utilisateur.listeOffre=null;
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere,alerteObserver, listeNotification);
		AVen.publierEnchere(Utilisateur.listeEnchere.get(0));
		AAch.emettreOffre(1, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertTrue(Utilisateur.listeOffre==null);
	}
	@Test
	public void testEmissionOffreNonPubliee() {
		Utilisateur.listeEnchere=null;
		Utilisateur.listeOffre=null;
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere,alerteObserver, listeNotification);
		BAch.emettreOffre(1, Utilisateur.listeEnchere.get(0), Utilisateur.listeOffre, alerteObserver);
		assertTrue(Utilisateur.listeOffre==null);
	}

}
