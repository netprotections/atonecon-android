package atone.asiantech.vn.atonelibrary;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import java.lang.ref.WeakReference;

import static android.view.View.GONE;

/**
 * Custom Dialog Fragment includes web-view to load Atone form. The web-view is loaded form html
 * file stored in assets folder.
 */

public class WebViewDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "WebViewDialog";

    private ImageButton mImgClose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_webview, container);
        mImgClose = view.findViewById(R.id.imgBtnCloseDialog);
        mImgClose.setOnClickListener(this);
        return view;
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
        if (javaScriptInterface != null) {
            // Handle form opened
            javaScriptInterface.setFormRenderListener(new OnFormRenderListener() {
                @Override
                public void onFormTransactionOpened() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImgClose.setVisibility(GONE);
                        }
                    });
                }
            });
            webView.addJavascriptInterface(javaScriptInterface, "Android");
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        // Load WebView
        webView.loadUrl("file:///android_asset/atone.html");
        webView.setWebViewClient(new WebViewClient() {
            /**
             * Using this deprecated function because the newer one is used for Android API 24 only
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Patterns.WEB_URL.matcher(url).matches()) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Create instance object.
     *
     * @param javaScriptInterface handle callback from web-view.
     * @return {@link WeakReference<WebViewDialogFragment>} object to avoid leak memory.
     */
    static WeakReference<WebViewDialogFragment> getInstance(JavaScriptInterface javaScriptInterface) {
        WebViewDialogFragment webViewFragmentDialog = new WebViewDialogFragment();
        WeakReference<WebViewDialogFragment> webViewDialogFragmentWeakReference =
                new WeakReference<>(webViewFragmentDialog);
        // Supply javaScriptInterface input as an argument.
        Bundle bundle = new Bundle();
        bundle.putParcelable("javaScriptInterface", javaScriptInterface);
        webViewFragmentDialog.setArguments(bundle);

        return webViewDialogFragmentWeakReference;
    }

    @Override
    public void onClick(View view) {
        if (AtonePay.getInstance().getDialogFragment() != null
                && AtonePay.getInstance().getDialogFragment().get() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.dialog_title_confirm_quit_payment);
            builder.setCancelable(false)
                    .setNegativeButton(R.string.button_dialog_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int id) {
                            AtonePay.getInstance().getDialogFragment().get().dismiss();
                        }
                    })
                    .setPositiveButton(R.string.button_dialog_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        AtonePay.sIsDialogStarted = false;
    }
}
