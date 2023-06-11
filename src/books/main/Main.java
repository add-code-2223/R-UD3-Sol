package books.main;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import book.util.SessionFactoryUtil;
import books.Books;
import books.Cuento;
import books.Publishers;

public class Main {
	private static Session session;
	
	public static void main(String[] args) {
		SessionFactory factory = SessionFactoryUtil.getSessionFactory();
		session = factory.openSession();
		
		
		ArrayList<Publishers> publishers =	getAllPublishers();
		mostrar(publishers);
		
		Publishers editor = getPublisherById(1);
		
		System.out.println(editor);
		
		Books book = new Books(editor, "Mi libro", "abc-fgh-ijk",  new Date(System.currentTimeMillis()), null);
		crear(book);
		ArrayList<Books> books= getAllBooksByPublisherName("Bruma");
		mostrar(books);
		
		
		Cuento c = new Cuento();
		c.setEdadMin(12);
		c.setTitle("El acceso a datos");
		c.setPublishers(editor);
		
		crear(c);
		
		mostrar(books);
		
		factory.close();
	}
	
	public static ArrayList<Publishers> getAllPublishers() {
	ArrayList<Publishers> publishers=	(ArrayList<Publishers>)session.createQuery("select p from Publishers p order by p.name").list();
	return publishers;
	}
	
	public static ArrayList<Books> getAllBooksByPublisherName(String publisherName) {
		
				
				Query q= session.createQuery("select b from Books b join b.publishers p where p.name = :publisherName"
				+ " order by b.title").setParameter("publisherName", publisherName);
				ArrayList<Books> books= (	ArrayList<Books>)	q.list();
				
		
		return books;
		}
	
	
	public static void crear(Books b) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Integer id = (Integer) session.save(b);
			tx.commit();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			if(tx!=null) {
				tx.rollback();
			}
			
		}
	}
	
	public static void crear(Cuento c) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Integer bookId = (Integer) session.save(c);
			tx.commit();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			if(tx!=null) {
				tx.rollback();
			}
			
		}
	}
	
	private static Publishers getPublisherById(int id) {
		Publishers p = session.get(Publishers.class, id);
		return p;
	}
	
	private static <E> void mostrar(ArrayList<E> entities) {
		for (E e : entities) {
			System.out.println(e);
		}
	}
	
	

}
