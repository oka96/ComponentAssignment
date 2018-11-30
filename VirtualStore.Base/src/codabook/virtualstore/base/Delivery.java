package codabook.virtualstore.base;

public class Delivery {
	String name;
	double fees;
	int deliveryDay;
	public Delivery() {}
	
	public String getName() {
		return name;
	}

	public double getFees() {
		return fees;
	}

	public int getDeliveryDay() {
		return deliveryDay;
	}

	public Delivery(String name,double fees,int deliveryDay) {
		this.name = name;
		this.fees = fees;
		this.deliveryDay = deliveryDay;
	}
	
}
