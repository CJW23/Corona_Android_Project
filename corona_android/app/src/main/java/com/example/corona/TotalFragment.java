package com.example.corona;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TotalFragment extends Fragment {
    private TextView checking;
    private TextView positive;
    private TextView today;
    private TextView totalSum;
    private TextView man;
    private TextView woman;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_total, container, false);
        checking = (TextView) view.findViewById(R.id.checking);
        positive = (TextView) view.findViewById(R.id.positive);
        today = (TextView) view.findViewById(R.id.today);
        totalSum = (TextView) view.findViewById(R.id.total_sum);
        man = (TextView) view.findViewById(R.id.man);
        woman = (TextView) view.findViewById(R.id.woman);
        getTotalData();
        return view;
    }
    private void getTotalData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.125.252.198:8080/")
                .addConverterFactory(GsonConverterFactory.create()
                )
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call<TotalData> call = jsonApi.getTotal();            //api 호출
        //호출 결과
        call.enqueue(new Callback<TotalData>() {
            @Override
            public void onResponse(Call<TotalData> call, Response<TotalData> response) {

                if(!response.isSuccessful()){
                    Log.d("fail", "fail");
                    return;
                }
                TotalData patientDataList = response.body();

                checking.setText("검사중 : " + Integer.toString(patientDataList.getChecking()));
                positive.setText("음성 판정 : " + Integer.toString(patientDataList.getPositive()));
                today.setText("금일 확정자 : " + Integer.toString(patientDataList.getToday()));
                totalSum.setText("총 확진자 : " + Integer.toString(patientDataList.getTotalSum()));
                man.setText("남자 : " + Integer.toString(patientDataList.getMan()));
                woman.setText("여자 : " + Integer.toString(patientDataList.getWoman()));
            }
            @Override
            public void onFailure(Call<TotalData> call, Throwable t) {
                Log.d("fail", "fail");
            }
        });


    }

}
