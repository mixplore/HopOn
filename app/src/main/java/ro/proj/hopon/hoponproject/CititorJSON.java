package ro.proj.hopon.hoponproject;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea on 04-Dec-15.
 */
public class CititorJSON extends AsyncTask<String,Void,Liste> {


    View textView=null;

    @Override
    protected Liste doInBackground(String... params) {

        Liste xyz=new Liste();

        String listaObiecte="";

        try {
            URL url = new URL(params[0]);
            //URL url = new URL("http://urbo.ro/openmap_transport/load_nid");
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            InputStream is=http.getInputStream();
            BufferedReader reader= new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            String linie="";

            while((linie=reader.readLine())!=null)
            {
                sb.append(linie);
            }



            String jsonText=sb.toString();
            JSONObject jo=new JSONObject(jsonText);
            JSONObject jo2=jo.getJSONObject("markers");
            JSONArray vector =jo2.getJSONArray("markers");
            for(int i=0;i<vector.length();i++)
            {
                JSONObject item=vector.getJSONObject(i);
                xyz.statii.add(item.getString("title"));
                xyz.autobuze.add(item.getString("body"));
                xyz.durate.add("minute");
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return xyz;
    }


    @Override
    protected void onPostExecute(Liste xyz) {
        ListView lv=(ListView)textView;



        CustomAdapter myAdapter=new CustomAdapter(textView.getContext(),xyz.statii,xyz.autobuze,xyz.durate);
        lv.setAdapter(myAdapter);

    }
}
