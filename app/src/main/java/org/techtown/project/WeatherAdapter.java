package org.techtown.project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    private ArrayList<TelDTO> mDataset;
    Button btn_conn;
    TextView tv_location;

    static RequestQueue requestQueue;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView tv_location;
        Button button;
        public MyViewHolder(View v) {
            super(v);
//              textView = v.findViewById(R.id.tv_today);

            button = v.findViewById(R.id.btn_conn);
            tv_location = v.findViewById(R.id.tv_location);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public WeatherAdapter(ArrayList<TelDTO> myDataset) {
        mDataset = myDataset;
    }
//    public TextView textView;
//    Button button;
    // Create new views (invoked by the layout manager)

    @Override
    public WeatherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        btn_conn = v.findViewById(R.id.btn_conn);
         tv_location = v.findViewById(R.id.tv_location);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tv_location.setText(mDataset.get(position).getName());

        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(v.getContext());
                    //requestQueue = Volley.newRequestQueue(getApplicationContext());
                }

                String url = "https://api.openweathermap.org/data/2.5/onecall?lat=37.57&lon=126.98&appid=dbf3abee8d29ca1bd9cefa8675b55c52&units=metric";

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("hhd",""+(response));

                        try {
                            JSONObject obj = new JSONObject(response);

                            String timezones = obj.getString("timezone");
                            Log.v("hhd",""+(timezones));




                            JSONObject current = obj.getJSONObject("current");
                            int temp = current.getInt("temp");
                            Log.v("hhd",""+(temp));

                            int sunrise = current.getInt("sunrise");
                            Log.v("hhd",""+(sunrise));

                            int sunset = current.getInt("sunset");
                            Log.v("hhd",""+(sunset));

                            double wind_speed = current.getDouble("wind_speed");
                            Log.v("hhd",""+(wind_speed));

                            int clouds = current.getInt("clouds");
                            Log.v("hhd",""+(clouds));



                            JSONArray daily = obj.getJSONArray("daily");
                            JSONObject one = (JSONObject)daily.get(1);
                            JSONObject temp2 = (JSONObject)one.getJSONObject("temp");
                            double day = temp2.getDouble("day");

                            Log.v("hhd",""+(day));


                            tv_location.append("day  : " + Double.toString(day)   + "\n");




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Gson gson = new Gson();
//                        WeatherList WeatherList = gson.fromJson(response, WeatherList.class);

                        // tv_content.append(response + "");

                        //tv_tem.append("wind_speed : ");
                        //Log.v("hhd",""+(WeatherList.weatherListResult == null));
                        //Log.v("hhd",""+WeatherList.weatherListResult.currently.get(0).wind_speed);
                        //tv_tem.append("wind_speed : " + WeatherList.weatherListResult.currently.get(0).wind_speed + "\n");


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_location.append("에러");
                    }
                });
                request.setShouldCache(false);   // 누를 때마다 새로운 정보를 출력해서 보여줌
                requestQueue.add(request);
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();

    }
}