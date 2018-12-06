package codabook.virtualstore.base;

import java.util.List;

public interface LogisticService {
	
	public void addDeliveryType(DeliveryType dt);
	public void removeDeliveryType(String deliveryTypeName);
	public List<DeliveryType> getAvailableDeliveryTypes();
}
