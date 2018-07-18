package com.moneytap.model;

import javax.xml.bind.annotation.XmlRootElement;

public class BitcoinPrice {

	private String currency = "USD";
	
	private String sellPrice;
	
	private String buyPrice;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	@Override
	public String toString() {
		return "BitcoinPrice [currency=" + currency + ", sellPrice="
				+ sellPrice + ", buyPrice=" + buyPrice + "]";
	}

	
}
