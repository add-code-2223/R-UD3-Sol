package books;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity

@PrimaryKeyJoinColumn(name="bookId")
public class Cuento extends Books{
	private int edadMin;

	public int getEdadMin() {
		return edadMin;
	}

	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}
	
	

}
