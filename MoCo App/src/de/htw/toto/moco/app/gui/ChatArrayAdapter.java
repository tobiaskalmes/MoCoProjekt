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
    public final Context       context;
    public final ChatMessage[] data;
    int layoutResourceId;

    public ChatArrayAdapter(Context context, int layoutResourceId, ChatMessage[] data) {
        super(context, layoutResourceId, R.layout.chat);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ChatMessageHolder holder = null;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            //switch ressource ID?
            row = inflater.inflate(layoutResourceId, parent,false);

            holder = new ChatMessageHolder();

            //select right id for view
            holder.lowerText = (TextView)row.findViewById(R.id.secondLineLeft);
            holder.upperText = (TextView)row.findViewById(R.id.firstLineLeft);
            row.setTag(holder);
        } else {
            holder = (ChatMessageHolder)row.getTag();
        }

        ChatMessage chatMessage = data[position];
        holder.upperText.setText(chatMessage.getSender()+":");
        holder.lowerText.setText(chatMessage.getContent());
        return row;
    }

    static class ChatMessageHolder{
        TextView upperText;
        TextView lowerText;
    }

}
