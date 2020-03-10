package de.suchwerk;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Product extends PanacheEntity {

	@Temporal(TemporalType.TIMESTAMP)
	public Date creationDate;
	public String description;
	public String detail;
	public String reference;
	public String currency;

	public BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "customer", unique = false, nullable = true)
	public Customer customer;

	public Product() {
	}
}
