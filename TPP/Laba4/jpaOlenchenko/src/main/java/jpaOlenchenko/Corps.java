package jpaOlenchenko;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CORPS database table.
 * 
 */
@Entity
@Table(name="CORPS")
@NamedQuery(name="Corps.findAll", query="SELECT c FROM Corps c")
public class Corps implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String audience;

	private String corps;

	private String furniture;

	
	//bi-directional many-to-one association to Audience
	@OneToMany(mappedBy="corps")
	private List<Audience> audiences;

	public Corps() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAudience() {
		return this.audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getCorps() {
		return this.corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public String getFurniture() {
		return this.furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	public List<Audience> getAudiences() {
		return this.audiences;
	}

	public void setAudiences(List<Audience> audiences) {
		this.audiences = audiences;
	}

	public Audience addAudience(Audience audience) {
		getAudiences().add(audience);
		audience.setCorps(this);

		return audience;
	}

	public Audience removeAudience(Audience audience) {
		getAudiences().remove(audience);
		audience.setCorps(null);

		return audience;
	}

}