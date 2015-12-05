package ro.proj.hopon.hoponproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andreea on 02-Dec-15.
 */
public class CustomAdapter extends ArrayAdapter{

    //private Context context;
    private List<String> statii;
    private List<String> autobuze;
    private List<String> durate;

    public CustomAdapter(Context context, List<String> statii, List<String> autobuze, List<String> durate)
    {
        super(context,R.layout.layout_linie, statii);
        this.statii = statii;
        this.autobuze = autobuze;
        this.durate = durate;
        //this.context=context;
    }

    //aici se stabileste ce va aparea in fiecare textview in functie de position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li= LayoutInflater.from(getContext());
        //(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=li.inflate(R.layout.layout_linie,parent,false);

        TextView statie=(TextView)v.findViewById(R.id.statieTv);
        TextView autobuz=(TextView)v.findViewById(R.id.autobuzeTv);
        TextView durata=(TextView)v.findViewById(R.id.durataTv);

        statie.setText(statii.get(position));
        autobuz.setText(autobuze.get(position));
        durata.setText(durate.get(position));

        return v;

    }
}
