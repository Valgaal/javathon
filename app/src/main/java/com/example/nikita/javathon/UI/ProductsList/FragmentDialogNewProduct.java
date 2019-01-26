package com.example.nikita.javathon.UI.ProductsList;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.nikita.javathon.R;

import java.io.Serializable;

public class FragmentDialogNewProduct extends DialogFragment{

    private final static String CALLBACK = "DialogCallback";

    private DialogCallback mCallback;

    interface DialogCallback extends Serializable {
        void productAdded(ProductsModel productsModel);
    }

    public static FragmentDialogNewProduct newInstance(DialogCallback callback) {
        FragmentDialogNewProduct fragmentDialog = new FragmentDialogNewProduct();
        Bundle arguments = new Bundle();
        arguments.putSerializable(CALLBACK, callback);
        fragmentDialog.setArguments(arguments);
        return fragmentDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
        {
            mCallback = (DialogCallback) getArguments().getSerializable(CALLBACK);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View rootView = inflater.inflate(R.layout.fragment_dialog_new_product, null);

        final EditText nameEt = rootView.findViewById(R.id.nameEt);
        final EditText countEt = rootView.findViewById(R.id.countEt);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            String name = nameEt.getText().toString();
            String count = countEt.getText().toString();
            ProductsModel productsModel = new ProductsModel();
            mCallback.productAdded(productsModel);
        });

        return builder.create();
    }
}
