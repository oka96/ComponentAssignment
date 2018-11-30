package codabook.componentmodel;

import java.util.HashMap;
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
public class ComponentRegistry {

	static Map<Class<?>, Object> components = new HashMap<Class<?>, Object>();
	
	public static void registerComponent(Class<?> ifceClazz, Object compObject) {
		components.put(ifceClazz, compObject);
	}
	
	public static Object fetchComponent(Class<?> ifceClazz) {
		return components.get(ifceClazz);
	}
}
