package com.example.pie4allapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;

public class PieData
{
	//singleton
	private static PieData _instance;
	public static PieData getInstance()
	{
		if( _instance == null )
			_instance = new PieData();
		
		return _instance;
	}
	


	private static String[] categories = {"Vlaaien", "Cakes", "Bruidstaarten", "Verjaardagstaarten"};
	private HashMap<String, String[]> products;
	private HashMap<String, String> productInfo;
	
	Socket s;
	Scanner reader;
	PrintWriter writer;
	String server = "94.211.183.172";
	int port = 4444;
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private PieData()
	{	
		products = new HashMap<String,String[]>();
		productInfo = new HashMap<String,String>();
		
		String[] vlaaien = { "Kersenvlaai", "Perzikvlaai" };
		products.put("Vlaaien", vlaaien );

		productInfo.put( "Kersenvlaai", "Heerlijk verse kersen zonder pit" );
		productInfo.put( "Perzikvlaai", "Mierzoet en extra plakkerig" );
		

		String[] cakes = { "Boerencake", "Chocoladecake" };
		products.put("Cakes", cakes );

		productInfo.put("Boerencake", "Rechtstreeks van het platteland" );
		productInfo.put("Chocoladecake", "Met hele stukken pure chocolade" );
		
		
		String[] bruidstaarten = { "Chocolade bruidstaart", "Aardbei bruidstaart" };
		products.put("Bruidstaarten", bruidstaarten );
		
		productInfo.put("Chocolade bruidstaart", "Drie lagen vloeibare chocola" );
		productInfo.put("Aardbei bruidstaart", "Vier lagen alleen maar aardbei" );
		
		
		String[] verjaardagstaarten = { "Slagroomtaart", "Kwarktaart" };
		products.put("Verjaardagstaarten", verjaardagstaarten );
		
		productInfo.put("Slagroomtaart", "Meer slagroom dan taart" );
		productInfo.put("Kwarktaart", "Met citroenzure smaak" );
		
		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		
//		 this.server = (String) MainActivity.ipadres.toString();
//		 this.port = Integer.parseInt(MainActivity.port.toString());
		
		try {
			s = new Socket(server, port);
			reader = new Scanner(new BufferedReader(new InputStreamReader(s.getInputStream())));
			writer = new PrintWriter(s.getOutputStream(), true);
		}catch(Exception e)
		{
			System.out.println("NO INTERNET MAN");
		}
		//update();
	}
	
	public String[] getCategories()
	{
		return categories;
	}
	
	public String[] getProducts( String category )
	{
		return products.get( category );
	}
	
	public String getProductInfo( String product )
	{
		return productInfo.get( product );
	}

	public static String[] getArray() {
		return categories;
	}
	
	public void update()
	{
			String response = "NO RESPONSE FOR YOU";
			
						
			sendToServer("{\"categorielijst\":\"\"}");
			response = reader.nextLine();
			processCategories(response);
			
			for(String c : categories)
			{
				sendToServer("{\"productenlijst\" : \""+c+"\" }");
				response = reader.nextLine();
				processProducts(c, response);
			}
	}
	
	public void processCategories(String r)
	{
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(r);
			
			if(array == null)
				System.out.println("Faul:"+r);
			this.categories = (String[]) array.toArray();
		} catch (ParseException e) {
			System.out.println("Couldnt parse server response (Products).");
			e.printStackTrace();
		}
	}
	
	public void processProducts(String categorie, String r)
	{
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(r);
			
			String[] productslist = (String[]) array.toArray();
			products.remove(categorie);
			products.put(categorie, productslist );
		} catch (ParseException e) {
			System.out.println("Couldnt parse server response (Products).");
			e.printStackTrace();
		}
		
	}
	
	public void sendBestelling(String productnaam, String aantal, String naam, String adres, String telefoon, String email)
	{
		String JSON = "{ \"bestel\" : " +
					"[{ \"productnaam\" : \""+productnaam+"\", " +
					"\"productaantal\" : \""+aantal+"\" }," +
					"{ \"kopernaam\" : \""+naam+"\" ," +
					"\"koperadres\" : \""+adres+"\"," +
					"\"kopertelnr\" : \""+telefoon+"\"," +
					"\"koperemail\" : \""+email+"\" } ] }";
		sendToServer(JSON);
		System.out.println("Sending bestelling to '"+s.getInetAddress()+":"+s.getPort()+"'");
	}
	
	private void sendToServer(String msg)
	{
		try {
			s = new Socket(server, port);
			writer = new PrintWriter(s.getOutputStream(), true);
			writer.println(msg);
			writer.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

