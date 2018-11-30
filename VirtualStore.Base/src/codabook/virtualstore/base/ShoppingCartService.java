package codabook.virtualstore.base;
import java.util.Map;
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
public interface ShoppingCartService {

	public void addItem(Product product, int quantity);

	public Map<Product, Integer> getShoppedProducts();

	public void removeItem(Product product, int quantity);

	public void empty();

	public double getPrice();

}