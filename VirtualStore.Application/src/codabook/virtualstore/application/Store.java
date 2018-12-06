package codabook.virtualstore.application;

import java.util.List;
import java.util.Map;

import codabook.virtualstore.base.InventoryService;
import codabook.virtualstore.base.Product;
import codabook.virtualstore.base.ShoppingCartService;
import codabook.virtualstore.base.LogisticService;
import codabook.virtualstore.base.DeliveryType;

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
public class Store {
	InventoryService inventory;
	ShoppingCartService shoppingCart;
	LogisticService logistic;
	UI ui;

	public Store(UI ui, InventoryService inventory, ShoppingCartService shoppingCart, LogisticService logistic) {
		this.ui = ui;
		this.inventory = inventory;
		this.shoppingCart = shoppingCart;
		this.logistic = logistic;
		init();
		initLogistic();
	}

	public void init() {
		Product iPadProduct = new Product("New IPad", 400.00);
		inventory.addProduct(iPadProduct, 100);

		Product iPhoneProduct = new Product("IPhone 5", 500.00);
		inventory.addProduct(iPhoneProduct, 200);

		Product galaxyPadProduct = new Product("Galaxy Tab 2", 300.00);
		inventory.addProduct(galaxyPadProduct, 300);
		
	}
	
	public void initLogistic() {
		DeliveryType selfService = new DeliveryType("Self service", 0, 0, "Come to our outlet to get your products");
		logistic.addDeliveryType(selfService);
		
		DeliveryType standardDeliveryService = new DeliveryType("Standard delivery", 5, 7, "N/A");
		logistic.addDeliveryType(standardDeliveryService);
		
		DeliveryType cashOnDeliveryService = new DeliveryType("Cash on delivery service", 8, 7,"Pay only while receive the products");
		logistic.addDeliveryType(cashOnDeliveryService);
		
		DeliveryType fastDeliveryService = new DeliveryType("Fast delivery", 15, 2,"N/A");
		logistic.addDeliveryType(fastDeliveryService);
	}

	public List<DeliveryType> getDeliveryTypes(){
		return logistic.getAvailableDeliveryTypes();
	}
	
	public List<Product> getAvailableProducts() {
		return inventory.getAvailableProducts();
	}

	public Map<Product, Integer> getShoppedProducts() {
		return shoppingCart.getShoppedProducts();
	}
	
	public Map<Product, Integer> getAllProductsStock() {
		return inventory.getAllProductsStock();
	}

	public void addItemToCart(Product product, int quantity) {

		if (product == null)
			return;

		if (inventory.getStock(product) >= quantity) {
			shoppingCart.addItem(product, quantity);
			inventory.reduceStock(product, quantity);
			ui.purchaseSuccess(product, quantity);
		} else {
			ui.purchaseFail(product, inventory.getStock(product));
		}
	}

	public void removeItemsFromCart(Product product, int quantity) {

		if (product == null)
			return;
		if (quantity == 0)
			return;
		inventory.increaseStock(product, quantity);
		shoppingCart.removeItem(product, quantity);
	}

	public double getShoppingCartPrice() {
		return shoppingCart.getPrice();
	}

	public void checkOut() {
		shoppingCart.empty();
	}

}
