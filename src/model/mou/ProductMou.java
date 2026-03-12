package model.mou;

import java.util.List;

import entities.Product;

public interface ProductMou {
	
	void insert(Product obj);
	void updateAddQuantity(Product obj);
	void updateRemoveQuantity(Product obj);
	void updatePrice(Product obj);
	void deleteById(Integer id);
	Product findById(Integer id);
	
	List<Product> findAll();
	

}
