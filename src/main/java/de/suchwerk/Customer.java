package de.suchwerk;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity {

	public String firstname;
	public String lastname;
	public String email;
	public String company;
	public String street;
	public String city;
	public String region;
	public String postcode;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer", unique = false, nullable = true)
	public Set<Product> products = new HashSet<>();

	public void setProducts(Set<Product> references) {
		this.products.clear();
		if (references == null || references.size() == 0) {
			return;
		} else {
			for (Product reference : references) {
				reference.customer = this;
				this.products.add(reference);
			}
		}

	}

	public void removeProducts(Product reference) {
		if (this.products != null) {
			this.products.remove(reference);
		}
	}

	public void addProduct(Product reference) {
		if (this.products == null) {
			this.products = new HashSet<>();
		}
		reference.customer = this;
		this.products.add(reference);
	}

}
