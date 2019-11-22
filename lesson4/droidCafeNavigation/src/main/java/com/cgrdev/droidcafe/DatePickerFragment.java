package com.cgrdev.droidcafe;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Get current date as default date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create new instance of DatePickerDialog and return
        return new DatePickerDialog(getActivity(),  this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        // Create the message to show in toast
        String year_string = Integer.toString(year);
        String month_string = Integer.toString(month);
        String day_string = Integer.toString(dayOfMonth);
        String toastMessage = getString(R.string.date_chosen) + day_string + "/" + month_string + "/" + year_string;

        // Send the message to the toast
        OrderActivity activity = (OrderActivity) getActivity();
        activity.displayToast(toastMessage);
    }
}
