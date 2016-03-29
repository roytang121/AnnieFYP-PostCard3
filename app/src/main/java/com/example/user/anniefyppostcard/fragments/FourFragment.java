package com.example.user.anniefyppostcard.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.user.anniefyppostcard.R;


/**
 * Created by user on 15/3/2016.
 */

public class FourFragment extends Fragment{
    private static final String LOCAL_FILE = "https://roytang121.github.io/jsvr-demo";
    private WebView myWebView;

    private View rootView;

    public FourFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ar, container, false);

        this.rootView = rootView;

        int permissionCheck1 = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA);

        if (permissionCheck1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.RECORD_AUDIO}, 101);
        } else {
            configureWebView();
        }

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void configureWebView() {

        // Find the web view in our layout xml
        myWebView = (WebView) rootView.findViewById(R.id.webView);

        // Settings
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        // Add JS interface to allow calls from webview to Android
        // code. See below for WebAppInterface class implementation
        myWebView.addJavascriptInterface(new WebAppInterface(this.getActivity()), "Android");
        myWebView.clearCache(true);

        // Set a web view client and a chrome client

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient() {
            // Need to accept permissions to use the camera and audio
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d(getClass().getSimpleName(), "onPermissionRequest");
                getActivity().runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        // Make sure the request is coming from our file
                        // Warning: This check may fail for local files
                        if (request.getOrigin().toString().equals(LOCAL_FILE)) {
                            request.grant(request.getResources());
                        } else {
                            request.deny();
                        }
                    }
                });
            }
        });
        // Load the local HTML file into the webview
        myWebView.loadUrl(LOCAL_FILE);
    }

    // Interface b/w JS and Android code
    private class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        // This function can be called in our JS script now
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            this.configureWebView();
        }
    }
}
