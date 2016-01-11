package stockExchange.to;

import org.springframework.stereotype.Component;

@Component
public class WalletStock {
	private QuotationObject object;
	private int quantity;

	public WalletStock(){
		
	}
	
	public WalletStock(QuotationObject object, int quantity) {
		this.object = object;
		this.quantity = quantity;
	}
	
	public QuotationObject getObject() {
		return object;
	}

	public void setObject(QuotationObject object) {
		this.object = object;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletStock other = (WalletStock) obj;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
