import java.util.ArrayList;

import java.util.List;



// 本

class Book {

	private String name; // 名称

	private int price; // 価格



	public Book(String name, int price) { // コンストラクタ

		this.name = name;

		this.price = price;

	}



	public String getName() { // 名称を取得

		return name;

	}



	public int getPrice() { // 価格を取得

		return price;

	}

}



// 本の容れ物

class BookList {

	private Book[] list; // 本を格納する配列

	private int numberOfStock; // 現在の在庫数



	public BookList(int number) { // コンストラクタ

		list = new Book[number]; // 配列の大きさを確定

		numberOfStock = 0;

	}



	public void add(Book book) {

		list[numberOfStock] = book; // 格納

		numberOfStock += 1; // 在庫数を1 つ増加

	}



	public Book getBook(int number) { // 指定番号の本ト取出し

		return list[number];

	}



	public int getNumberOfStock() { // 現在の在庫数を取得

		return numberOfStock;

	}

}



interface Iterator {

	public void first(); // 取り出し位置を最初の要素へ変える



	public void next(); // 取り出し位置を次の要素へ変える



	public boolean isDone(); // 取り出し位置が最後を超えたか？



	public Object getItem(); // 現在の取り出し位置から取り出す

}



class BookListIterator implements Iterator {

	private BookListAggregate aggregate;

	private int current;



	public BookListIterator(BookListAggregate aggregate) {

		this.aggregate = aggregate;

	}



	@Override

	public void first() {

		current = 0;

	}



	@Override

	public void next() {

		current += 1;

	}



	@Override

	public boolean isDone() {

		if (current >= aggregate.getNumberOfStock()) {

			return true;

		} else {

			return false;

		}

	}



	@Override

	public Object getItem() {

		return aggregate.getAt(current);

	}

}



interface Aggregate {

	public Iterator createIterator();

}



class BookListAggregate implements Aggregate {

	//private Game[] list = new Game[20];

	private List<Book> Book = new ArrayList<>();

	private int numberOfStock;



	@Override

	public Iterator createIterator() {

		return new BookListIterator(this);

	}



	public void add(Book book) {

		Book.add(book);

		numberOfStock += 1;

	}



	public Object getAt(int number) {

		return Book.get(number);

	}



	public int getNumberOfStock() {

		return numberOfStock;

	}

}



public class IteratorSample1 {

	public static void main(String[] args) {

		BookListAggregate bookListAggregate = new BookListAggregate();

		Iterator iterator = bookListAggregate.createIterator();

		bookListAggregate.add(new Book("初心者C", 3700));

		bookListAggregate.add(new Book("Python実践", 7300));

		bookListAggregate.add(new Book("ネットワーク技術", 5400));

		bookListAggregate.add(new Book("麻雀の達人", 5200));

		iterator.first(); // まず探す場所を先頭位置にしてもらう

		while (!iterator.isDone()) { // まだある？ まだあるよ！

			Book book = (Book) iterator.getItem(); // はいどうぞ (と受取る)

			System.out.println(book.getName());

			iterator.next(); // 次を頂戴

		}

	}

}