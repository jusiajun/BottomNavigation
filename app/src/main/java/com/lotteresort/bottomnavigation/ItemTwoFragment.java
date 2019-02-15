package com.lotteresort.bottomnavigation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemTwoFragment extends Fragment {
    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    TextInputLayout errorInputLayout, customErrorInputLayout;
    TextInputEditText errorEditText, customErrorEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_item_two, container, false);

        errorEditText = (TextInputEditText) v.findViewById(R.id.errorEditText);
        errorInputLayout = (TextInputLayout) v.findViewById(R.id.errorInputLayout);

        customErrorEditText = (TextInputEditText) v.findViewById(R.id.customErrorEditText);
        customErrorInputLayout = (TextInputLayout) v.findViewById(R.id.customErrorInputLayout);

        errorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > errorInputLayout.getCounterMaxLength())
                    errorInputLayout.setError("Max character length is " + errorInputLayout.getCounterMaxLength());
                else
                    errorInputLayout.setError(null);

            }
        });

        customErrorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > customErrorInputLayout.getCounterMaxLength())
                    customErrorInputLayout.setError("Max character length is " + customErrorInputLayout.getCounterMaxLength());
                else
                    customErrorInputLayout.setError(null);

            }
        });

        return v;
    }
}
