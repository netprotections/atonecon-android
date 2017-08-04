package atone.asiantech.vn.atonelibrary;

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

/**
 * Copyright by Gio.
 * Created on 8/3/2017.
 */

public class WebViewDialogFragment extends DialogFragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_webview, container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        final WebView webView = view.findViewById(R.id.webView);
        JavaScriptInterface javaScriptInterface = getArguments().getParcelable("javaScriptInterface");
        webView.addJavascriptInterface(javaScriptInterface, "Android");
        webView.getSettings().setJavaScriptEnabled(true);
        // Load WebView
        webView.loadUrl("file:///android_asset/atonedev.html");
        webView.setVisibility(View.INVISIBLE);  // To show ProgressBar
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    webView.setVisibility(View.VISIBLE);
                }
            }
        });
        ImageButton imgBtn = view.findViewById(R.id.imgBtnCloseDialog);
        imgBtn.setOnClickListener(this);
    }

    static DialogFragment getInstance(JavaScriptInterface javaScriptInterface) {
        WebViewDialogFragment webViewFragmentDialog = new WebViewDialogFragment();
        // Supply javaScriptInterface input as an argument.
        Bundle bundle = new Bundle();
        bundle.putParcelable("javaScriptInterface", javaScriptInterface);
        webViewFragmentDialog.setArguments(bundle);

        return webViewFragmentDialog;
    }

    @Override
    public void onClick(View view) {
        if (AtonePay.getInstance().getDialogFragment() != null) {
            AtonePay.getInstance().getDialogFragment().dismiss();
        }
    }
}
