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

public class VinFragment extends Fragment {

    private EditText editTextVehicleId;
    private Button searchButton;
    private TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vin, container, false);

        editTextVehicleId = view.findViewById(R.id.editTextVehicleId);
        resultTextView = view.findViewById(R.id.resultTextView);
        searchButton = view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(x-> {

                searchVehicleIdFromAPI(); // Funkcja searchVehicleIdFromAPI uruchamiana po kliknięciu przycisku

        });

        return view;
    }

    private void searchVehicleIdFromAPI() {
        String vehicleId = editTextVehicleId.getText().toString().trim(); // Pobieranie ID pojazdu

        // Sprawdzanie, czy ID pojazdu zostało wprowadzone
        if (vehicleId.isEmpty()) {
            resultTextView.setText("Wprowadź numer ID pojazdu!");
            return;
        }

        // Zbudowanie URL-a do API z numerem ID pojazdu
        String url = "https://api.cepik.gov.pl/pojazdy/" + vehicleId;

        // Utworzenie zapytania HTTP za pomocą Volley
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Przetwarzanie odpowiedzi z API
                        resultTextView.setText("Informacje o pojeździe: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Obsługa błędu w przypadku niepowodzenia zapytania
                resultTextView.setText("Błąd: " + error.toString());
            }
        });

        // Dodanie zapytania do kolejki Volley
        queue.add(stringRequest);
    }
}
