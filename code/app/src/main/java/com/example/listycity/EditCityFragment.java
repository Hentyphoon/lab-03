package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    private static String ARG_CITY_NAME = "city_name";
    private static String ARG_PROVINCE_NAME = "province_name";
    private static String ARG_POSITION = "position";

    interface EditCityDialogListener {
        void editCity(City city, int position);
    }

    private EditCityDialogListener listener;

    public static EditCityFragment newInstance(City city, int position) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY_NAME, city.getName());
        args.putString(ARG_PROVINCE_NAME, city.getProvince());
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EditCityDialogListener) context;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        editCityName.setText(getArguments().getString(ARG_CITY_NAME));
        editProvinceName.setText(getArguments().getString(ARG_PROVINCE_NAME));


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    int position = getArguments().getInt(ARG_POSITION);
                    listener.editCity(new City(cityName, provinceName), position);
                })
                .create();
    }
}
