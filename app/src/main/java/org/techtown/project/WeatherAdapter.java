package org.techtown.project;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.project.model.Weather;

import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    RecyclerView recyclerView;

    private ArrayList<TelDTO> mDataset;

    private static RequestQueue requestQueue;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        Button loadButton;
        TextView weatherTextView;
        TextView sunriseTextView;

        MyViewHolder(View v) {
            super(v);

            sunriseTextView = v.findViewById(R.id.tv_sunrise);
            weatherTextView = v.findViewById(R.id.tv_weather);

            loadButton = v.findViewById(R.id.btn_conn);
            loadButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (requestQueue == null) {
                        requestQueue = Volley.newRequestQueue(v.getContext());
                    }

                    String prefix = "https://api.openweathermap.org/data/2.5/onecall";
                    double lat = 37.57;
                    double lon = 126.98;
                    String applicationID = "dbf3abee8d29ca1bd9cefa8675b55c52";
                    String url = prefix;
                    url += "?lat=" + Double.toString(lat);
                    url += "&lon=" + Double.toString(lon);
                    url += "&appid=" + applicationID;
                    url += "&units=metric";

                    StringRequest request = new StringRequest(
                            Request.Method.GET,
                            url, createWeatherResponse(), createErrorListener());
                    request.setShouldCache(false);   // 누를 때마다 새로운 정보를 출력해서 보여줌
                    requestQueue.add(request);
                }
            });
        }

        private Response.Listener<String> createWeatherResponse() {
            return new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Weather weather = null;

                    try {
                        JSONObject obj = new JSONObject(response);
                        weather = (new Gson()).fromJson(response, Weather.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (weather == null) { return; }

                    // Day of Index
                    int dayIndex = 0;
                    Weather.Daily daily = weather.daily.get(dayIndex);
                    weatherTextView.setText("Weather: " + daily.temp.day.toString());
                    sunriseTextView.setText(daily.sunrise.toString());
                }
            };
        }

        private Response.ErrorListener createErrorListener() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {}
            };
        }
    }

    WeatherAdapter(ArrayList<TelDTO> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public WeatherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}