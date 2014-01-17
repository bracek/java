package sk.mka.phd.matlab.cons;

/**
 *
 * @author bracek
 * @date Jun 28, 2009
 */
public interface Const {

     enum BBColums {

        THREE(3),
        FIVE(5);
        private final int columns;

        BBColums(int colums) {
            this.columns = colums;
        }

         int value() {
            return columns;
        }
    }
     static String EQUALS_SEPARATOR = "=";
     static String TABULATOR_SEPARATOR = "\t";

    enum InputParams {

        ALFA, GAMA, MOMENTUM
    };

    enum Topology {

        NEURONS
    };
    /**
     * represents params as alfa, gama, momentum
     */
     static final String PHONEME_FILENAME = "phoneme.m";
     static final String VOICED_UNVOICED_LIST = "input/voicedUnvoicedList.txt";
     static final String INPUT_TABLE_TEX_VM = "input/table_tex_voiced.vm";
     static final int UNVOICED_REPRESENTATION = 0;
     static final int VOICED_REPRESENTATION = 1;
     static final int NUMBER_OF_PHONEME = 61;

    public interface OUTPUT {

        public interface TXT {

             static final String OUTPUT_TABLE_TEX = "table.tex";
        }
    }

    public interface INPUT {

        public interface VM {

             static final String TABLE_TEX_VM = "input/table_tex.vm";
        }
         static final String NEURAL_NETWORK_TOPOLOGY = "input/topology.txt";
         static final String RESULTS = "input/result.txt";
         static final String PHONELIST = "input/train_phonelist.txt";
    }

    public interface GENERAL_OUTPUT {

         static final String OUTPUT_GRAPH = "graph.pdf";
         static final String OUTPUT_GRAPH_CLASS_A = "graphA.pdf";
         static final String OUTPUT_GRAPH_CLASS_AB = "graphAB.pdf";
         static final String OUTPUT_GRAPH_CLASS_BA = "graphBA.pdf";
         static final String OUTPUT_GRAPH_CLASS_B = "graphB.pdf";
         static final String OUTPUT_GENERAL_SUCCESS = "output.dat";
         static final String OUTPUT_SUCCESS_CLASS_AA = "outputA.dat";
         static final String OUTPUT_SUCCESS_CLASS_AB = "outputAB.dat";
         static final String OUTPUT_SUCCESS_CLASS_BB = "outputB.dat";
         static final String OUTPUT_SUCCESS_CLASS_BA = "outputBA.dat";
         static final String APATTERN_SUCCESS = "ApatternSuccess.pdf";
         static final String BPATTERN_SUCCESS = "BpatternSuccess.pdf";
         static final String OUTPUT_REDUCTIONB = "reductionB.pdf";
    }

    public interface EPSTOPDF {

         static final String EPSTOPDF_GRAPH_A_EPS = "epstopdf graphA.eps";
         static final String EPSTOPDF_GRAPH_AB_EPS = "epstopdf graphAB.eps";
         static final String EPSTOPDF_GRAPH_BA_EPS = "epstopdf graphBA.eps";
         static final String EPSTOPDF_GRAPH_B_EPS = "epstopdf graphB.eps";
         static final String EPSTOPDF_GRAPH_GENERAL_EPS = "epstopdf graph.eps";
         static final String APATTERN_SUCCESS_EPS = "epstopdf ApatternSuccess.eps";
         static final String BPATTERN_SUCCESS_EPS = "epstopdf BpatternSuccess.eps";
         static final String EPSTOPDF_REDUCTIONB = "epstopdf reductionB.eps";
    }

    public interface GNUPLOT {

         static final String GNUPLOT_GRAPH_A_GP = "gnuplot graphA.gp";
         static final String GNUPLOT_GRAPH_AB_GP = "gnuplot graphAB.gp";
         static final String GNUPLOT_GRAPH_B_GP = "gnuplot graphB.gp";
         static final String GNUPLOT_GRAPH_BA_GP = "gnuplot graphBA.gp";
         static final String GNUPLOT_GRAPH_GENERAL_GP = "gnuplot graph.gp";
         static final String APATTERN_SUCCESS_GP = "gnuplot ApatternSuccess.gp";
         static final String BPATTERN_SUCCESS_GP = "gnuplot BpatternSuccess.gp";
         static final String GNUPLOT_REDUCTIONB = "gnuplot reductionB.gp";
    }
}

