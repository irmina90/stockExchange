package stockExchange.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUOTATION")
public class Quotation{
	@Id
	@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	@Column(name = "day", nullable = false, length = 8)
	private String day;
	@Column(name = "price", nullable = false, length = 10)
	private BigDecimal price;

	public Quotation() {

	}
	
	public Quotation(Long id, String name, String day, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.day = day;
		this.price = price;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


}
