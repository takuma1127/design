public class SingletonTest {

	public static void main(String args[]) {

		Renban item1 = Renban.getNumber();

		item1.name("a");

		item1.code(1);

		item1.print();

		Renban item2 = Renban.getNumber();

		item2.code(2);

		item2.name("b");

		item2.print();



	}

}



class Renban {

	private static Renban singleton = new Renban();

	private String code;

	private String name;



	public static Renban getNumber() {

		return singleton;



	}



	public void code(int num) {

		this.code = String.format("%04d", num);

	}



	public void name(String name) {

		this.name = name;

	}



	public void print() {

		System.out.println("+-------------------+");

		System.out.println("製品番号：" + code);

		System.out.println("製品名：" + name);

		System.out.println("+-------------------+");

	}



}