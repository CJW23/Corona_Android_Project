package com.example.corona;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientFragment extends Fragment {
    RecyclerView recyclerPatient;
    Retrofit retrofit;
    JsonApi jsonApi;
    Call<List<PatientData>> call;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_patient, container, false);
        getPatientData();

        return view;
    }

    private void getPatientData() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.9:8080/")
                .addConverterFactory(GsonConverterFactory.create()
                )
                .build();
        jsonApi = retrofit.create(JsonApi.class);
        call = jsonApi.getPatient();            //api 호출
        //호출 결과
        call.enqueue(new Callback<List<PatientData>>() {
            @Override
            public void onResponse(Call<List<PatientData>> call, Response<List<PatientData>> response) {

                if(!response.isSuccessful()){
                    Log.d("fail", "fail");
                    return;
                }
                List<PatientData> patientDataList = response.body();

                Log.d("asdasdasd", Integer.toString(patientDataList.size()));
                RecyclerView.Adapter patientAdapter = new PatientListAdapter(patientDataList, getActivity());
                recyclerPatient = view.findViewById(R.id.recycler_patient);
                recyclerPatient.setHasFixedSize(true);
                recyclerPatient.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerPatient.setAdapter(patientAdapter);
            }
            @Override
            public void onFailure(Call<List<PatientData>> call, Throwable t) {
                Log.d("fail", "fail");
            }
        });
    }

}
