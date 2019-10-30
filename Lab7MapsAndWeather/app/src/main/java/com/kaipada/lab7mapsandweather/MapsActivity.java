package com.kaipada.lab7mapsandweather;

import androidx.fragment.app.FragmentActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker oldMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
	    .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        oldMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

	mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
		@Override
		public void onMapLongClick(LatLng point) {
		    oldMarker.remove();
		    oldMarker = mMap.addMarker(new MarkerOptions()
					       .position(point)
					       .title("You are here")
					       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
		    mMap.moveCamera(CameraUpdateFactory.newLatLng(point));

		    //Toast.makeText(getApplicationContext(),"New marker added " + point.toString(), Toast.LENGTH_LONG).show();
		    getWeatherData(point);
		}
	    });
    }

    public void getWeatherData(LatLng point) {
	RequestQueue queue = Volley.newRequestQueue(this);
	String url ="https://api.openweathermap.org/data/2.5/weather?lat="+point.latitude+"&lon="+point.longitude+"&appid="+"ebd3fa567bfab853d879c4284aba72db";

	// Request a string response from the provided URL.
	StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
							new Response.Listener<String>() {
							    @Override
							    public void onResponse(String response) {
								Toast.makeText(getApplicationContext(),ParseJSON(response), Toast.LENGTH_LONG).show();
							    }
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError error) {
								    Toast.makeText(getApplicationContext(),"Error from weather api", Toast.LENGTH_LONG).show();
								}
							    });

	// Add the request to the RequestQueue.
	queue.add(stringRequest);

    }

    private String ParseJSON(String json){
	String jsonResult = "";

	try {
	    JSONObject JsonObject = new JSONObject(json);
	    String cod = jsonHelperGetString(JsonObject, "cod");

	    if(cod != null){
		if(cod.equals("200")){

		    JSONObject sys = jsonHelperGetJSONObject(JsonObject, "sys");
		    if(sys != null){
			jsonResult += jsonHelperGetString(sys, "country") + "\n";
		    }
		    jsonResult += "\n";

		    JSONObject main = jsonHelperGetJSONObject(JsonObject, "main");
		    if(main != null){
			jsonResult += "temp: " + jsonHelperGetString(main, "temp") + "\n";
			jsonResult += "\n";
		    }

		}else if(cod.equals("404")){
		    String message = jsonHelperGetString(JsonObject, "message");
		    jsonResult += "cod 404: " + message;
		}
	    }else{
		jsonResult += "cod == null\n";
	    }

	} catch (JSONException e) {
	    e.printStackTrace();
	    jsonResult += e.getMessage();
	}

	return jsonResult;
    }

    private String jsonHelperGetString(JSONObject obj, String k){
	String v = null;
	try {
	    v = obj.getString(k);
	} catch (JSONException e) {
	    e.printStackTrace();
	}

	return v;
    }

    private JSONObject jsonHelperGetJSONObject(JSONObject obj, String k){
	JSONObject o = null;

	try {
	    o = obj.getJSONObject(k);
	} catch (JSONException e) {
	    e.printStackTrace();
	}

	return o;
    }

    private JSONArray jsonHelperGetJSONArray(JSONObject obj, String k){
	JSONArray a = null;

	try {
	    a = obj.getJSONArray(k);
	} catch (JSONException e) {
	    e.printStackTrace();
	}

	return a;
    }

}
