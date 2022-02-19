package jpaOlenchenko;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the AUDIENCE database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Audience.findAll", query = "SELECT a FROM Audience a")
})
public class Audience implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String area;

	private String floor;

	private String number;


	//bi-directional many-to-one association to Corps
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="IDCORPS")
	private Corps corps;
	
	public Audience() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Corps getCorps() {
		return this.corps;
	}

	public void setCorps(Corps corps) {
		this.corps = corps;
	}

}