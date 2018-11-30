package codabook.virtualstore.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codabook.componentmodel.ComponentInterface;
import codabook.virtualstore.base.InventoryService;
import codabook.virtualstore.base.Product;
/*************************************************************************************************
*
* COMPANION CODE FOR THE BOOK “Component Oriented Development & Assembly – CODA Using Java”
* 
* @author – Piram Manickam, Sangeetha S, Subrahmanya S V
* @see -  http://www.codabook.com
* 
* <br><br><b>CODE CONTRIBUTORS</b> – <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
* Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>
* 
**************************************************************************************************/
public class Inventory implements InventoryService, ComponentInterface {

	Map<Product, Integer> products = new HashMap<Product, Integer>();
	

	@Override
	public void addProduct(Product product, int stock) {
		products.put(product, stock);
	}
	

	@Override
	public void reduceStock(Product product, int quantity) {
		int oldStock = products.get(product);
		int newStock = oldStock - quantity;
		products.put(product, newStock);
	}
	

	@Override
	public void increaseStock(Product product, int quantity) {
		int oldStock = products.get(product);
		int newStock = oldStock + quantity;
		products.put(product, newStock);
	}
	

	@Override
	public List<Product> getAvailableProducts() {
		
		List<Product> availableProducts = new ArrayList<Product>();
		for (Product product : products.keySet()) {
			if (products.get(product) > 0)
				availableProducts.add(product);
		}
		
		return availableProducts;
	}
	

	@Override
	public int getStock(Product product) {
		return products.get(product);
	}


	@Override
	public Map<Product, Integer> getAllProductsStock() {
		return products;
	}
	
}
