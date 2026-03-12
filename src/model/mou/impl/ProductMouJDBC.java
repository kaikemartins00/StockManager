package model.mou.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import entities.Product;
import model.mou.ProductMou;

public class ProductMouJDBC implements ProductMou{
	
	private Connection conn;
	
	public ProductMouJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Product obj) {
		try {
			PreparedStatement st = null;
			ResultSet rs = null;
			
        	st = conn.prepareStatement (
       			 "INSERT INTO products "
       			+ " (id, name, quantity, price) "
       			+ " VALUES "
       			+ " (?, ?, ?, ?)");
        	
        	st.setObject(1, obj.getId());
        	st.setObject(2, obj.getName());
        	st.setObject(3, obj.getQuantity()); 
        	st.setObject(4, obj.getPrice());
			
        	
        	int rowsAffected = st.executeUpdate();
        	
        	if (rowsAffected > 0) {
        		System.out.println("successfully created product");
        	}
        	
       } catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void updateAddQuantity(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE products "
				    + "SET quantity = quantity + ? "
					+ "WHERE id = ?");
			
			st.setInt(1, obj.getQuantity());
			st.setInt(2, obj.getId());
			st.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}
		
	}
	
	@Override
	public void updateRemoveQuantity(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE products "
					+ "SET quantity = quantity - ? "
					+ "WHERE id = ?");
			
			st.setInt(1, obj.getQuantity());
			st.setInt(2, obj.getId());
			st.executeUpdate();

		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}
		
	}
	
	@Override
	public void updatePrice(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE products "
					+ "SET price = ? "
					+ "WHERE id = ?");
			
			st.setDouble(1, obj.getPrice());
			st.setInt(2, obj.getId());
			st.executeUpdate();
			
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}
		
	}
	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM products WHERE id = ? ");
			
			st.setInt(1,  id);
			st.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}
	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
	        st.setInt(1, id);
	        rs = st.executeQuery();
	        
	        if (rs.next()) { 
	            Product obj = new Product();
	            obj.setId(rs.getInt("id"));
	            obj.setName(rs.getString("name"));
	            obj.setQuantity(rs.getInt("quantity"));
	            obj.setPrice(rs.getDouble("price"));
	            return obj; 
	        } else {
	        	return null;
	        }
	        
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Product> findAll() {
		return null;
	}

}
