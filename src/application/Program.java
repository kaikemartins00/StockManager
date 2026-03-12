package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import db.DB;
import db.DbException;
import entities.Product;
import model.mou.FactoryMou;
import model.mou.ProductMou;

public class Program {
	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		ProductMou productMou = FactoryMou.createProductMou();

		try {

			System.out.println("ADD / UPDATE / REMOVE ");
			System.out.print("Enter the operation (A/U/R): ");
			char optionAUR = sc.next().charAt(0);

			switch (optionAUR) {
			case 'A':
				System.out.print("How many products do you want to add? ");
				int j = sc.nextInt();

				for (int i = 1; i <= j; i++) {
					System.out.print("Product id #" + i + ": ");
					int id = sc.nextInt();
					sc.nextLine();

					Product p = productMou.findById(id);
					if (p == null) {
						System.out.print("Product name #" + i + ": ");
						String name = sc.nextLine();

						System.out.print("Quantity #" + i + ": ");
						int quantity = sc.nextInt();

						System.out.print("Price #" + i + ": ");
						double price = sc.nextDouble();
						sc.nextLine();

						Product product = new Product(id, name, quantity, price);
						productMou.insert(product);

					} else {
						System.out.println("PRODUCT EXISTING");
					}
				}
				break;

			case 'U':
				System.out.println("UPDATE QUANTITY(Q) OR PRICE(P).");
				System.out.print("Enter the operation (Q/P): ");
				char optionQP = sc.next().charAt(0);

				switch (optionQP) {
				case 'P':
					System.out.print("How many products will you update the price of? ");
					int k = sc.nextInt();

					for (int i = 1; i <= k; i++) {

						System.out.print("What is the product ID #" + i + " that will update the price? ");
						int id = sc.nextInt();

						Product p = productMou.findById(id);
						if (p != null) {
							System.out.print("What is the new price of product " + i + " ? ");
							double newPrice = sc.nextDouble();

							Product product = new Product();
							product.setId(id);
							product.setPrice(newPrice);
							productMou.updatePrice(product);

							System.out.println("Price of product " + id + " updated!");
						} else {
							System.out.println("PRODUCT " + id + " DOES NOT EXIST");
						}
					}
					break;

				case 'Q':
					System.out.println("ADD(A) ou REMOVE(R) QUANTITY");
					System.out.print("Enter the operation (A/R): ");
					char optionARQ = sc.next().charAt(0);

					switch (optionARQ) {
					case 'A':
						System.out.print("How many products do you want to add quantity of? ");
						int n = sc.nextInt();

						for (int i = 1; i <= n; i++) {
							System.out.print("What is the product id #" + i + " that will add the quantity? ");
							int id = sc.nextInt();

							Product p = productMou.findById(id);
							if (p != null) {
								System.out.print("Quantity you will add? ");
								int addQuantity = sc.nextInt();

								Product product = new Product();
								product.setId(id);
								product.setQuantity(addQuantity);
								productMou.updateAddQuantity(product);

								System.out.println("Product quantity updated to " + id);
							} else {
								System.out.println("PRODUCT " + id + " DOES NOT EXIST");
							}
						}
						break;

					case 'R':
						System.out.print("How many products do you want to remove? ");
						int l = sc.nextInt();

						for (int i = 1; i <= l; i++) {
							System.out.print("What is the product id #" + i + " from which you will withdraw the quantity? ");
							int id = sc.nextInt();

							Product p = productMou.findById(id);
							if (p != null) {
								System.out.print("How much will you withdraw? ");
								int removeQuantity = sc.nextInt();

								Product product = new Product();
								product.setId(id);
								product.setQuantity(removeQuantity);
								productMou.updateRemoveQuantity(product);

								System.out.println("Product quantity updated to " + id);
							} else {
								System.out.println("PRODUCT " + id + " DOES NOT EXIST");
							}
						}
						break;
					}
					break;
					default:
						System.out.println("Invalid option");
					
				}
				break;
				

			case 'R':
				System.out.print("How many products do you want to remove? ");
				int m = sc.nextInt();
				sc.nextLine();

				for (int i = 1; i <= m; i++) {
					System.out.print("Product #" + i + " id: ");
					int id = sc.nextInt();

					Product p = productMou.findById(id);
					if (p != null) {
						productMou.deleteById(id);
						System.out.println("PRODUCT " + id + " REMOVED! ");
					} else {
						System.out.println("PRODUCT " + id + " DOES NOT EXIST");
					}

				}
				break;
				default:
					System.out.println("Invalid option");
			}
			
			
		} 
		catch(DbException e){
			System.out.println("ERROR! " + e.getMessage());
		}
		finally {
			sc.close();

			DB.closeConnection();
		}

	}

}
