package sk.mka.phd.matlab.cons;

/**
 *
 * @author bracek
 * @date Jun 28, 2009
 */
public interface Const {

    public enum BBColums {

        THREE(3),
        FIVE(5);
        private final int columns;

        BBColums(int colums) {
            this.columns = colums;
        }

        public int value() {
            return columns;
        }
    }
    public static String EQUALS_SEPARATOR = "=";
    public static String TABULATOR_SEPARATOR = "\t";

    enum InputParams {

        ALFA, GAMA, MOMENTUM
    };

    enum Topology {

        NEURONS
    };
    /**
     * represents params as alfa, gama, momentum
     */
    public static final String PHONEME_FILENAME = "phoneme.m";
    public static final String VOICED_UNVOICED_LIST = "input/voicedUnvoicedList.txt";
    public static final String INPUT_TABLE_TEX_VM = "input/table_tex_voiced.vm";
    public static final int UNVOICED_REPRESENTATION = 0;
    public static final int VOICED_REPRESENTATION = 1;
    public static final int NUMBER_OF_PHONEME = 61;

    public interface OUTPUT {

        public interface TXT {

            public static final String OUTPUT_TABLE_TEX = "table.tex";
        }
    }

    public interface INPUT {

        public interface VM {

            public static final String TABLE_TEX_VM = "input/table_tex.vm";
        }
        public static final String NEURAL_NETWORK_TOPOLOGY = "input/topology.txt";
        public static final String RESULTS = "input/result.txt";
        public static final String PHONELIST = "input/train_phonelist.txt";
    }

    public interface GENERAL_OUTPUT {

        public static final String OUTPUT_GRAPH = "graph.pdf";
        public static final String OUTPUT_GRAPH_CLASS_A = "graphA.pdf";
        public static final String OUTPUT_GRAPH_CLASS_AB = "graphAB.pdf";
        public static final String OUTPUT_GRAPH_CLASS_BA = "graphBA.pdf";
        public static final String OUTPUT_GRAPH_CLASS_B = "graphB.pdf";
        public static final String OUTPUT_GENERAL_SUCCESS = "output.dat";
        public static final String OUTPUT_SUCCESS_CLASS_AA = "outputA.dat";
        public static final String OUTPUT_SUCCESS_CLASS_AB = "outputAB.dat";
        public static final String OUTPUT_SUCCESS_CLASS_BB = "outputB.dat";
        public static final String OUTPUT_SUCCESS_CLASS_BA = "outputBA.dat";
        public static final String APATTERN_SUCCESS = "ApatternSuccess.pdf";
        public static final String BPATTERN_SUCCESS = "BpatternSuccess.pdf";
        public static final String OUTPUT_REDUCTIONB = "reductionB.pdf";
    }

    public interface EPSTOPDF {

        public static final String EPSTOPDF_GRAPH_A_EPS = "epstopdf graphA.eps";
        public static final String EPSTOPDF_GRAPH_AB_EPS = "epstopdf graphAB.eps";
        public static final String EPSTOPDF_GRAPH_BA_EPS = "epstopdf graphBA.eps";
        public static final String EPSTOPDF_GRAPH_B_EPS = "epstopdf graphB.eps";
        public static final String EPSTOPDF_GRAPH_GENERAL_EPS = "epstopdf graph.eps";
        public static final String APATTERN_SUCCESS_EPS = "epstopdf ApatternSuccess.eps";
        public static final String BPATTERN_SUCCESS_EPS = "epstopdf BpatternSuccess.eps";
        public static final String EPSTOPDF_REDUCTIONB = "epstopdf reductionB.eps";
    }

    public interface GNUPLOT {

        public static final String GNUPLOT_GRAPH_A_GP = "gnuplot graphA.gp";
        public static final String GNUPLOT_GRAPH_AB_GP = "gnuplot graphAB.gp";
        public static final String GNUPLOT_GRAPH_B_GP = "gnuplot graphB.gp";
        public static final String GNUPLOT_GRAPH_BA_GP = "gnuplot graphBA.gp";
        public static final String GNUPLOT_GRAPH_GENERAL_GP = "gnuplot graph.gp";
        public static final String APATTERN_SUCCESS_GP = "gnuplot ApatternSuccess.gp";
        public static final String BPATTERN_SUCCESS_GP = "gnuplot BpatternSuccess.gp";
        public static final String GNUPLOT_REDUCTIONB = "gnuplot reductionB.gp";
    }
}

