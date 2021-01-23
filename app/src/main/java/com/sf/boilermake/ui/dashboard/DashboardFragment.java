package com.sf.boilermake.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sf.boilermake.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private WebView webview;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
         webview = (WebView)root.findViewById(R.id.webView);
        webview.setWebViewClient(new WebClient());
        WebSettings set = webview.getSettings();


        set.setBuiltInZoomControls(true);
        webview.loadUrl("https://www.randymajors.org/custom-color-coded-maps?sheetid=1gMwsWTMeMchWpwuqdaKZf2YIP2CVDbNVGGg_TVv46m8&areatype=counties&title=My+Color-Coded+Map&color=08224c");

        return root;
    }
    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}