package ml.unicef.accord_droid;

import android.os.Bundle;
import android.webkit.WebView;

public class AboutActivity extends Base {


    private static WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
//        setupUI();
    }

    public void setupUI() {
    }

}
