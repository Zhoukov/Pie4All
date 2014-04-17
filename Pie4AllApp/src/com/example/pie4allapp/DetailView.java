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

public class DetailView extends Activity implements OnClickListener{

String selectedProduct;
TextView titel;
TextView detail;
Button bestellen;
EditText invoernr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Intent detailview = getIntent();
		selectedProduct = detailview.getStringExtra("selectedProduct");
		
		System.out.println("Detailview: Selected product: "+selectedProduct);
		
		titel = (TextView) findViewById(R.id.titel);
		titel.setText(selectedProduct);
		
		detail = (TextView) findViewById(R.id.detail);
		detail.setText(PieData.getInstance().getProductInfo(selectedProduct));
		
		invoernr = (EditText) findViewById(R.id.invoernr);
		
		bestellen = (Button) findViewById(R.id.bestellen);
		bestellen.setOnClickListener(this);
			
		
			
	}
	

	@Override
    public void onClick(View v) {
      
		//invoernr.getText().toString();
		
		System.out.println(invoernr.getText().toString());
		
		Intent i = new Intent(DetailView.this, CustomerInfo.class);
		i.putExtra("hoeveelheid", (String)invoernr.getText().toString());
		i.putExtra("product", (String)selectedProduct);
		startActivity(i);

		finish();
		
		
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
		Intent i = new Intent(DetailView.this, MainActivity.class);
		startActivity(i);
		finish();
       
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	
	        	//System.out.println("UP Pressed");
	        	
	        	Intent i = new Intent(DetailView.this, MainActivity.class);
	        	startActivity(i);
	        	
	        	finish();
	        	
	            return(true);
	    }

	    return(super.onOptionsItemSelected(item));
	}


	
	

}
	
	
	

