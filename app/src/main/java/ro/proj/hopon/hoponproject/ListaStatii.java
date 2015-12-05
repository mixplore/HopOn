package ro.proj.hopon.hoponproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaStatii extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_statii);

        //plan: facem jumate harta jumate rute recente or smth like that...plus ceva sus de cautare :-??

//        MapFragment mMapFragment = MapFragment.newInstance();
//        FragmentTransaction fragmentTransaction =
//                getFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.my_container, mMapFragment);
//        fragmentTransaction.commit();

//        List<String> statii=new ArrayList<String>();
//        statii.add("Romana");
//        statii.add("Universitate");
//        statii.add("Politehnica");
//
//        List<String> autobuze=new ArrayList<String>();
//        autobuze.add("301,300,381");
//        autobuze.add("381,69,135");
//        autobuze.add("601,66");
//
//        List<String> durate=new ArrayList<String>(); //asteptare aproximativa...
//        durate.add("10 min");
//        durate.add("5 min");
//        durate.add("9 min");
//
//        ListView lv=(ListView)this.findViewById(R.id.listViewStatii);
//        CustomAdapter myAdapter=new CustomAdapter(this,statii,autobuze,durate);
//        lv.setAdapter(myAdapter);

        parsareJSON(findViewById(R.id.listViewStatii));

    }


    public void parsareJSON(View view) {


        final ListView lv=(ListView)findViewById(R.id.listViewStatii);
        final List<String> statii=new ArrayList<String>();
        final List<String> autobuze=new ArrayList<String>();
        final List<String> durate=new ArrayList<String>();

        CititorJSON ob=new CititorJSON(){
            @Override
            protected void onPostExecute(String s) {

                if(s!="") {
                    String[] linii=s.split("\n");
                    if(linii!=null) {
                    for (int i = 0; i < linii.length; i++) {
                        String[] obiecte = linii[i].split("-");

                            statii.add(obiecte[0]);
                            //autobuze.add(obiecte[1]);
                            autobuze.add("300");
                            durate.add("3 minute");
                        }
                    }
                }

                CustomAdapter myAdapter=new CustomAdapter(getApplicationContext(),statii,autobuze,durate);
                lv.setAdapter(myAdapter);

            }
        };
        ob.execute("http://urbo.ro/openmap_transport/load_nid");
        //ob.execute(findViewById(R.id.txtView1));
    }

}
