package com.moneytap.junit;

import static io.restassured.RestAssured.get;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class BitcoinSummaryTest {
	@BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testBitcoinMeanPrice() {
        get("BitcoinSummary/btc/mean/12");
    }
    
    @Test
    public void testBitcoinMedianPrice() {
        get("BitcoinSummary/btc/median/12");
    }
}
