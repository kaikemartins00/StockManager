package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import entities.Product;
import management.Inventory;

public class Program {
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Map<Integer, Product> map = new HashMap<>();

		String path = "C:\\Users\\kaike\\Projetos\\StockManager\\stock.csv";

		Inventory inventory = new Inventory();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line;
			while ((line = br.readLine()) != null) {

				String[] fields = line.split(", ");
				String name = fields[0];
				int id = Integer.parseInt(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				double price = Double.parseDouble(fields[3]);

				Product product = new Product(name, id, quantity, price);
				map.put(id, product);
			}

		} catch (IOException e) {
			System.out.println("...");
		}

		System.out.println("ADD / UPDATE / REMOVE ");
		System.out.print("Enter the operation (A/U/R): ");
		char option = sc.next().charAt(0);

		if (option == 'A') {
			System.out.print("How many products do you want to add? ");
			int n = sc.nextInt();
			sc.nextLine();

			for (int i = 1; i <= n; i++) {

				System.out.print("Product name #" + i + ": ");
				String name = sc.nextLine();

				System.out.print("Product code #" + i + ": ");
				int id = sc.nextInt();

				System.out.print("Quantity #" + i + ": ");
				int quantity = sc.nextInt();

				System.out.print("Price #" + i + ": ");
				double price = sc.nextDouble();
				sc.nextLine();

				Product product = new Product(name, id, quantity, price);
				inventory.getList().add(product);
				map.put(id, product);

			}

			System.out.println("PRODUCT ADDED!");
		}

		if (option == 'U') {
			System.out.println("UPDATE QUANTITY(Q) OR PRICE(P).");
			System.out.print("Enter the operation (Q/P): ");
			char optionQP = sc.next().charAt(0);

			if (optionQP == 'P') {
				System.out.print("Product ID that will update the price? ");
				int id = sc.nextInt();

				Product product = map.get(id);

				if (product != null) {
					System.out.print("What is the new price? ");
					double newPrice = sc.nextDouble();

					product.updateprice(newPrice);
					System.out
							.println("Price of the product " + product.getName() + " updated to " + product.getPrice());
				} else {
					System.out.println("Product not found");
				}
			}

			if (optionQP == 'Q') {
				System.out.println("ADD(A) ou REMOVE(R) QUANTITY");
				System.out.print("Enter the operation (A/R): ");
				char optionU = sc.next().charAt(0);

				if (optionU == 'A') {
					System.out.println("Which product ID will be added? ");
					int id = sc.nextInt();

					Product product = map.get(id);

					if (product != null) {
						System.out.println("How much will you add? ");
						int amount = sc.nextInt();

						product.addQuantity(amount);
						System.out.println("Quantity of the product " + product.getName() + " updated to "
								+ product.getQuantity());
					} else {
						System.out.println("Product not found");
					}

				}
				if (optionU == 'R') {
					System.out.println("What is the product ID from which you will withdraw the quantity? ");
					int id = sc.nextInt();

					Product product = map.get(id);

					if (product != null) {
						System.out.println("How much will you withdraw? ");
						int amount = sc.nextInt();

						product.removeQuantity(amount);
						System.out.println("Quantity of the product " + product.getName() + " updated to "
								+ product.getQuantity());
					} else {
						System.out.println("Product not found");
					}
				}

			}
		}

		if (option == 'R') {
			System.out.println("Which product ID will you be removing? ");
			int id = sc.nextInt();

			Product product = map.get(id);

			if (product != null) {
				map.remove(id);
				System.out.println("PRODUCT REMOVED!");
			} else {
				System.out.println("Product not found");
			}
		}

		try (FileWriter arq = new FileWriter(path)) {
			for (Product p : map.values()) {
				arq.write(p.getName() + ", " + p.getId() + ", " + p.getQuantity() + ", " + p.getPrice() + "\n");
			}

		} catch (IOException e) {
			System.out.println("Error saving: " + e.getMessage());
		}

		sc.close();
	}
}
