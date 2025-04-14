package com.example.pojazdy;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class VehiclesFragment extends Fragment {
    private Button searchBTN;
    private TextView resultTextView;
    private EditText editTextRegion, editTextBrand, editTextYear, editTextModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicles, container, false);

        editTextRegion = view.findViewById(R.id.editTextRegion);
        editTextBrand = view.findViewById(R.id.editTextBrand);
        editTextYear = view.findViewById(R.id.editTextYear);
        editTextModel = view.findViewById(R.id.editTextModel);
        searchBTN = view.findViewById(R.id.searchButton);
        resultTextView = view.findViewById(R.id.resultTextView);

        searchBTN.setOnClickListener(x ->  {

                searchVehiclesFromAPI();

        });

        return view;
    }

    private void searchVehiclesFromAPI() {
        String region = editTextRegion.getText().toString().trim();
        String brand = editTextBrand.getText().toString().trim();
        String year = editTextYear.getText().toString().trim();
        String model = editTextModel.getText().toString().trim();


        // Tworzenie URL do zapytania
        String url = "https://api.cepik.gov.pl/pojazdy?" +
                "wojewodztwo=" + region +
                "&data-od=" + year + "0101" +
                "&data-do=" + year + "1231" +
                "&filter[marka]=" + brand +
                "&filter[model]=" + model;

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Tworzenie zapytania HTTP
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        resultTextView.setText("Response: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error: " + error.toString());
            }
        });

        // Dodanie zapytania do kolejki
        queue.add(stringRequest);
    }


}