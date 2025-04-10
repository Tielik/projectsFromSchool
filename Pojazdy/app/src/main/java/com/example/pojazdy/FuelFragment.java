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

public class FuelFragment extends Fragment {

    private EditText editTextRegion, editTextFuelType, editTextYear;
    private Button searchBTN;
    private TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fuel, container, false);

        editTextRegion = view.findViewById(R.id.editTextRegion);
        editTextFuelType = view.findViewById(R.id.editTextFuelType);
        editTextYear = view.findViewById(R.id.editTextYear);
        searchBTN = view.findViewById(R.id.searchButton);
        resultTextView = view.findViewById(R.id.resultTextView);

        searchBTN.setOnClickListener(v -> searchFuelFromAPI());

        return view;
    }

    private void searchFuelFromAPI() {
        String region = editTextRegion.getText().toString().trim();
        String fuelType = editTextFuelType.getText().toString().trim();
        String year = editTextYear.getText().toString().trim();

        if (region.isEmpty() || fuelType.isEmpty() || year.isEmpty()) {
            resultTextView.setText("Wprowadź wszystkie dane!");
            return;
        }
        // Tworzenie URL z odpowiednimi parametrami
        String url = "https://api.cepik.gov.pl/pojazdy?" +
                "wojewodztwo=" + region +
                "&rodzaj-paliwa=" + fuelType +
                "&data-od=" + year + "0101" +
                "&data-do=" + year + "1231";


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        resultTextView.setText("Liczba zarejestrowanych pojazdów: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Błąd: " + error.toString());
            }
        });

        queue.add(stringRequest);
    }

}
