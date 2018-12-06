package codabook.virtualstore.base;

public class DeliveryType {
	String name;
	double fees;
	int deliveryDay;
	String description;
		
	public String getName() {
		return name;
	}

	public double getFees() {
		return fees;
	}

	public int getDeliveryDay() {
		return deliveryDay;
	}

	public String getDescription() {
		return description;
	}
	
	public DeliveryType(String name,double fees,int deliveryDay, String description) {
		this.name = name;
		this.fees = fees;
		this.deliveryDay = deliveryDay;
		this.description = description;
	}
	
}
