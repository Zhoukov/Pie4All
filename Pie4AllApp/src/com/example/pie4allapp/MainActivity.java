package com.example.pie4allapp;

import java.util.ArrayList; 
import java.util.Arrays;
import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;



public class MainActivity extends Activity  implements OnItemSelectedListener, OnItemClickListener{

	
Spinner spinner;
ListView categorie;
PieData pieData;
ArrayList<String> productList = new ArrayList<String>();
//static EditText ipadres;
//static EditText port;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pieData = PieData.getInstance();
		Spinner spinner = (Spinner) findViewById(R.id.categories);
		this.spinner = spinner;
			String [] categories = PieData.getArray(); 
			categorie = (ListView) findViewById(R.id.listView1);
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, categories);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(dataAdapter);
			spinner.setOnItemSelectedListener(this);
			categorie.setOnItemClickListener(this);
			
//			ipadres = (EditText) findViewById(R.id.ipadres);
//			ipadres.setText("94.211.183.172");
//			
//			port = (EditText) findViewById(R.id.port);
//			port.setText(4444);
			
			
			
			
			if(Preferences.getInstance(this) == null)
				System.out.println("no instance of preferences");
		String[] pref = Preferences.getInstance(this).getMainActivityPreferences();
		if(pref[0] != null)
			spinner.setSelection(Integer.parseInt(pref[0]));
		
		
	}
		
			 
	
	
			 public void onItemSelected(AdapterView<?> parent, View view, int position,
					   long id) {
				 
				 String[] loc = { ""+position };
				 Preferences.getInstance(this).updateMainActivityPreferences(loc);
				 
				 switch(position){
				 case 0: //Vlaaien
					 System.out.println("Vlaai");
					 
					productList.clear();
					productList.addAll(Arrays.asList(pieData.getProducts("Vlaaien")));
					
										
					break;
				 case 1: //Cakes
					 System.out.println("cake");
					 
					 productList.clear();
						productList.addAll(Arrays.asList(pieData.getProducts("Cakes")));
						break;
				 case 2: //Bruidstaarten
					 System.out.println("btaart");
					 
					 productList.clear();
						productList.addAll(Arrays.asList(pieData.getProducts("Bruidstaarten")));
						 break;
				 case 3: //Verjaardagstaarten
					 System.out.println("vtaart");
					 
					 productList.clear();
						productList.addAll(Arrays.asList(pieData.getProducts("Verjaardagstaarten")));
					 break;
					 }
				 
				 
				 ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productList);
				 categorie.setAdapter(listAdapter);
				 
				 }

					 @Override
					 public void onNothingSelected(AdapterView<?> arg0) {
					  // TODO Auto-generated method stub

					  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; }




	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		System.out.println("List clicked: "+ arg2);
		if(spinner == null){
			System.out.println("no spinner");
		}else{
		spinner.getSelectedItem().toString();
		System.out.println("Categorie selected: "+(String)spinner.getSelectedItem());
		productList.get(arg2);
		System.out.println("Product selected: "+(String)productList.get(arg2));
		}
		
		
		Intent i = new Intent(MainActivity.this, DetailView.class);
		i.putExtra("selectedProduct", (String)productList.get(arg2));
		startActivity(i);

		finish();
		
		
		
	}

	

		
	
	
}