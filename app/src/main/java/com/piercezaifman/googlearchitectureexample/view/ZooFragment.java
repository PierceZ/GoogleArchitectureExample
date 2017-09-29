package com.piercezaifman.googlearchitectureexample.view;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.piercezaifman.googlearchitectureexample.R;
import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.response.Response;
import com.piercezaifman.googlearchitectureexample.viewmodel.ZooFragmentViewModel;

public class ZooFragment extends AppCompatDialogFragment {

    private static final String ARGS_ZOO_ID = "ARGS_ZOO_ID";

    private EditText mNameView;
    private Button mCancelButton;
    private Button mSaveButton;
    private ProgressBar mProgressBar;

    private Long mZooId;
    private Zoo mZoo;

    public static ZooFragment newInstance(long id) {
        ZooFragment fragment = new ZooFragment();
        Bundle args = new Bundle();
        args.putLong(ARGS_ZOO_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public static ZooFragment newInstance() {
        ZooFragment fragment = new ZooFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args.containsKey(ARGS_ZOO_ID)) {
            mZooId = args.getLong(ARGS_ZOO_ID);
        }

        // if editing a zoo, we need to load it
        if (mZooId != null) {
            ZooFragmentViewModel viewModel = ViewModelProviders.of(this).get(ZooFragmentViewModel.class);
            viewModel.loadZoo(mZooId);
            viewModel.getZoo().observe(this, zoo -> {
                if (zoo != null) {
                    mZoo = zoo;
                    mNameView.setText(zoo.getName());
                }
            });
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_zoo, null);
        builder.setView(v);
        mNameView = v.findViewById(R.id.fragment_zoo_edittext_name);
        mCancelButton = v.findViewById(R.id.fragment_zoo_button_cancel);
        mSaveButton = v.findViewById(R.id.fragment_zoo_button_save);
        mProgressBar = v.findViewById(R.id.fragment_zoo_progressbar);

        setupViews();

        return builder.create();
    }

    private void showProgressBar(boolean show) {
        mSaveButton.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private boolean validate() {
        return mNameView.getText().toString().trim().length() > 0;
    }

    private void setupViews() {
        ZooFragmentViewModel viewModel = ViewModelProviders.of(this).get(ZooFragmentViewModel.class);
        viewModel.getZooUpdateResponse().observe(this, response -> {
            if (response != null) {
                switch (response.getStatus()) {
                    case Response.STATUS_LOADING:
                        showProgressBar(true);
                        break;
                    case Response.STATUS_SUCCESS:
                        dismiss();
                        break;
                    case Response.STATUS_FAIL:
                        showProgressBar(false);
                        Toast.makeText(getContext(), response.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        mCancelButton.setOnClickListener(view -> dismiss());

        mSaveButton.setOnClickListener(view -> {
            if (validate()) {
                showProgressBar(true);
                if (mZooId == null) {
                    viewModel.addZoo(mNameView.getText().toString());
                } else if (mZoo != null) {
                    mZoo.setName(mNameView.getText().toString());
                    viewModel.updateZoo(mZoo);
                }
            }
        });
    }
}
