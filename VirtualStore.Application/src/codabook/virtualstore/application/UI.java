package codabook.virtualstore.application;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import codabook.componentmodel.ComponentRegistry;
import codabook.virtualstore.base.LogisticService;
import codabook.virtualstore.base.DeliveryType;
import codabook.virtualstore.base.InventoryService;
import codabook.virtualstore.base.Product;
import codabook.virtualstore.base.ShoppingCartService;
/*************************************************************************************************
*
* COMPANION CODE FOR THE BOOK �Component Oriented Development & Assembly � CODA Using Java�
* 
* @author � Piram Manickam, Sangeetha S, Subrahmanya S V
* @see -  http://www.codabook.com
* 
* <br><br><b>CODE CONTRIBUTORS</b> � <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
* Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>

**************************************************************************************************/
public class UI {

	static Scanner scanner = new Scanner(System.in);
	private Store store;

	public void setStore(Store store) {
		this.store = store;
	}

	public void displayProducts() {

		Map<Product, Integer> products = store.getAllProductsStock();

		System.out.println("\n------------------");
		System.out.println(" AVAILABLE PRODUCTS");
		System.out.println("--------------------\n");
		System.out
				.println("----------------------------------------------------------------");
		System.out.println("No.\t\tITEM\t\t\tPRICE\t\tSTOCK");
		System.out
				.println("----------------------------------------------------------------\n");
		int productIndex = 0;
		for (Product product : products.keySet()) {
			productIndex++;
			System.out.printf("%1$d.\t\t%2$s\t\t$%3$,.2f\t\t%4$d\n",
					productIndex, product.getName(), product.getPrice(), products.get(product));
		}
	}

//	public void displayDeliveryTypes() {
//		List<DeliveryType> deliveryTypes = store.getDeliveryTypes();
//		System.out.println("\n------------------");
//		System.out.println(" AVAILABLE PRODUCTS");
//
//	}
	
	public Product selectItemToBuy() {

		List<Product> products = store.getAvailableProducts();

		System.out.println("\n---------");
		System.out.println(" CATALOG");
		System.out.println("---------\n");
		System.out.println("-------------------------------------------------");
		System.out.println("No.\t\tITEM\t\t\tPRICE");
		System.out
				.println("-------------------------------------------------\n");

		int productIndex = 0;

		for (Product product : products) {
			productIndex++;

			System.out.printf("%1$d.\t\t%2$s\t\t$%3$,.2f\n", productIndex,
					product.getName(), product.getPrice());
		}

		System.out.println("\nPlease select item no. to buy:");

		int selectedIndex = scanner.nextInt();

		if (selectedIndex > 0 && selectedIndex <= products.size()) {
			Product selectedProduct = products.get(selectedIndex - 1);
			return selectedProduct;
		}
		System.out.println("ERROR - Invalid product selection");
		return null;
	}

	public int selectQuantityToBuy(Product product) {
		System.out.println("How many \"" + product.getName()
				+ "\" do you want? ");
		int quantity = scanner.nextInt();
		return quantity;
	}

	public void purchaseSuccess(Product product, int quantity) {
		System.out.println("SUCCESS - " + quantity + " quantities of "
				+ product.getName() + " added to shopping cart!");

		displayShoppingCart();
	}

	public void purchaseFail(Product product, int stock) {
		System.out.println("ERROR - Insufficient Stock; only " + stock
				+ " quantities of " + product.getName() + " are available");
		displayShoppingCart();
	}

	public Product[] displayShoppingCart() {
		Map<Product, Integer> shoppedProducts = store.getShoppedProducts();
		if(shoppedProducts.isEmpty()) {
			System.out.println("No Item In Your Shopping Cart!");
			return null;
		}
		
		System.out.println("\n---------------------------------");
		System.out.println(" DISPLAYING ITEMS IN SHOPPING CART");
		System.out.println("-----------------------------------\n");

		System.out
				.println("--------------------------------------------------------------------------------");
		System.out
				.println("No.    ITEM                     UNIT-PRICE        QUANTITY      SUB-TOTAL");
		System.out
				.println("--------------------------------------------------------------------------------\n");

		int itemIndex = 0;
		double totalPrice = 0;
		Product[] products = new Product[shoppedProducts.size()];
		for (Product product : shoppedProducts.keySet()) {
			products[itemIndex] = product;
			itemIndex++;
			double unitPrice = product.getPrice();
			int quantity = shoppedProducts.get(product);
			double subTotal = unitPrice * quantity;
			totalPrice += subTotal;
			System.out.printf(
					"%1$2d%2$-5s%3$-25s$%4$,-17.2f%5$-14d$%6$,-8.2f\n",
					itemIndex, ".", product.getName(), product.getPrice(),
					quantity, subTotal);
		}
		System.out
				.println("                                                -----------------------------");
		System.out
				.printf("                                                 TOTAL PRICE:   $%1$,-8.2f\n",
						totalPrice);
		System.out
				.println("                                                -----------------------------\n");

		return products;

	}
	
	public Product selectItemToRemove() {

		Product[] products = displayShoppingCart();
		if(products==null) {
			return null;
		}
		System.out.println("\nPlease select item no. to remove:");

		int selectedIndex = scanner.nextInt();

		if (selectedIndex > 0 && selectedIndex <= products.length) {
			Product product = products[selectedIndex - 1];
			return product;
		}

		System.out.println("ERROR - Invalid item specification\n");
		return null;
	}

	public int selectQuantityToRemove(Product product) {
		System.out.println("How many " + product.getName()
				+ " would you like to remove?");

		Map<Product, Integer> shoppedProducts = store.getShoppedProducts();
		int currentQuantity = shoppedProducts.get(product);

		System.out.println("Currently Shopping Cart contains : "
				+ currentQuantity);
		int removalQty = scanner.nextInt();
		if (removalQty > 0 && removalQty <= currentQuantity) {
			return removalQty;
		} else {
			System.out.println("ERROR - Inappropriate quantity to remove");
			return 0;
		}
	}

	public DeliveryType selectDeliveryType() {

		List<DeliveryType> deliveryTypes = store.getDeliveryTypes();

		System.out.println("\n---------");
		System.out.println(" DELIVERY TYPE LIST");
		System.out.println("---------\n");
		System.out.println("-----------------------------------------------------------");
		System.out.println("No.\tNAME\t\t\t\tPRICE\tDAY\tREMARK");
		System.out
				.println("-----------------------------------------------------------\n");

		int deliveryIndex = 0;

		for (DeliveryType deliveryType : deliveryTypes) {
			deliveryIndex++;

			System.out.printf("%2d.\t%-30s\t$%3.2f\t%2d\t%s\n", deliveryIndex,
					deliveryType.getName(), deliveryType.getFees(),
					deliveryType.getDeliveryDay(),deliveryType.getDescription());
		}

		System.out.println("\nPlease select delivery type no. :");

		int selectedIndex = scanner.nextInt();

		if (selectedIndex > 0 && selectedIndex <= deliveryTypes.size()) {
			DeliveryType selectedDeliveryType = deliveryTypes.get(selectedIndex - 1);
			return selectedDeliveryType;
		}
		System.out.println("ERROR - Invalid delivery type selection");
		return null;
	}
	
	public void removalSuccess(Product product, int quantity) {
		System.out.println("SUCCESS - " + quantity + " quantities of "
				+ product.getName() + " removed from shopping cart!");
		displayShoppingCart();
	}

	public void displayCheckOut(DeliveryType dt) {
		double totalPrice = store.getShoppingCartPrice()+dt.getFees();
		System.out.printf("Please Pay $%-8.2f(include $%.2f delivery fees)\n", totalPrice,dt.getFees());
		System.out.println("Thank you for your patronage! Please visit again!");
	}
	
	public int mainMenu() {
		System.out.println("\n-------------------");
		System.out.println(" WELCOME TO eSTORE!");
		System.out.println("-------------------\n");

		System.out.println("1. View Inventory");
		System.out.println("2. Add item to shopping cart");
		System.out.println("3. Remove item from shopping cart");
		System.out.println("4. Check Out (with delivery service)");
		System.out.println("5. Exit");

		System.out.println("\nChoose an option:");
		return scanner.nextInt();
	}

	public static void main(String[] args) {

		UI ui = new UI();
		InventoryService inventory = (InventoryService) ComponentRegistry
				.fetchComponent(InventoryService.class);
		ShoppingCartService shoppingCart = (ShoppingCartService) ComponentRegistry
				.fetchComponent(ShoppingCartService.class);
		LogisticService logistic = (LogisticService) ComponentRegistry
				.fetchComponent(LogisticService.class);
		Store store = new Store(ui, inventory, shoppingCart, logistic);
		ui.setStore(store);

		int userChoice = ui.mainMenu();

		while (userChoice > 0 && userChoice <= 4) {

			switch (userChoice) {

			case 1:
				ui.displayProducts();
				break;

			case 2:
				Product selectedProduct = ui.selectItemToBuy();
				if (selectedProduct != null) {
					int quantity = ui.selectQuantityToBuy(selectedProduct);
					store.addItemToCart(selectedProduct, quantity);
				}

				break;

			case 3:
				Product product = ui.selectItemToRemove();
				if (product == null)
					break;
				int quantity = ui.selectQuantityToRemove(product);
				store.removeItemsFromCart(product, quantity);
				ui.displayShoppingCart();
				break;

			case 4:
				Map<Product, Integer> shoppedProducts = store.getShoppedProducts();
				if(shoppedProducts.isEmpty()) {
					System.out.println("No Item In Your Shopping Cart. Please add some item(s) before select delivery service.");
					break;
				}
				DeliveryType deliveryType = ui.selectDeliveryType();
				if(deliveryType==null)
					break;
				ui.displayCheckOut(deliveryType);
				store.checkOut();

			default:
				break;
			}

			userChoice = ui.mainMenu();

		}

	}

}
