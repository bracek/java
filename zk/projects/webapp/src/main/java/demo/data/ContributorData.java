package demo.data;
 
import java.util.Arrays;
import java.util.List;
 
public class ContributorData {
     
    private static final List<String[]> LANG_CONTRIBUTORS = 
        Arrays.asList(new String[][]{
            { "Arabic (ar)", "Ayman Elgharabawy", "iso-8859-6" },
            { "Hungarian (hu)", "Andreas Klein", "iso-8859-2" },
            { "Italian (it)", "Matteo Barbieri", "iso-8859-1" },
            { "Indonesian (id)", "James Liam Supangkat", "iso-8859-1" },
            { "Japanese (ja)", "Poli Lee", " iso-2022-jp" },
            { "Korean (ko)", "Deok-su Lee", "euc-kr" },
            { "Dutch (nl)", "Wido Jansen", "iso-8859-1" },
            { "Portuguese (pt)", "Airton Carrara", "iso-8859-1" },
            { "Brazilian Portugese (pt_BR)", "Airton Carrara", "iso-8859-1" },
            { "Romanian (ro)", "Calin Vaida", "iso-8859-2" },
            { "Russian (ru)", "Denis Yarkovoy", "iso-8859-1" },
            { "French (fr)", "Jêrôme Vergereau", "iso-8859-1" },
            { "Spanish (es)", "Daniel Octavio Ruiz Rodriguez", "iso-8859-1" },
            { "German (de)", "H.-Dirk Schmitt", "iso-8859-1" },
            { "Czech (cs)", "Pavel Mica", "iso-8859-2" },
            { "Catalan (ca)", "Xavier Covas O'Ryan", "iso-8859-1" },
            { "Bulgarian (bg) ", "Boril Yonchev", "iso-8859-5" },
            { "Ukrainian (uk)", "Denis Yarkovoy", "iso-8859-5" },
            { "Turkish (tr)", "Kursat Kurt", "iso-8859-9" },
            { "Swedish (sv)", "Easit AB", "iso-8859-1" },
            { "Slovenian (sl) ", "Ziga", "iso-8859-2" }
    });
     
    public static List<String[]> getContributors() {
        return LANG_CONTRIBUTORS;
    }
     
}
