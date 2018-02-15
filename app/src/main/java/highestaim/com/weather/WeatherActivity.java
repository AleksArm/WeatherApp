package highestaim.com.weather;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import highestaim.com.pixomaticprojectweather.R;

public class WeatherActivity extends AppCompatActivity  {

    private Spinner mSpinner;
     TextView celsius;
     TextView weather;
     TextView dayCloudy;
     TextView pressure;
     TextView humidity;
    private final Map<String,String> citys = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        checkInternetConnection();

        citys.put("Armenia","yerevan");
        citys.put("Russia","moscow");
        citys.put("USA","washington");
        citys.put("Georgia","tbilisi");

        weather = findViewById(R.id.weather);
        mSpinner = findViewById(R.id.countryspinner);
        celsius = findViewById(R.id.celsius);
        dayCloudy = findViewById(R.id.datcloudy);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 1:
                        showWeather(citys.get("Armenia"));
                        break;
                    case 2:
                        showWeather(citys.get("Russia"));
                        break;
                    case 3:
                        showWeather(citys.get("USA"));
                        break;
                    case 4:
                        showWeather(citys.get("Georgia"));
                        break;
                    default:
                        weather.setTextSize(25);
                        weather.setText(R.string.nothing);
                        dayCloudy.setText(R.string.nothing);
                        pressure.setText(R.string.nothing);
                        humidity.setText(R.string.nothing);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });



    }

    private void showWeather(String city){
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=00f25f0cf098e001b0c7ccc58f265b6b&units=imperial";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mainObject = response.getJSONObject("main");
                    JSONArray jsonArray = response.getJSONArray("weather");
                    JSONObject object = jsonArray.getJSONObject(0);
                    String temp = String.valueOf(mainObject.getDouble("temp"));
                    String pressureValue = String.valueOf(mainObject.getDouble("pressure"));
                    String humidityValue = String.valueOf(mainObject.getDouble("humidity"));
                    String desc = object.getString("description");

                    dayCloudy.setText(desc);
                    pressure.setText(pressureValue);
                    humidity.setText(humidityValue + " %");

                    double tempD = Double.parseDouble(temp);
                    double centi = (tempD - 32) / 1.8000;
                    centi = Math.round(centi);

                    int tempValue = (int) centi;
                    weather.setTextSize(130);
                    weather.setText(String.valueOf(tempValue));

                } catch (JSONException js) {
                    js.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void checkInternetConnection(){
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr != null ? conMgr.getActiveNetworkInfo() : null;
        if (netInfo == null){
            new AlertDialog.Builder(WeatherActivity.this, android.app.AlertDialog.THEME_HOLO_LIGHT)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setIcon(R.mipmap.ic_exclamation)
                    .setMessage(getResources().getString(R.string.internet_error))
                    .setPositiveButton("Cancel", null)
                    .setNegativeButton(getApplicationContext().getResources().getString(R.string.open_network_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                            startActivity(intent);
                            //get gps
                        }
                    }).show();
        }
    }

}
