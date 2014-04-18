package com.example.pie4allapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

	private static Preferences _instance;
	private static final String PREFS_NAME = "PIE4ALL";
	private SharedPreferences settings;
	private Editor editor;
	public static String[] mainViewPreferences = { "dropDown" };
	public static String[] detailViewPreferences = { "aantal", "productnaam" };
	public static String[] customerInfoPreferences = { "naam", "adres", "telefoon", "email" };
	 
	public static Preferences getInstance(Context c)
	{
		if( _instance == null )
			_instance = new Preferences(c);
		
		return _instance;
	}
	
	private Preferences(Context c)
	{
		settings = c.getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
	}
	
	public String[] getMainActivityPreferences()
	{
		ArrayList<String> resp = new ArrayList<String>();
		for(int i = 0; i < mainViewPreferences.length; i++)
		{
			resp.add(settings.getString(mainViewPreferences[i], null));
		}
		return (String[]) resp.toArray(new String[mainViewPreferences.length]);
	}
	public String[] getDetailViewPreferences()
	{
		ArrayList<String> resp = new ArrayList<String>();
		for(int i = 0; i < detailViewPreferences.length; i++)
		{
			resp.add(settings.getString(detailViewPreferences[i], null));
		}
		return (String[]) resp.toArray(new String[detailViewPreferences.length]);
	}
	public String[] getCustomerInfoPreferences()
	{
		ArrayList<String> resp = new ArrayList<String>();
		for(int i = 0; i < customerInfoPreferences.length; i++)
		{
			resp.add(settings.getString(customerInfoPreferences[i], null));
		}
		return (String[]) resp.toArray(new String[customerInfoPreferences.length]);
	}
	
	public void updateMainActivityPreferences(String[] input)
	{
		if(input == null)
			return;
		for(int i = 0; i < input.length; i++)
		{
			editor.putString(mainViewPreferences[i], input[i]);
		}
		editor.commit();
	}
	public void updateDetailViewPreferences(String[] input)
	{
		for(int i = 0; i < input.length; i++)
		{
			editor.putString(detailViewPreferences[i], input[i]);
		}
		editor.commit();
	}
	public void updateCustomerInfoPreferences(String[] input)
	{
		for(int i = 0; i < input.length; i++)
		{
			editor.putString(customerInfoPreferences[i], input[i]);
		}
		editor.commit();
	}
}
