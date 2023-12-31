package books;
// Generated 11 jun 2023 11:01:03 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Publishers generated by hbm2java
 */
@Entity
@Table(name = "publishers")
public class Publishers implements java.io.Serializable {

	private Integer publisherId;
	private String name;
	private Set<Books> bookses = new HashSet<Books>(0);

	@Override
	public String toString() {
		return "Publishers [publisherId=" + publisherId + ", name=" + name + "]";
	}

	public Publishers() {
	}

	public Publishers(String name) {
		this.name = name;
	}

	public Publishers(String name, Set<Books> bookses) {
		this.name = name;
		this.bookses = bookses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "publisher_id", unique = true, nullable = false)
	public Integer getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publishers")
	public Set<Books> getBookses() {
		return this.bookses;
	}

	public void setBookses(Set<Books> bookses) {
		this.bookses = bookses;
	}

}
