package entities;

import java.util.Objects;

public class Product {
	
	private String name;
	private final Integer id;
	private Integer quantity;
	private Double price;
	

	public Product(String name, Integer id, Integer quantity, Double price) {
		this.name = name;
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void addQuantity(int amount) {
		this.quantity += amount;
	}
	
	public void removeQuantity(int amount) {
		
		if(getQuantity() >= amount) {
			this.quantity -= amount;
		}else {
			System.out.println("ESTOQUE INSUFICIENTE");
		}
	}
	
	public void updateprice(double newPrice) {
		this.price = newPrice;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
