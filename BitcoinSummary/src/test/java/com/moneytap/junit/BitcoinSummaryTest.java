package com.moneytap.junit;

import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BitcoinSummaryTest {
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void testBitcoinMeanPrice() {
		Response res = get("BitcoinSummary/btc/mean/12");
		assertNotNull(res);
	}

	@Test
	public void testBitcoinMedianPrice() {
		Response res = get("BitcoinSummary/btc/median/12");
		assertNotNull(res);
	}
}
