package ro.proj.hopon.hoponproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class Login extends Activity {

    private static Button button_login;
    private static TextView register_clickable;
    private static TextView fgt_pass_clickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_frame);



        button_login = (Button) findViewById(R.id.login_BTN);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText et=(EditText)findViewById(R.id.username_ET);
                final String user=et.getText().toString();
                EditText et2=(EditText)findViewById(R.id.pass_ET);
                final String pass=et2.getText().toString();


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        SoapObject so=new SoapObject("http://tempuri.org/","login");
                        //primul parametru e NAMESPACE si al doilea e numele metodei (login)
                        so.addProperty("username", user);
                        so.addProperty("parola",pass);

                        SoapSerializationEnvelope env= new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.dotNet=true;
                        env.setOutputSoapObject(so);
                        HttpTransportSE site=new HttpTransportSE("url.asmx");
                        //parametrul este url-ul ceva de genul asta: "http://192.168.2.2:8080/LoginWebService/LoginWebService?WSDL"
                        try {
                            site.call("http://tempuri.org/login",env);
                            //aici e namespace+numele metodei (login)
                            int id_user=Integer.parseInt(env.getResponse().toString());
                            if(id_user>=0)
                            {
                                Intent in = new Intent(getApplicationContext(),Homepage.class);
                                in.putExtra("username",user);
                                startActivity(in);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "parola gresita", Toast.LENGTH_LONG);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();





                //if(user.equals("nume cautat in baza de date") si verificare match parola) {
                //Intent intent = new Intent(getApplicationContext(), Homepage.class);
                //intent.putExtra("username",user);
                //startActivity(intent);
                //}
                // else
                // {
                //     Toast t= Toast.makeText(getApplicationContext(),"Username invalid",Toast.LENGTH_LONG);
                //     t.show();
                // }


            }
        });


        register_clickable = (TextView) findViewById(R.id.register_TV);
        register_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(getApplicationContext(),Register.class);
                startActivity(intent1);
            }
        });

        fgt_pass_clickable = (TextView) findViewById(R.id.fgt_pass_TV);
        fgt_pass_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Forgotpass.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_frame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
