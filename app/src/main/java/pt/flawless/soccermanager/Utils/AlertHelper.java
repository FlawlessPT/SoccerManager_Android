package pt.flawless.soccermanager.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import pt.flawless.soccermanager.Utils.Alert.AlertType;

public class AlertHelper {
    private Context context;
    private String message;
    private AlertType alertType;

    public AlertHelper(Context context) {
        this.context = context;
    }

    public AlertHelper(Context context, String message, AlertType alertType) {
        this.context = context;
        this.message = message;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public void show() {
        if (alertType == AlertType.OK) {
            new AlertDialog.Builder(this.context)
                    .setTitle("Aviso: ")
                    .setCancelable(true)
                    .setMessage(this.message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }
}
