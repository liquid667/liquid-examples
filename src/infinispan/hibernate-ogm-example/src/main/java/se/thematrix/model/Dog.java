package se.thematrix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries( { @NamedQuery(name = "Dog.findDogById", query = "select d "
	+ "from Dog d where d.id= :id") })
public class Dog {
	private Long id;
	private String name;
	private Breed breed;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dog")
	@TableGenerator(name = "dog", table = "sequences", pkColumnName = "key", pkColumnValue = "dog", valueColumnName = "seed")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + "]";
	}
}