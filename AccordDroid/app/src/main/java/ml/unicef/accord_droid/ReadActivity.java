package ml.unicef.accord_droid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by fad on 01/11/14.
 */
public class ReadActivity extends Base {

    private static final String TAG = Constants.getLogTag("ReadActivity");
    public PDFView pdfView;
    public float zoomValue = 1;
    public SharedPreferences sharedPrefs;
    public boolean readMode;
    private View modeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_reader);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadPDF();
    }

    public void loadPDF() {
        String language = sharedPrefs.getString(Constants.LANGUAGE, "");
        pdfView = findViewById(R.id.pdfView);
        int pageRead = sharedPrefs.getInt(Constants.PAGE_NB, 0);

        readMode = sharedPrefs.getBoolean(Constants.MODE_READ, true);
        modeButton =  findViewById(R.id.mode_read);
        if (readMode){
            modeButton.setBackgroundResource(R.drawable.sun);
        } else {
            modeButton.setBackgroundResource(R.drawable.moon);
        }

        pdfView.fromAsset(language + ".pdf")
                .spacing(0)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(pageRead)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .nightMode(readMode)
                .pageSnap(true)
                .load();
    }

    public void nextPage(View view) {
        pdfView.jumpTo(pdfView.getCurrentPage() + 1, true);
        final SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(Constants.PAGE_NB, pdfView.getCurrentPage());
        editor.apply();
    }

    public void prevPage(View view) {

        pdfView.jumpTo(pdfView.getCurrentPage() - 1, true);
        final SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(Constants.PAGE_NB, pdfView.getCurrentPage());
        editor.apply();
    }

    public  void changeMode(View view){
        final SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(Constants.MODE_READ, !readMode);
        editor.apply();
        loadPDF();
    }


    public void zoomIn(View view) {
        ++zoomValue;
        pdfView.zoomTo(zoomValue);
        pdfView.loadPages();

    }

    public void zoomOut(View view) {

        if (zoomValue != 1) {
            --zoomValue;
            pdfView.zoomTo(zoomValue);
            pdfView.loadPages();
        }

    }
}


