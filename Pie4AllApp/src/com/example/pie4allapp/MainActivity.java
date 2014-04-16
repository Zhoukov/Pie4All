package com.example.pie4allapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity  implements OnItemSelectedListener {

	
Spinner spinner;
TextView categorie;
PieData pieData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pieData = PieData.getInstance();
		Spinner spinner = (Spinner) findViewById(R.id.categories);
			String [] categories = PieData.getArray(); 
			categorie = (TextView) findViewById(R.id.textView1);
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, categories);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(dataAdapter);
			spinner.setOnItemSelectedListener(this);
	}
		
			 
			 public void onItemSelected(AdapterView<?> parent, View view, int position,
					   long id) {
				 String text = "";
				 switch(position){
				 case 0: //Vlaaien
					 System.out.println("Vlaai");
					 
					 text = "";
					 for(String s : pieData.getProducts("Vlaaien"))
						 text +=s+"\n";
					 categorie.setText(text);					 
					 break;
				 case 1: //Cakes
					 System.out.println("cake");
					 text = "";
					 for(String s : pieData.getProducts("Cakes"))
						 text +=s+"\n";
					 categorie.setText(text);
					 break;
				 case 2: //Bruidstaarten
					 System.out.println("btaart");
					 text = "";
					 for(String s : pieData.getProducts("Bruidstaarten"))
						 text +=s+"\n";
					 categorie.setText(text);
					 break;
				 case 3: //Verjaardagstaarten
					 System.out.println("vtaart");
					 text = "";
					 for(String s : pieData.getProducts("Verjaardagstaarten"))
						 text +=s+"\n";
					 categorie.setText(text);
					 break;
					 }
				 
				 
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

	

		
	
	
}