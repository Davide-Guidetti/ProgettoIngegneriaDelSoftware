package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BeanTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	private Set<BeanItem> items = new HashSet<BeanItem>();

	public Set<BeanItem> getItems() {
		return items;
	}
	
	public BeanItem[] getItemsArr() {
		return items.toArray(new BeanItem[0]);
	}
	
	public void empty() {
		this.items = new HashSet<BeanItem>();
	}

}
