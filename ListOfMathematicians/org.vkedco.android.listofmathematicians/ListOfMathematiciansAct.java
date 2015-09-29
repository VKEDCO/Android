package org.vkedco.android.listofmathematicians;

/*
 *************************************************
 * ListOfMathematiciansAct is the main activity
 * class of ListOfMathematicians application.
 * The application demonstrates how to customize
 * views and array adapters. 
 * 
 * To run this app with different views, change
 * the view_type parameter in /res/values/params.xml:
 * 
 * <integer name="view_type">1</integer> - the value of
 * 1 uses the standard ListView and TextView layouts.
 * 
 * <integer name="view_type">2</integer> - the value of
 * 2 uses a custom view, MathematicianView, to display
 * an ArrayList<Mathematician>.
 * 
 * <integer name="view_type">3</integer> - the value of
 * 3 uses a custom view, MathematicianView, to display
 * an ArrayList<Mathematician> with a custom adapter
 * MathematicianViewArrayAdapter.
 *
 * @author: Vladimir Kulyukin
 * 
 ************************************************* 
 */

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListOfMathematiciansAct extends ListActivity {
	private static String[] mListOfMathematicians = null;
    private static Resources mRes = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // modify the value of view_type integer
        // parameter in /res/values/params.xml to
        // run the appropriate populateListView method.
        mRes = getResources();
        switch ( mRes.getInteger(R.integer.view_type) ) {
        case 1: 
        	populateListViewOne();
        	break;
        case 2:
        	populateListViewTwo();
        	break;
        case 3:
        	populateListViewThree();
        	break;
        }
        
        getListView().setTextFilterEnabled(true);
    }
    
    // use the standard ArrayAdapter<String>
    private void populateListViewOne() {
    	mListOfMathematicians = mRes.getStringArray(R.array.list_of_famous_mathematicians);
        
        getListView().setAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_expandable_list_item_1,
        		mListOfMathematicians));
    }
    
    // 1) customize views through Mathematician,
    // MathematicianView and /res/layout/mathematician_view_1.xml
    // 2) customize ArrayAdapter as ArrayAdapter<Mathematician>
    private void populateListViewTwo() {
    	mListOfMathematicians = mRes.getStringArray(R.array.list_of_famous_mathematicians);
    	ArrayList<Mathematician> mathematicians = 
    		new ArrayList<Mathematician>();
    	for(String name : mListOfMathematicians) {
    		String[] fnln = name.trim().split(" ");
    		mathematicians.add(new Mathematician(fnln[0], fnln[1]));
    	}
    	getListView().setAdapter(new ArrayAdapter<Mathematician>(this,
    			R.layout.mathematician_view_1,
    			mathematicians));
    }
    
    // customize populating Views in ListViews through
    // a custom array adapter MathematicianViewArrayAdapter
    private void populateListViewThree() {
    	mListOfMathematicians = mRes.getStringArray(R.array.list_of_famous_mathematicians);
    	ArrayList<Mathematician> mathematicians = 
    		new ArrayList<Mathematician>();
    	for(String name : mListOfMathematicians) {
    		String[] fnln = name.trim().split(" ");
    		mathematicians.add(new Mathematician(fnln[0], fnln[1]));
    	}
    	MathematicianViewArrayAdapter adptr =
    		new MathematicianViewArrayAdapter(this, 
    				R.layout.mathematician_view_2,
    				mathematicians);
    	getListView().setAdapter(adptr);
    }
}
