package ro.proj.hopon.hoponproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends Activity {

    EditText username, email, password;
    Button register;
    RequestQueue requestQueue;
    String registerURL = "http://smartphp.byethost14.com/htdocs/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_frame);

        username = (EditText) findViewById(R.id.usr_reg_ET);
        email = (EditText) findViewById(R.id.email_reg_ET);
        password = (EditText) findViewById(R.id.pass_reg_ET);
//aici trebuie sa faci findviewbyid pt butonul register
        //cum ai facut cu edittexturile de mai sus
        //aaaa stai sa fac
         register=(Button)findViewById(R.id.reg_BTN);
        //imediat sa verific
        //pot s ama mai uit prin cod sa vad si eu cum ai facut regsiterul?


        requestQueue = Volley.newRequestQueue(getApplicationContext());


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, registerURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("eroare:",error.getMessage());
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("username", username.getText().toString());
                        parameters.put("email", email.getText().toString());
                        parameters.put("password", password.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_frame, menu);
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