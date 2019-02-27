package ml.unicef.accord_droid;

/**
 * Created by fad on 21/11/14.
 */


public class Constants {

    public static final String CURRENT_LEVEL = "current_level";
    public static final String SONG = "song_on";
    public static final String LEVEL = "level";
    public static final String share = "https://play.google.com/store/apps/details?id=fadcorp.mprice";

    static String MODE_READ = "read_mode";
    static String l1 = "Français";
    static String l3 = "Bambara";
    static String l4 = "Fulfulde";
    static String l5 = "Songhoi";
    static String l6 = "Tamacheq";
    static String l7 = "Bomu";
    static String l8 = "Mamara";
    static String l9 = "Shennara";
    static String l10 = "Soninke";
    static String l11 = "Bozo";
    static String l12 = "Dogon";
    static String l13 = "Malinke";
    static String l14 = "Khasonke";
    static String LANGUAGE = "language";
    static String PAGE_NB = "page_nb";
    static int QTUPE1 = 1;
    static int QTUPE2 = 2;


    static String[] languages = new String[] { l1, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14 };


    public static final String getLogTag(String activity) {
        return String.format("Acord-Log-%s", activity);
    }
    public static final String[] getLanguages() {
        return languages;
    }

}
