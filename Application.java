import java.util.Arrays;

public class Application {

	public static void main(String[] args) {
		//consumers ---- stores
		
		CakeStore Bucuria = new CakeStore();
		CakeStore Franzeluta = new CakeStore();
		
		System.out.println(" START (FACTORY): " +  Arrays.toString( CakeFactory.cakes ));
		
		//supplier ----- factory
		Market piata_centrala = new Market (Bucuria, 0);
			   piata_centrala.start();
		Market piata_tiraspol = new Market (Franzeluta, 1);
			   piata_tiraspol.start();
		
//		
//		System.out.println("\t= == === ==== ===== ==== === == =");
//		System.out.println( Arrays.toString( Bucuria.cakes ));
//		System.out.println( Arrays.toString(Franzeluta.cakes ));

	}

}
////////////////////////////////////////////
class Market extends Thread{
	CakeStore store;
	int portion;
	//constructor
	public Market(CakeStore store, int portion){
		this.store = store;
		this.portion = portion;
	}

	@Override
	public void run() {
		Cake[] production_1 = CakeFactory.offerCakes(portion);
		store.buyCakes( production_1 );
//		Cake[] production_2 = CakeFactory.offerCakes(1);
//		store.buyCakes( production_2 );
	}
	
}

class Cake{
	
}

class CakeFactory{
	static Cake[] cakes = {new Cake(), new Cake(), new Cake() };
		static Cake[] offerCakes(int portion){
			Cake [] production = Arrays.copyOfRange(cakes, portion*2 , portion*2 + 2);
			
			int to = portion*2 + 2 >= cakes.length ? cakes.length : portion*2 + 2;
			
			Arrays.fill(cakes, portion*2 , to , null);
			System.out.println(" OFFERS (FACTORY): " + Arrays.toString( production ));
			System.out.println(" REMAIN(FACTORY): " + Arrays.toString(cakes));
			return production;
		}
}

class CakeStore extends Thread{
	Cake[] cakes = new Cake[10];
		void buyCakes(Cake[] cakes){
			System.out.println( " BUY (STORE): " + Arrays.toString( cakes));
			this.cakes = cakes;
		}
}






