package de.htw.toto.moco.app.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 21.09.13
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class POIArrayAdapter extends ArrayAdapter<POI> {

    int            ressource;
    //LayoutInflater layoutInflater;

    public POIArrayAdapter(Context context, int layoutResourceId, POI[] data) {
        super(context, layoutResourceId, data);
        this.ressource = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View view;
         TextView text;
        Context context = getContext();

        LayoutInflater inflater = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (convertView == null) {
            view = inflater.inflate(ressource, parent, false);
        } else {
            view = convertView;
        }

        //no mField value
        text = (TextView) view;

        if (convertView == null) {
            view = inflater.inflate(ressource, parent, false);
        } else {
            view = convertView;
        }

        POI item = getItem(position);

        text.setText(item.getName());

        return view;
    }




}
