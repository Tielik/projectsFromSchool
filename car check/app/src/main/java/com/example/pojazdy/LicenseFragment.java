package com.example.pojazdy;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LicenseFragment extends Fragment {

    private Button compareBTN;
    private TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_license, container, false);

        compareBTN = view.findViewById(R.id.compareButton);
        resultTextView = view.findViewById(R.id.resultTextView);

        compareBTN.setOnClickListener(x -> {
            licenseFromAPI(); //uruchomienie funkcji api po kliknięciu
        });

        return view;
    }

    private void licenseFromAPI() {
        String url = "https://api.cepik.gov.pl/prawa-jazdy";

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        resultTextView.setText("Porównanie licencji prawa jazdy: " + response);
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
