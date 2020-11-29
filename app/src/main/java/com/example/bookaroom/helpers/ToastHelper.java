package com.example.bookaroom.helpers;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookaroom.R;

public class ToastHelper {
    public enum Severity {
        SUCCESS,
        INFO,
        WARNING,
        ERROR
    }

    public static void showToast(Context context, Severity severity, String message, int length) {
        Toast toast = Toast.makeText(context, message, length);

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout;

        switch(severity) {
            case SUCCESS:
                layout = inflater.inflate(R.layout.confirmation_layout, null);
                break;
            case INFO:
                layout = inflater.inflate(R.layout.info_layout, null);
                break;
            case ERROR:
                layout = inflater.inflate(R.layout.error_layout, null);
                break;
            case WARNING:
                layout = inflater.inflate(R.layout.warning_layout, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown severity");
        }
        ((TextView) layout.findViewById(R.id.message)).setText(message);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 50);
        toast.setView(layout);
        toast.show();
    }

}
