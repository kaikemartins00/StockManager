package model.mou;

import db.DB;
import model.mou.impl.ProductMouJDBC;

public class FactoryMou {
	
	public static ProductMou createProductMou() {
		return new ProductMouJDBC(DB.getConnection());
	}
}
