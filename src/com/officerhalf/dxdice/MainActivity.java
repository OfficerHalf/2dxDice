package com.officerhalf.dxdice;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

//Everything happens here
public class MainActivity extends Activity {

	protected NumberPicker dicePicker;
	protected NumberPicker sidesPicker;
	protected int[] lastDice = {0, 0};
	protected int[] lastRoll;
	protected int[] savedDice = {0, 0};
	protected int[] savedRoll = {0};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Set up number pickers
		dicePicker = (NumberPicker) findViewById(R.id.dicePicker);
		sidesPicker = (NumberPicker) findViewById(R.id.sidesPicker);
		dicePicker.setMaxValue(100);
		dicePicker.setMinValue(1);
		sidesPicker.setMaxValue(100);
		sidesPicker.setMinValue(1);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void roll_button (View view) {
		
		//Get sides, number of dice
		int dice = dicePicker.getValue();
		int sides = sidesPicker.getValue();

		lastDice[0] = dice;
		lastDice[1] = sides;

		int[] rolls = new int[dice];	
		
		//Get Rolls
		Random r = new Random();
		for(int i = 0; i < dice; i++)
			rolls[i] = r.nextInt(sides) + 1;
		
		//Save roll for recall
		lastRoll = rolls;
		
		//Display Rolls
		TextView output = (TextView) findViewById(R.id.roll_output);
		String outputText = Integer.toString(dice) + "d" + Integer.toString(sides) + ":  ";
		for(int i = 0; i < dice; i++)
		{
			outputText = outputText + rolls[i];
			if(i != dice - 1)
			{
				outputText = outputText + "  +  ";
			}
		}
		int sum = 0;
		for(int i = 0; i < dice; i++)
			sum += rolls[i];
		outputText = outputText + "  =  " + Integer.toString(sum);
		output.setText(outputText);
	}
	
	public void save_roll (View view) {

		//Save the sides/number of dice as well as the totals
		savedRoll = lastRoll;
		savedDice[0] = lastDice[0];
		savedDice[1] = lastDice[1];
		
	}
	
	public void recall_roll (View view) {

		//Check to see if a roll has been made
		if(savedRoll[0] != 0)
		{

			//Show the saved roll
			TextView output = (TextView) findViewById(R.id.roll_output);
			String outputText = Integer.toString(savedDice[0]) + "d" + Integer.toString(savedDice[1]) + ":  ";
			for(int i = 0; i < savedDice[0]; i++)
			{
				outputText = outputText + savedRoll[i];
				if(i != savedDice[0] - 1)
				{
					outputText = outputText + "  +  ";
				}
			}
			int sum = 0;
			for(int i = 0; i < savedDice[0]; i++)
				sum += savedRoll[i];
			outputText = outputText + "  =  " + Integer.toString(sum);
			output.setText(outputText);
		}
	}
}
