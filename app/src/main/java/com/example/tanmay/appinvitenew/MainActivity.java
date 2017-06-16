package com.example.tanmay.appinvitenew;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.facebook.internal.CallbackManagerImpl.RequestCodeOffset.AppInvite;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();

        FacebookCallback<AppInviteDialog.Result> facebookCallback = new FacebookCallback<AppInviteDialog.Result>() {
            @Override
            public void onSuccess(AppInviteDialog.Result result) {
                Log.i("TAG", "MainACtivity, InviteCallback - SUCCESS!" + result.getData());
            }

            @Override
            public void onCancel() {
                Log.i("TAG", "MainACtivity, InviteCallback - CANCEL!");
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("TAG", "MainACtivity, InviteCallback - ERROR! " + e.getMessage());
            }
        };

        AppInviteDialog appInviteDialog = new AppInviteDialog(this);
        if (appInviteDialog.canShow()) {
            AppInviteContent.Builder content = new AppInviteContent.Builder();
            content.setApplinkUrl("https://fb.me/1349472831832219");
            content.setPreviewImageUrl("https://yt3.ggpht.com/-CRHSauniQ94/AAAAAAAAAAI/AAAAAAAAAAA/6_c-kqZZzeg/s900-c-k-no-mo-rj-c0xffffff/photo.jpg");
            AppInviteContent appInviteContent = content.build();
            appInviteDialog.registerCallback(callbackManager, facebookCallback);
            appInviteDialog.show(this, appInviteContent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}


