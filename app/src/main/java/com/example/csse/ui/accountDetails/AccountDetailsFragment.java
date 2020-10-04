package com.example.csse.ui.accountDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.csse.R;

public class AccountDetailsFragment extends Fragment {

    private AccountDetailsViewModel accountDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountDetailsViewModel =
                ViewModelProviders.of(this).get(AccountDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account_details, container, false);
//        final TextView textView = root.findViewById(R.id.textView2);
//        accountDetailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
