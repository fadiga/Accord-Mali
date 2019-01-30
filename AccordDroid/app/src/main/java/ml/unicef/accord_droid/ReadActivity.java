package ml.unicef.accord_droid;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by fad on 01/11/14.
 */
public class ReadActivity extends Base {

    private static final String TAG = Constants.getLogTag("ReadActivity");
    public PDFView pdfView;
    public float zoomValue = 1;
    public SharedPreferences sharedPrefs;
    public Button bttSearch;
    public TextInputEditText textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_reader);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String language = sharedPrefs.getString(Constants.LANGUAGE, "");
        Boolean readMode = sharedPrefs.getBoolean(Constants.MODE_READ, false);
        int pageRead = sharedPrefs.getInt(Constants.PAGE_NB, 0);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        language = language + ".pdf";
        pdfView.fromAsset(language)
                .spacing(10)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(pageRead)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .nightMode(readMode)
                .enableAntialiasing(true)
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

    public void searchInPage(View view){

        final Dialog searchDialog = new Dialog(ReadActivity.this);
        searchDialog.setContentView(R.layout.reach_dialogue);
//        settingdialog.setTitle("Paramètre de langue");
        searchDialog.show();
        bttSearch =  searchDialog.findViewById(R.id.bttSearch);
        bttSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textSearch = searchDialog.findViewById(R.id.testSearch);
                Log.i(TAG, textSearch.getText().toString());
//                pdfView.SearchText()
                pdfView.findViewWithTag(textSearch.getText().toString());
                searchDialog.dismiss();

            }
        });


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


