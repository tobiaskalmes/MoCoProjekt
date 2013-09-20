package de.htw.toto.moco.app.gui;

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
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage>{
    public final Context context;
    public final ChatMessage[] chatMessages;

    public ChatArrayAdapter(Context context, ChatMessage[] chatMessages) {
        super(context, R.layout.chatrowright);
        this.context = context;
        this.chatMessages = chatMessages;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        TextView textViewFirst;
        TextView textViewSecond;
        if(SessionInfo.getInstance().getUsername().equals(chatMessages[position].getSender())){
        rowView = inflater.inflate(R.layout.chatrowright, parent, false);
             textViewFirst = (TextView) rowView.findViewById(R.id.firstLineRight);
             textViewSecond = (TextView) rowView.findViewById(R.id.secondLineRight);
        }else {
            rowView = inflater.inflate(R.layout.chatrowleft, parent, false);
             textViewFirst = (TextView) rowView.findViewById(R.id.firstLineLeft);
             textViewSecond = (TextView) rowView.findViewById(R.id.secondLineLeft);
        }
        textViewFirst.setText(chatMessages[position].getSender()+":");
        textViewSecond.setText(chatMessages[position].getContent());

        return rowView;
    }

}
