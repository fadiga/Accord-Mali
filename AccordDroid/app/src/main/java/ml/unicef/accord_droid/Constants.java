package ml.unicef.accord_droid;

/**
 * Created by fad on 21/11/14.
 */


public class Constants {

    public static final String CURRENT_LEVEL = "current_level";
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
    static String l12 = "Bogon";
    static String l13 = "Malinke";
    static String l14 = "Khasonke";
    static String LANGUAGE = "language";
    static String PAGE_NB = "page_nb";

//    Q1
    static String L1Q1 = "Question L1Q1";
    static String L1Q2 = "Question L1Q2";
    static String L1Q3 = "Question L1Q3";
    static String L1Q4 = "Question L1Q4";
    static String L1Q5 = "Question L1Q5";
    static String[] L1 = new String[] { L1Q1, L1Q2, L1Q3, L1Q4, L1Q5};
    static String L2Q1 = "Question L2Q1";
    static String L2Q2 = "Question L2Q2";
    static String L2Q3 = "Question L2Q3";
    static String L2Q4 = "Question L2Q4";
    static String L2Q5 = "Question L2Q5";
    static String[] L2 = new String[] { L2Q1, L2Q2, L2Q3, L2Q4, L2Q5};
    static String L3Q1 = "Question L3Q1";
    static String L3Q2 = "Question L3Q2";
    static String L3Q3 = "Question L3Q3";
    static String L3Q4 = "Question L3Q4";
    static String L3Q5 = "Question L3Q5";
    static String[] L3 = new String[] { L3Q1, L3Q2, L3Q3, L3Q4, L3Q5};
    static String L4Q1 = "Question L4Q1";
    static String L4Q2 = "Question L4Q2";
    static String L4Q3 = "Question L4Q3";
    static String L4Q4 = "Question L4Q4";
    static String L4Q5 = "Question L4Q5";
    static String[] L4 = new String[] { L4Q1, L4Q2, L4Q3, L4Q4, L4Q5};
    static String L5Q1 = "Question L5Q1";
    static String L5Q2 = "Question L5Q2";
    static String L5Q3 = "Question L5Q3";
    static String L5Q4 = "Question L5Q4";
    static String L5Q5 = "Question L5Q5";
    static String[] L5 = new String[] { L5Q1, L5Q2, L5Q3, L5Q4, L5Q5};
    static String L6Q1 = "Question L6Q1";
    static String L6Q2 = "Question L6Q2";
    static String L6Q3 = "Question L6Q3";
    static String L6Q4 = "Question L6Q4";
    static String L6Q5 = "Question L6Q5";
    static String[] L6 = new String[] { L6Q1, L6Q2, L6Q3, L6Q4, L6Q5};


    static String[] languages = new String[] { l1, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14 };


    public static final String getLogTag(String activity) {
        return String.format("Acord-Log-%s", activity);
    }
    public static final String[] getLanguages() {
        return languages;
    }

}
