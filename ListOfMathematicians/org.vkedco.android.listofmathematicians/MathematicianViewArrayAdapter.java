package org.vkedco.android.listofmathematicians;

/*
 **********************************************************
 * MathematicianViewAdapter is a class whose instances
 * represent the names of famous mathematicians
 * displayed in a ListView.
 * 
 * author: vladimir kulyukin
 ********************************************************** 
 */

import java.util.List;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MathematicianViewArrayAdapter extends ArrayAdapter<Mathematician> {
	int mResID;
	Context mContext;
	List<Mathematician> mListOfMathematicians;
	
	public MathematicianViewArrayAdapter(Context c, int resID, List<Mathematician> items) {
		super(c, resID, items);
		mResID = resID;
		mContext = c;
		mListOfMathematicians = items;
	}
	
	// override getView method to return RelativeLayouts
	// to display Mathematician objects in the list view.
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		// mv is mathematician's view
		RelativeLayout mv;
		Mathematician selected_mat = getItem(position);
		String selected_first_name = selected_mat.getFirstName();
		String selected_last_name = selected_mat.getLastName();
		
		if ( convertView == null ) {
			mv = new RelativeLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(mResID, mv, true);
		}
		else {
			mv = (RelativeLayout)convertView;
		}
		
		// inflate the last_name and first_name text views
		// of the relative layout
		((TextView) mv.findViewById(R.id.last_name)).setText(selected_last_name + ",");
		((TextView) mv.findViewById(R.id.first_name)).setText(selected_first_name);
		
		// inflate the image view of the relative layout
		ImageView imgv = (ImageView) mv.findViewById(R.id.image);
		Resources res = mContext.getResources();
		// set the drawable of the image view to the
		// appropriate image
		
		Drawable draw = null;
		switch ( position ) {
		case 0: draw = res.getDrawable(R.drawable.alonzo_church); break;
		case 1: draw = res.getDrawable(R.drawable.kurt_godel); break;
		case 2: draw = res.getDrawable(R.drawable.david_hilbert); break;
		case 3: draw = res.getDrawable(R.drawable.giuseppe_peano); break;
		case 4: draw = res.getDrawable(R.drawable.georg_cantor); break;
		case 5: draw = res.getDrawable(R.drawable.abu_abdullah_muhammad_bin_musa_alkhwarizmi); break;
		case 6: draw = res.getDrawable(R.drawable.blaise_pascal); break;
		case 7: draw = res.getDrawable(R.drawable.isaac_newton); break;
		case 8: draw = res.getDrawable(R.drawable.johannes_kepler); break;
		case 9: draw = res.getDrawable(R.drawable.nikolaus_kopernikus); break;
		case 10: draw = res.getDrawable(R.drawable.omar_khayyam); break;
		default: draw = res.getDrawable(R.drawable.menu_icon);
		}
		
		imgv.setImageDrawable(draw);
		
		return mv;
		
	}
}
