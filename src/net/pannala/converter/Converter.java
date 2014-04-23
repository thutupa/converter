package net.pannala.converter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class Converter extends Activity implements OnScrollListener {

    private static final int DEFAULT_SELECTED_POSITION = Integer.MAX_VALUE / 2;
    TwoWayConverterInterface converter;
    ListView lv1;
    ListView lv2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	centerLists();
    }

    private void centerLists() {
	// converter = new FarenhietCentigrateConverter();
	converter = new CentigradeFarenhietConverter();
	lv1 = (ListView) findViewById(R.id.listView1);
	ListAdapter lv1Adapter = new Cvt1ListAdapter(this, false);
	lv1.setAdapter(lv1Adapter);
	lv1.setSelection(DEFAULT_SELECTED_POSITION);
	lv1.setOnScrollListener(this);

	lv2 = (ListView) findViewById(R.id.listView2);
	ListAdapter lv2Adapter = new Cvt1ListAdapter(this, true);
	lv2.setAdapter(lv2Adapter);
	lv2.setSelection(DEFAULT_SELECTED_POSITION);
    }

    class Cvt1ListAdapter implements ListAdapter {

	Activity activity;
	boolean isSecondList;

	public Cvt1ListAdapter(Activity activity, boolean isSecondList) {
	    this.activity = activity;
	    this.isSecondList = isSecondList;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public int getCount() {
	    return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public long getItemId(int position) {
	    // TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public boolean hasStableIds() {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    if (convertView == null) {
		convertView = new TextView(activity);
	    }
	    String displayedText;
	    if (isSecondList) {
		displayedText = "second"
			+ (int) (position - DEFAULT_SELECTED_POSITION);
		Log.e("XYZ", "pos = " + (position - DEFAULT_SELECTED_POSITION)
			+ " value = " + displayedText);
	    } else {
		displayedText = "first"
			+ (position - DEFAULT_SELECTED_POSITION);
	    }
	    TextView tv = (TextView) convertView;
	    tv.setText(displayedText);
	    return convertView;
	}

	@Override
	public int getItemViewType(int position) {
	    // TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public int getViewTypeCount() {
	    return 1;
	}

	@Override
	public boolean isEmpty() {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public boolean areAllItemsEnabled() {
	    // TODO Auto-generated method stub
	    return true;
	}

	@Override
	public boolean isEnabled(int position) {
	    // TODO Auto-generated method stub
	    return true;
	}

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
	// TODO Auto-generated method stub

    }

    @SuppressLint("NewApi")
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
	    int visibleItemCount, int totalItemCount) {
	int firstIndex = firstVisibleItem - DEFAULT_SELECTED_POSITION;
	int first = firstIndex;
	int secondIndex = (int) converter.to(first);
	if (lv2 != null) {
	    Log.e("XYZ", "Scrolling to " + secondIndex + "src = " + firstIndex);
	    lv2.setSelectionFromTop(secondIndex + DEFAULT_SELECTED_POSITION, 0);
	}
    }
}
