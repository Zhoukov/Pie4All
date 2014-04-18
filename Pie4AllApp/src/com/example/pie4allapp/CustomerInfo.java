package com.example.pie4allapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerInfo extends Activity implements OnClickListener{

String hoeveelheid;
TextView Quantity;
String product;
EditText naam;
EditText email;
EditText telefoon;
EditText adres; 
Button annuleren;
Button bestellen;

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		Intent customerinfo = getIntent();
		hoeveelheid = customerinfo.getStringExtra("hoeveelheid");
		product = customerinfo.getStringExtra("product");
		
		System.out.println(hoeveelheid);
		
		Quantity = (TextView) findViewById(R.id.textView1);
		Quantity.setText("U heeft "+ hoeveelheid + " keer " + product + " gekozen, vul hier uw naam, adres, telefoonnummer en e-mail in alstublief");
		
		naam = (EditText) findViewById(R.id.naam);
		//naam.setHint("Naam");
		
		adres = (EditText) findViewById(R.id.adres);
		//adres.setHint("Adres");
		
		telefoon = (EditText) findViewById(R.id.telefoon);
		//telefoon.setHint("0611111111");
		
		email = (EditText) findViewById(R.id.email);
		//telefoon.setHint("E-mail");
		
		annuleren = (Button) findViewById(R.id.annuleren);
		annuleren.setOnClickListener(this);
		
		bestellen = (Button) findViewById(R.id.bestellen);
		bestellen.setOnClickListener(this);
		
		String[] prefs = Preferences.getInstance(this).getCustomerInfoPreferences();
		if(prefs[0] != null)
			this.naam.setText(prefs[0]);
		if(prefs[1] != null)
			this.adres.setText(prefs[1]);
		if(prefs[2] != null)
			this.telefoon.setText(prefs[2]);
		if(prefs[3] != null)
			this.email.setText(prefs[3]);
		}
	
	@Override
	public void onClick(View v) {
		String[] newprefs = { this.naam.getText().toString(), this.adres.getText().toString(), this.telefoon.getText().toString(), this.email.getText().toString() };
		Preferences.getInstance(this).updateCustomerInfoPreferences(newprefs);
        switch(v.getId()){
        case R.id.bestellen:

        PieData.getInstance().sendBestelling(this.product,
        									this.hoeveelheid, 
        									this.naam.getText().toString(), 
        									this.adres.getText().toString(), 
        									this.telefoon.getText().toString(), 
        									this.email.getText().toString());
        this.annuleren.setText("Terug");
        this.bestellen.setEnabled(false);
        Toast.makeText(this, "Bestelling verzonden, bedankt!", Toast.LENGTH_SHORT).show();
        
        break;
        case R.id.annuleren:
        	Intent j = new Intent(CustomerInfo.this, MainActivity.class);
        	startActivity(j);
        	
        	finish();
        break;
    }
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
	{
        if ((keyCode == KeyEvent.KEYCODE_BACK)) 
        {
            onBackPressed();
            System.out.println("Back pressed");
        }
       
		return true;
	}
	@Override
    public void onBackPressed()
	{
		Intent i = new Intent(CustomerInfo.this, MainActivity.class);
		startActivity(i);
		finish();
       
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	
	        	//System.out.println("UP Pressed");
	        	
	        	Intent i = new Intent(CustomerInfo.this, MainActivity.class);
	        	startActivity(i);
	        	
	        	finish();
	        	
	            return(true);
	    }

	    return(super.onOptionsItemSelected(item));
	}


}


