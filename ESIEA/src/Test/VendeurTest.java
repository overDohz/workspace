package Test;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CAO_Encheres.Enchere;
import CAO_Encheres.Enchere.ETAT;
import CAO_UtilisateurDuSysteme.Utilisateur;
import CAO_UtilisateurDuSysteme.Utilisateur.ROLE;

public class VendeurTest {
	
	private Utilisateur AAch,AVen,BAch,BVen,CAch;
	private Date d;
	private GregorianCalendar calendar;
	private Date d2;
	private GregorianCalendar calendar2;
//	private static ArrayList<Enchere> listeEnchere;
	private Enchere enchere;
//	private ArrayList<Offre> listeOffre;
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
		enchere = new Enchere(Utilisateur.listeEnchere,ETAT.CREEE, AVen, d, 1,2, "id","desc");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreationEnchere() {
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere);
		assertFalse(AVen.listeEnchere().isEmpty() || (AVen.listeEnchere()==null));
		Utilisateur.listeEnchere=null;
	}
	
	@Test
	public void testCreationEnchereMauvaisPrix() {
		Utilisateur.listeEnchere=null;
		AVen.creerEnchere(d, ETAT.CREEE,10,2, "id", "desc",Utilisateur.listeEnchere);
		assertTrue(Utilisateur.listeEnchere==null);
	}
	
	@Test
	public void testCreationEnchereMauvaiseDate() {
		Utilisateur.listeEnchere=null;
		AVen.creerEnchere(d2, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere);
		assertTrue(Utilisateur.listeEnchere==null);
	}
	@Test
	public void testPublicationEnchere(){
		Utilisateur.listeEnchere=null;
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere);
		AVen.publierEnchere(Utilisateur.listeEnchere.get(0));
		assertEquals(ETAT.PUBLIEE, Utilisateur.listeEnchere.get(0).getEtat());
	}
	
	@Test
	public void testPublicationEnchereMauvaisUtilisateur(){
		Utilisateur.listeEnchere=null;
		AVen.creerEnchere(d, ETAT.CREEE,1,2, "id", "desc",Utilisateur.listeEnchere);
		BVen.publierEnchere(Utilisateur.listeEnchere.get(0));
		assertNotEquals(ETAT.PUBLIEE, Utilisateur.listeEnchere.get(0).getEtat());
	}
	
	@Test
	public void testAnnulationEnchere(){
		AVen.publierEnchere(enchere);
		AVen.annulerEnchere(enchere);
		assertEquals(ETAT.ANNULEE, enchere.getEtat());
	}
	
	@Test
	public void testAnnulationEnchereMauvaisETAT(){
		AVen.annulerEnchere(enchere);
		assertNotEquals(ETAT.ANNULEE,enchere.getEtat());
	}
	@Test
	public void testAnnulationEnchereMauvaisUtilisateur(){
		BAch.annulerEnchere(enchere);
		assertNotEquals(ETAT.ANNULEE,enchere.getEtat());
	}

}
