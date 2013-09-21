package de.htw.toto.moco.app.gui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 21.09.13
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class POIArrayAdapter extends ArrayAdapter<POIList> {
    public final Context context;
    public final POI[] data;
    int layoutResourceId;
    LayoutInflater layoutInflater;

    public POIArrayAdapter(Context context,int layoutResourceId ,POI[] data) {
        super(context,layoutResourceId , R.layout.chat);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView text;

        if (convertView == null) {
            view = layoutInflater.inflate(layoutResourceId, parent, false);
        } else {
            view = convertView;
        }

        text = (TextView) view;

               // text = (TextView) view.findViewById(mFieldId);




        POI item = data[position];

        text.setText(item.getName());


        return view;
    }



    //

}
