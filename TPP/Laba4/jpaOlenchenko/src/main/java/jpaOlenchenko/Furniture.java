package jpaOlenchenko;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.CascadeType.REMOVE;



/**
 * Entity implementation class for Entity: Furniture
 *
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Furniture.findAll", query = "SELECT x FROM Furniture x")
})
public class Furniture implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private int price;
	private int amount_of_furniture;
	private int weight;
	
	@ManyToOne(targetEntity = jpaOlenchenko.Audience.class, cascade = REMOVE)
	private Audience audience;
	private static final long serialVersionUID = 1L;

	public Furniture() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}   
	public int getAmount_of_furniture() {
		return this.amount_of_furniture;
	}

	public void setAmount_of_furniture(int amount_of_furniture) {
		this.amount_of_furniture = amount_of_furniture;
	}   
	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}   
	public Audience getAudience() {
		return this.audience;
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}
   
}
