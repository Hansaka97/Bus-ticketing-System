package com.example.csse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csse.R;
import com.example.csse.model.PassengerLayoutModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {


    private List<PassengerLayoutModel> modelList;

    public Adapter(List<PassengerLayoutModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.passenger_in_bus_layout, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

        String name = "";
        String seatCount = modelList.get(position).getNoOfSeats();
        String totalFare  = modelList.get(position).getTotalFare();
        String payMethod = modelList.get(position).getPaymentMethod();
        String isPay  = modelList.get(position).getIsPay();

        viewholder.SetData(name,seatCount,totalFare,payMethod,isPay);
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewSeatCount;
        private TextView textViewTotalFare;
        private TextView textViewpayMethod;
        private ImageView imageView7;
        private Button buttonconfirmPament;




        public Viewholder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewSeatCount = itemView.findViewById(R.id.textViewSeatCount);
            textViewTotalFare = itemView.findViewById(R.id.textViewTotalFare);
            textViewpayMethod = itemView.findViewById(R.id.textViewpayMethod);
            imageView7 = itemView.findViewById(R.id.imageView7);
            buttonconfirmPament = itemView.findViewById(R.id.buttonconfirmPament);
            imageView7.setVisibility(View.GONE);
            buttonconfirmPament.setVisibility(View.GONE);
        }

        private void SetData(String name, String seatCount, String totalFare, String payMethod, String isPay){

            textViewName.setText(name);
            textViewSeatCount.setText(seatCount);
            textViewTotalFare.setText(totalFare);
            textViewpayMethod.setText(payMethod);

            if(isPay.equals("YES")){
                imageView7.setVisibility(View.VISIBLE);
            } else {
                buttonconfirmPament.setVisibility(View.VISIBLE);
            }

        }

    }

}
