package codabook.virtualstore.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import codabook.componentmodel.ComponentInterface;
import codabook.virtualstore.base.Product;
import codabook.virtualstore.base.ShoppingCartService;
/*************************************************************************************************
*
* COMPANION CODE FOR THE BOOK “Component Oriented Development & Assembly – CODA Using Java”
* 
* @author – Piram Manickam, Sangeetha S, Subrahmanya S V
* @see -  http://www.codabook.com
* 
* <br><br><b>CODE CONTRIBUTORS</b> – <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
* Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>

**************************************************************************************************/
public class ShoppingCart implements ShoppingCartService, ComponentInterface {

	private Map<Product, Integer> shoppedProducts = new HashMap<Product, Integer>();

	@Override
	public void addItem(Product product, int quantity) {
		if (shoppedProducts.containsKey(product)) {
			quantity += shoppedProducts.get(product);
		}
		shoppedProducts.put(product, quantity);
	}
	
	@Override
	public Map<Product, Integer> getShoppedProducts() {
		return shoppedProducts;
	}
	
	@Override
	public void removeItem(Product product, int quantity) {
		if (!shoppedProducts.containsKey(product)) return;
		int oldQuantity = shoppedProducts.get(product);
		int newQuantity = oldQuantity - quantity;
		if (newQuantity > 0)
			shoppedProducts.put(product, newQuantity);
		else
			shoppedProducts.remove(product);
	}

	@Override
	public void empty() {
		shoppedProducts.clear();
	}
	
	@Override
	public double getPrice() {
		double totalPrice = 0;
		for (Product product : shoppedProducts.keySet()) {
			double unitPrice = product.getPrice();
			int quantity = shoppedProducts.get(product);
			double subTotal = unitPrice * quantity;
			totalPrice += subTotal;
		}
		return totalPrice;
	}

}
