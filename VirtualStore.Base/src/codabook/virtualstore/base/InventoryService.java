package codabook.virtualstore.base;

import java.util.List;
import java.util.Map;

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

**************************************************************************************************/
public interface InventoryService {

	public void addProduct(Product product, int stock);

	public void reduceStock(Product product, int quantity);

	public void increaseStock(Product product, int quantity);

	public List<Product> getAvailableProducts();
	
	public Map<Product, Integer> getAllProductsStock();

	public int getStock(Product product);

}