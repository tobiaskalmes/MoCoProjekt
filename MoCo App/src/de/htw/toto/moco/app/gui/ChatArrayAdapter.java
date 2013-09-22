package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.SessionInfo;
import de.htw.toto.moco.server.messaging.ChatMessage;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 20.09.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
//TODO MASSIVE
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    int            ressource;
    //LayoutInflater layoutInflater;

    public ChatArrayAdapter(Context context, int layoutResourceId, ChatMessage[] data) {
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

        ChatMessage item = getItem(position);

        text.setText(item.getContent());


        return view;
    }

}
