package ml.unicef.accord_droid;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class HistoryActivity extends Base {


    private static WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        setupUI();
    }

    private void setupUI() {
        String url = "https://fr-fr.facebook.com/pages/category/Government-Organization/Minist%C3%A8re-de-la-R%C3%A9conciliation-Nationale-1631784637066809/";
        myWebView = findViewById(R.id.webview);
        myWebView.loadUrl(url);
    }
}
