package com.moneytap.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

import com.moneytap.model.BitcoinPrice;

public class BitcoinDetails {

	private String getBitcoinHistory(int duration) {
		long time = Instant.now().minus(duration, ChronoUnit.MINUTES).toEpochMilli();
		System.out.println("time here:"+time);
		String output = "";
		try{
			URL url = new URL("https://api.gemini.com/v1/trades/btcusd?since="+time);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			System.out.println("Data Received .... \n");
			String data;
			while ((data = br.readLine()) != null) {
				System.out.println(data);
				output += data;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return output;
	}

	private ArrayList<ArrayList<Double>> getPriceList(int duration){
		String priceHistory = getBitcoinHistory(duration);
		JSONArray priceArr = new JSONArray(priceHistory);
		ArrayList<Double> sellPriceList = new ArrayList<Double>();
		ArrayList<Double> buyPriceList = new ArrayList<Double>();
		for (int i=0;i<priceArr.length();i++) {
			//System.out.println(priceArr.get(i));
			JSONObject obj = (JSONObject)priceArr.get(i);
			double price = Double.parseDouble(obj.getString("price"));
			String type = obj.getString("type");
			System.out.println("type:"+type+" price:"+price);
			if(type.indexOf("buy")>=0){
				buyPriceList.add(price);
			}else{
				sellPriceList.add(price);
			}
		}
		System.out.println("sell list:"+sellPriceList.size());
		System.out.println("buy list:"+buyPriceList.size());
		ArrayList<ArrayList<Double>> sellBuyList = new ArrayList<ArrayList<Double>>();
		Collections.sort(sellPriceList);
		Collections.sort(buyPriceList);
		sellBuyList.add(sellPriceList);
		sellBuyList.add(buyPriceList);
		return sellBuyList;
	}


	public BitcoinPrice getMeanPrice(int duration){
		ArrayList<ArrayList<Double>> sellBuyList = getPriceList(duration);
		ArrayList<Double> sellPriceList = sellBuyList.get(0);
		ArrayList<Double> buyPriceList = sellBuyList.get(1);

		double sellPriceMean = 0;
		double buyPriceMean = 0;
		BitcoinPrice btcPrice = new BitcoinPrice();
		if(sellPriceList.size() > 0){
			for (Double price : sellPriceList) {
				sellPriceMean += price;
			}
			sellPriceMean /= sellPriceList.size();
			btcPrice.setSellPrice(String.format("%1.2f",sellPriceMean));
		}else{
			btcPrice.setSellPrice("NA");
		}

		if(buyPriceList.size() > 0){
			for (Double price : buyPriceList) {
				buyPriceMean += price;
			}
			buyPriceMean /= buyPriceList.size();
			btcPrice.setBuyPrice(String.format("%1.2f", buyPriceMean));
		}else{
			btcPrice.setBuyPrice("NA");
		}
		return btcPrice;
	}

	public BitcoinPrice getMedianPrice(int duration){
		ArrayList<ArrayList<Double>> sellBuyList = getPriceList(duration);
		ArrayList<Double> sellPriceList = sellBuyList.get(0);
		ArrayList<Double> buyPriceList = sellBuyList.get(1);

		double sellPriceMean = 0;
		double buyPriceMean = 0;
		BitcoinPrice btcPrice = new BitcoinPrice();
		if(sellPriceList.size() > 0){
			if(sellPriceList.size() % 2 == 0) {
				int index = sellPriceList.size()/2;
				sellPriceMean = (sellPriceList.get(index-1) + sellPriceList.get(index))/2;
			}else {
				int index = sellPriceList.size()/2;
				sellPriceMean = sellPriceList.get(index);
			}
			System.out.println("sell price median:"+sellPriceMean);
			btcPrice.setSellPrice(String.format("%1.2f",sellPriceMean));
		}else{
			btcPrice.setSellPrice("NA");
		}

		if(buyPriceList.size() > 0){
			if(buyPriceList.size() % 2 == 0) {
				int index = buyPriceList.size()/2;
				buyPriceMean = (buyPriceList.get(index-1) + buyPriceList.get(index))/2;
			}else {
				int index = buyPriceList.size()/2;
				buyPriceMean = buyPriceList.get(index);
			}
			System.out.println("buy price median:"+buyPriceMean);
			btcPrice.setBuyPrice(String.format("%1.2f",buyPriceMean));
		}else{
			btcPrice.setBuyPrice("NA");
		}
		return btcPrice;
	}

	//	public static void main(String[] args){
	//		System.out.println(getMeanPrice(50));
	//		System.out.println(getMedianPrice(50));
	//	}
}
