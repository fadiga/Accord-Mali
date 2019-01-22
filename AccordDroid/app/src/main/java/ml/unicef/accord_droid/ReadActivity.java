package ml.unicef.accord_droid;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_reader);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String language = sharedPrefs.getString(Constants.LANGUAGE, "");
        int pageRead = sharedPrefs.getInt(Constants.PAGE_NB, 0);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        language = language + ".pdf";
        pdfView.fromAsset(language)
                .spacing(10)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(pageRead)
//                .onPageChange(true)
                // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
                // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
//                .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                .onPageChange(onPageChangeListener)
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(onRenderListener) // called after document is rendered for the first time
                // called on single tap, return true if handled, false to toggle scroll handle visibility
//                .onTap(onTapListener)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .invalidPageColor(Color.BLUE) // color
                .load();
        pdfView.findViewWithTag("");
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

    public void search(View view){
        pdfView.findViewWithTag();

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


