package weekopdracht2;
import java.util.Scanner;

public class Kermis {
	
	static void kassaOmzet() {
		Centralekassa kassa = new Centralekassa();
		System.out.println("Totale omzet van de kermiskassa bedraagt " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); 
	}
	
	void kaartVerkoop() {
		Centralekassa kassa = new Centralekassa(); 
		System.out.println("Totale verkochten kermiskaarten bedraagt " + kassa.getTotaalverkochtKaarten() );
	}
	
	void intro() { 
		Bezoeker bezoeker = new Bezoeker(); 
		bezoeker.setBezoekerNaam(); 
		System.out.println("Aha dat is beter, welkom op de kermis " + bezoeker.getBezoekerNaam() + "!" ); 
	}

	void attractieKeuze() { 
		String genomenKeuzeAttractie;
		boolean again = true;

			do {
				Scanner sckeuzeAttractie = new Scanner(System.in);
				System.out.printf("%nKies uit onderstaande attracties een attractie die je wilt bezoeken: \r\n" + 
				"[1] voor botsauto's, \r\n" + 
				"[2] voor spin, \r\n" + 
				"[3] voor spiegelpaleis, \r\n" + 
				"[4] voor spookhuis, \r\n" + 
				"[5] voor hawaii, \r\n" + 
				"[6] voor ladderklimmen \r\n\r\n" +
				"Of kies voor onderstaande opties: \r\n" + 
				"[0] om de kermis te verlaten... \r\n" + 
				"[o] voor de totale omzet \r\n" + 
				"[k] voor het totale aantal verkochte kaartjes \r\n" );
				genomenKeuzeAttractie = sckeuzeAttractie.nextLine().toLowerCase();
				System.out.println("Uw keuze was: " + genomenKeuzeAttractie);
				
				if ( genomenKeuzeAttractie.equals("1") ) {
					Attractie attractie = new Botsauto();
				
				} else if ( genomenKeuzeAttractie.equals("2") ) {
					RisicoRijkeAttractie risicoRijkeAttractie = new Spin();
					Centralekassa kassa= new Centralekassa();
					
				} else if ( genomenKeuzeAttractie.equals("3") ) {
					Attractie attractie = new Spiegelpaleis();
					
				} else if ( genomenKeuzeAttractie.equals("4") ) {
					Attractie attractie = new Spookhuis();
					
				} else if ( genomenKeuzeAttractie.equals("5") ) {
					RisicoRijkeAttractie risicoRijkeAttractie = new Hawaii();
						
				} else if ( genomenKeuzeAttractie.equals("6") ) {		
					Attractie attractie = new Ladderklimmen();
					
				} else if ( genomenKeuzeAttractie.equals("0") ) {
					System.out.println("U verlaat de kermis, bedankt voor uw bezoek en hopelijk tot ziens!");
					again = false; // exit the program 
					
				} else if ( genomenKeuzeAttractie.equals("o") ) {
					Centralekassa kassa = new Centralekassa();
					System.out.println("De totale omzet van de kermis is op dit moment: " + kassa.getTotaleOmzetKermis() + " euro." );
					
				} else if ( genomenKeuzeAttractie.equals("k") ) {
					kaartVerkoop();
					
				} else {
					System.out.println("De opgegeven waarde is ongeldig, het dient een getal te zijn van 1 tot en met 6. Probeer het gerust opnieuw ofsluit af met 0: ");
					}
		} while ( again == true );
		}

	//MAIN
	public static void main(String[] args) { 
		Kermis kermis = new Kermis();
		kermis.intro();
		kermis.attractieKeuze();
		}
}

abstract class Attractie {
	private String naam;
	protected double prijs; 
	private double oppervlakte;

	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private static double omzet; 
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	void attractieDraaien() {
		System.out.println("De attractie " + naam + " wordt gedraaid.");
	}
}

class Botsauto extends Attractie {
	private final String naam = "Botsauto"; 
	private final double prijs = super.prijs + 2.50; 
	private double oppervlakte;
	
	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private static double omzet;

	Botsauto(){
		super();
		attractieDraaien();
	}
	
	public static int getTotaleTicketVerkoop() {
		return totaleTicketVerkoop;
	}
	public static void setTotaleTicketVerkoop() {
		Botsauto.totaleTicketVerkoop = Botsauto.totaleTicketVerkoop + losseTicket;
	}

	public double getPrijs() {
		return prijs;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getOmzet() { 
		return omzet;
	}
	public void setOmzet() {
		omzet = ( prijs*getTotaleTicketVerkoop() );
	}
	
	void attractieDraaien() { 
		System.out.println("De attractie " + getNaam() + " wordt gedraaid."); 
		System.out.println("De oude hoeveelheid tickets voor deze attractie was " + getTotaleTicketVerkoop() + " tickets."); //check
		losseTicket = 1;
		System.out.println("Er is voor deze attractie " + losseTicket + " ticket verkocht voor de prijs van " + getPrijs() + " euro.");
		setTotaleTicketVerkoop();
		setOmzet();
		System.out.println("In totaal is/zijn er voor de attractie " + getNaam() + " " + getTotaleTicketVerkoop() + " kaartje(s) verkocht"
				+ " en is er " + String.format("%.02f",getOmzet()) + " euro verdiend.");
		
		Centralekassa kassa = new Centralekassa();
		kassa.getTotaleOmzetKermis(); 
		kassa.getTotaalverkochtKaarten();
		System.out.println("Totale omzet van de kermiskassa was " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten was " + kassa.getTotaalverkochtKaarten() ); //check
		
		kassa.setTotaleOmzetKermis( (kassa.getTotaleOmzetKermis() +  ( ( prijs*losseTicket ) ) )); 
		kassa.setTotaalverkochtKaarten(  (kassa.getTotaalverkochtKaarten() + losseTicket) );
		System.out.println("Totale omzet van de kermiskassa is " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten is " + kassa.getTotaalverkochtKaarten() ); //check
	} 	
	
}

class Spin extends RisicoRijkeAttractie { 
	private String naam = "Spin"; 
	private double prijs = 2.25; //super.prijs + 2.25; 

	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private double omzet;
	
	static int draaiLimiet = 5;
	
	Spin(){
		super();
		attractieDraaien();
	}
	
	public static int getTotaleTicketVerkoop() {
		return totaleTicketVerkoop;
	}
	public static void setTotaleTicketVerkoop() {
		Spin.totaleTicketVerkoop = Spin.totaleTicketVerkoop + losseTicket;
	}

	public double getPrijs() {
		return prijs;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getOmzet() { 
		return omzet;
	}
	public void setOmzet() {
		omzet = ( prijs*getTotaleTicketVerkoop() );
	}
	
	void attractieDraaien() { 
		opstellingsKeuring();
		draaiLimiet--;
//		System.out.println("Draai limiet is " + draaiLimiet); //check
		if (draaiLimiet <= 0) {
			draaiLimiet();
		}
		System.out.println("De attractie " + getNaam() + " wordt gedraaid."); 
		System.out.println("De oude hoeveelheid tickets voor deze attractie was " + getTotaleTicketVerkoop() + " tickets."); //check
		losseTicket = 1;
		System.out.println("Er is voor deze attractie " + losseTicket + " ticket verkocht voor de prijs van " + getPrijs() + " euro.");
		setTotaleTicketVerkoop();
		setOmzet();
		System.out.println("In totaal is/zijn er voor de attractie " + getNaam() + " " + getTotaleTicketVerkoop() + " kaartje(s) verkocht"
				+ " en is er " + String.format("%.02f",getOmzet()) + " euro verdiend.");
		
		Centralekassa kassa = new Centralekassa();
		kassa.getTotaleOmzetKermis(); 
		kassa.getTotaalverkochtKaarten();
		System.out.println("Totale omzet van de kermiskassa was " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten was " + kassa.getTotaalverkochtKaarten() ); //check
		
		kassa.setTotaleOmzetKermis( (kassa.getTotaleOmzetKermis() +  ( ( prijs*losseTicket ) ) )); 
		kassa.setTotaalverkochtKaarten(  (kassa.getTotaalverkochtKaarten() + losseTicket) );
		System.out.println("Totale omzet van de kermiskassa is " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten is " + kassa.getTotaalverkochtKaarten() ); //check
	} 	
	
	void draaiLimiet() {
		onderhoudskeuring();
		draaiLimiet += 5;	
		} 

@Override
void opstellingsKeuring() {
	System.out.println("Attractie is onderzocht volgens de opstellingskeuring. De attractie kan worden gebrukt.");
} 
	
}

class Spiegelpaleis extends Attractie { 
	private String naam = "Spiegelpaleis"; 
	private double prijs = super.prijs + 2.75;
	
	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private double omzet;

	Spiegelpaleis(){
		super();
		attractieDraaien();
	}
	
	public static int getTotaleTicketVerkoop() {
		return totaleTicketVerkoop;
	}
	public static void setTotaleTicketVerkoop() {
		Spiegelpaleis.totaleTicketVerkoop = Spiegelpaleis.totaleTicketVerkoop + losseTicket;
	}

	public double getPrijs() {
		return prijs;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getOmzet() { 
		return omzet;
	}
	public void setOmzet() {
		omzet = ( prijs*getTotaleTicketVerkoop() );
	}
	
	void attractieDraaien() { 
		System.out.println("De attractie " + getNaam() + " wordt gedraaid."); 
		System.out.println("De oude hoeveelheid tickets voor deze attractie was " + getTotaleTicketVerkoop() + " tickets."); //check
		losseTicket = 1;
		System.out.println("Er is voor deze attractie " + losseTicket + " ticket verkocht voor de prijs van " + getPrijs() + " euro.");
		setTotaleTicketVerkoop();
		setOmzet();
		System.out.println("In totaal is/zijn er voor de attractie " + getNaam() + " " + getTotaleTicketVerkoop() + " kaartje(s) verkocht"
				+ " en is er " + String.format("%.02f",getOmzet()) + " euro verdiend.");
		
		Centralekassa kassa = new Centralekassa();
		kassa.getTotaleOmzetKermis(); 
		kassa.getTotaalverkochtKaarten();
		System.out.println("Totale omzet van de kermiskassa was " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten was " + kassa.getTotaalverkochtKaarten() ); //check
		
		kassa.setTotaleOmzetKermis( (kassa.getTotaleOmzetKermis() +  ( ( prijs*losseTicket ) ) )); 
		kassa.setTotaalverkochtKaarten(  (kassa.getTotaalverkochtKaarten() + losseTicket) );
		System.out.println("Totale omzet van de kermiskassa is " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten is " + kassa.getTotaalverkochtKaarten() ); //check
	} 	
	
}
	
class Spookhuis extends Attractie{ 
	private String naam = "Spookhuis"; 
	private double prijs = super.prijs + 3.20; 	
	
	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private double omzet;

	Spookhuis(){
		super();
		attractieDraaien();
	}
	
	public static int getTotaleTicketVerkoop() {
		return totaleTicketVerkoop;
	}
	public static void setTotaleTicketVerkoop() {
		Spookhuis.totaleTicketVerkoop = Spookhuis.totaleTicketVerkoop + losseTicket;
	}

	public double getPrijs() {
		return prijs;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getOmzet() { 
		return omzet;
	}
	public void setOmzet() {
		omzet = ( prijs*getTotaleTicketVerkoop() );
	}
	
	void attractieDraaien() { 
		System.out.println("De attractie " + getNaam() + " wordt gedraaid."); 
		System.out.println("De oude hoeveelheid tickets voor deze attractie was " + getTotaleTicketVerkoop() + " tickets."); //check
		losseTicket = 1;
		System.out.println("Er is voor deze attractie " + losseTicket + " ticket verkocht voor de prijs van " + getPrijs() + " euro.");
		setTotaleTicketVerkoop();
		setOmzet();
		System.out.println("In totaal is/zijn er voor de attractie " + getNaam() + " " + getTotaleTicketVerkoop() + " kaartje(s) verkocht"
				+ " en is er " + String.format("%.02f",getOmzet()) + " euro verdiend.");
		
		Centralekassa kassa = new Centralekassa();
		kassa.getTotaleOmzetKermis(); 
		kassa.getTotaalverkochtKaarten();
		System.out.println("Totale omzet van de kermiskassa was " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten was " + kassa.getTotaalverkochtKaarten() ); //check
		
		kassa.setTotaleOmzetKermis( (kassa.getTotaleOmzetKermis() +  ( ( prijs*losseTicket ) ) )); 
		kassa.setTotaalverkochtKaarten(  (kassa.getTotaalverkochtKaarten() + losseTicket) );
		System.out.println("Totale omzet van de kermiskassa is " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten is " + kassa.getTotaalverkochtKaarten() ); //check
	} 	
	
}
	
class Hawaii extends RisicoRijkeAttractie { 
	private String naam = "Hawaii"; 
	private double prijs = 2.90; 	//super.prijs + 2.90; 
	
	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private double omzet;
	
	static int draaiLimiet = 10;

	Hawaii(){
		super();
		attractieDraaien();
	}
	
	public static int getTotaleTicketVerkoop() {
		return totaleTicketVerkoop;
	}
	public static void setTotaleTicketVerkoop() {
		Hawaii.totaleTicketVerkoop = Hawaii.totaleTicketVerkoop + losseTicket;
	}

	public double getPrijs() {
		return prijs;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getOmzet() { 
		return omzet;
	}
	public void setOmzet() {
		omzet = ( prijs*getTotaleTicketVerkoop() );
	}
	
	void attractieDraaien() { 
		opstellingsKeuring();
		draaiLimiet--;
//		System.out.println("Draai limiet is " + draaiLimiet); //check
		if (draaiLimiet <= 0) {
			draaiLimiet();
		}
		
		System.out.println("De attractie " + getNaam() + " wordt gedraaid."); 
		System.out.println("De oude hoeveelheid tickets voor deze attractie was " + getTotaleTicketVerkoop() + " tickets."); //check
		losseTicket = 1;
		System.out.println("Er is voor deze attractie " + losseTicket + " ticket verkocht voor de prijs van " + getPrijs() + " euro.");
		setTotaleTicketVerkoop();
		setOmzet();
		System.out.println("In totaal is/zijn er voor de attractie " + getNaam() + " " + getTotaleTicketVerkoop() + " kaartje(s) verkocht"
				+ " en is er " + String.format("%.02f",getOmzet()) + " euro verdiend.");
		
		Centralekassa kassa = new Centralekassa();
		kassa.getTotaleOmzetKermis(); 
		kassa.getTotaalverkochtKaarten();
		System.out.println("Totale omzet van de kermiskassa was " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten was " + kassa.getTotaalverkochtKaarten() ); //check
		
		kassa.setTotaleOmzetKermis( (kassa.getTotaleOmzetKermis() +  ( ( prijs*losseTicket ) ) )); 
		kassa.setTotaalverkochtKaarten(  (kassa.getTotaalverkochtKaarten() + losseTicket) );
		System.out.println("Totale omzet van de kermiskassa is " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten is " + kassa.getTotaalverkochtKaarten() ); //check
	}
	
	void draaiLimiet() {
			onderhoudskeuring();
			draaiLimiet += 10;	
			} 
	
	@Override
	void opstellingsKeuring() {
		System.out.println("Attractie is onderzocht volgens de opstellingskeuring. De attractie kan worden gebrukt.");
	} 	
	
}

class Ladderklimmen extends Attractie implements GokAttractie { 
	private String naam = "Ladderklimmen"; 
	private double prijs = super.prijs + 5;
	
	private static int losseTicket;
	private static int totaleTicketVerkoop; 
	private double omzet;
	
	Ladderklimmen(){
		super();
		attractieDraaien();
	}
	
	public static int getTotaleTicketVerkoop() {
		return totaleTicketVerkoop;
	}
	public static void setTotaleTicketVerkoop() {
		Ladderklimmen.totaleTicketVerkoop = Ladderklimmen.totaleTicketVerkoop + losseTicket;
	}

	public double getPrijs() {
		return prijs;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public double getOmzet() { 
		return omzet;
	}
	public void setOmzet() {
		omzet = ( (prijs*0.70) * getTotaleTicketVerkoop() );
		kansSpelBelastingBetalen();
	}
	
	void attractieDraaien() { 
		System.out.println("De attractie " + getNaam() + " wordt gedraaid."); 
		System.out.println("De oude hoeveelheid tickets voor deze attractie was " + getTotaleTicketVerkoop() + " tickets."); //check
		losseTicket = 1;
		System.out.println("Er is voor deze attractie " + losseTicket + " ticket verkocht voor de prijs van " + getPrijs() + " euro.");
		setTotaleTicketVerkoop();
		setOmzet();
		System.out.println("In totaal is/zijn er voor de attractie " + getNaam() + " " + getTotaleTicketVerkoop() + " kaartje(s) verkocht"
				+ " en is er " + String.format("%.02f",getOmzet()) + " euro verdiend.");
		
		Centralekassa kassa = new Centralekassa();
		kassa.getTotaleOmzetKermis(); 
		kassa.getTotaalverkochtKaarten();
		System.out.println("Totale omzet van de kermiskassa was " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten was " + kassa.getTotaalverkochtKaarten() ); //check
		
		kassa.setTotaleOmzetKermis( kassa.getTotaleOmzetKermis() +  ((prijs*losseTicket) *0.70) );				 
		kassa.setTotaalverkochtKaarten(  (kassa.getTotaalverkochtKaarten() + losseTicket) );
		System.out.println("Totale omzet van de kermiskassa is " + String.format("%.02f", kassa.getTotaleOmzetKermis()) ); //check
		System.out.println("Totale verkochten kermiskaarten is " + kassa.getTotaalverkochtKaarten() ); //check		
	}

	@Override
	public double kansSpelBelastingBetalen() {
		double reserveren = (((getOmzet()/70)*100) * 0.30 );
		System.out.println("Er is nu (30%): " + reserveren + " euro gereserveerd van de omzet van deze attractie voor de kansspelbelasting."); 
		System.out.println("De nieuwe omzet is nu: " + String.format("%.02f", getOmzet()) + " euro.");
		return reserveren;
	} 	
	
}

class Centralekassa { 
	private static double totaleOmzetKermis;
	private static int totaalverkochtKaarten; 
	
	public double getTotaleOmzetKermis() { return totaleOmzetKermis; } 
	public void setTotaleOmzetKermis(double totaleOmzetKermis) {
		Centralekassa.totaleOmzetKermis = totaleOmzetKermis;
	}
	public int getTotaalverkochtKaarten() {
		return totaalverkochtKaarten;
	}
	public void setTotaalverkochtKaarten(int totaalverkochtKaarten) {
		Centralekassa.totaalverkochtKaarten = totaalverkochtKaarten;
	}
}

class Bezoeker {
	private String bezoekerNaam;
	
	public String getBezoekerNaam() {
		return bezoekerNaam;
	}
	
	public void setBezoekerNaam() {
		Scanner scBezoekerNaam = new Scanner(System.in);
		System.out.println("Welkom bezoeker op de kermis!! Laten we het iets informeler aanpakken, wat is je voornaam?: ");
		this.bezoekerNaam = scBezoekerNaam.nextLine();
	}
}

abstract class RisicoRijkeAttractie {
int draaiLimiet; 

void draaiLimiet() {
	if (draaiLimiet == 0) {
		onderhoudskeuring();
		draaiLimiet += 5;	
		} else {System.out.println("Er is niets aan de hand de attractie kan gewoon gebruikt worden.");
		}
	}

void onderhoudskeuring() {
	System.out.println("Er wordt onderhoud wordt gepleegd...");
}
 
		
abstract void opstellingsKeuring();
}

interface GokAttractie {
	double kansSpelBelastingBetalen();
	
}
