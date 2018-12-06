package codabook.virtualstore.logistic;

import java.util.ArrayList;
import java.util.List;

import codabook.componentmodel.*;
import codabook.virtualstore.base.DeliveryType;
import codabook.virtualstore.base.LogisticService;

/***
 * 
 * @author oka96
 *
 */

public class Logistic implements LogisticService, ComponentInterface {
	private List<DeliveryType> deliveryTypes = new ArrayList<>();
	
	@Override
	public void addDeliveryType(DeliveryType dt) {
		// TODO Auto-generated method stub
		deliveryTypes.add(dt);
	}

	@Override
	public void removeDeliveryType(String name) {
		// TODO Auto-generated method stub
		for(DeliveryType d: deliveryTypes){
			if(d.getName().equals(name)) {
				deliveryTypes.remove(d);
			}
		}
	}

	@Override
	public List<DeliveryType> getAvailableDeliveryTypes() {
		// TODO Auto-generated method stub
		return deliveryTypes;
	}

}
