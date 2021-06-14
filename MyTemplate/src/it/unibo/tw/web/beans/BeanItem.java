package it.unibo.tw.web.beans;

import java.io.Serializable;

public class BeanItem implements Serializable {

	int examplePropery;
	
	public void setOrdereQTY(int ordereQTY) {
		this.examplePropery = ordereQTY;
	}

	public int getOrdereQTY() {
		return examplePropery;
	}

}
