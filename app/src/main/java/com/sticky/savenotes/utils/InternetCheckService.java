package com.sticky.savenotes.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.sticky.savenotes.R;
import com.sticky.savenotes.utils.NetworkUtil;

public class InternetCheckService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Integer result = NetworkUtil.getNetworkState(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.internet_alert_dialog,null);
        builder.setView(view);

        Button button = view.findViewById(R.id.retry_btn);

        AlertDialog alertDialog = builder.setCancelable(false).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Toast;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onReceive(context,intent);
            }
        });

        switch (result){
            case 0:
                alertDialog.show();
                break;
            case 1:
            case 2:
                alertDialog.dismiss();
                break;
        }
    }
}
