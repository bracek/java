package sk.mka.phd.matlab.cons;

/**
 *
 * @author bracek
 * @date Jun 28, 2009
 */
public interface Const {

     static final String PHONEME_FILENAME = "phoneme.m";
     static final String VOICED_UNVOICED_LIST = "input/voicedUnvoicedList.txt";
     static final String INPUT_TABLE_TEX_VM = "input/table_tex_voiced.vm";
     static final int UNVOICED_REPRESENTATION = 0;
     static final int VOICED_REPRESENTATION = 1;
     static final int NUMBER_OF_PHONEME = 61;

    public interface INPUT {

        public interface VM {

             static final String TEMPLATE_AVERAGE_LIST_VOICED_EXPERIMENTS = "input/experiments/voiced/table_list_experiments.vm";
             static final String TEMPLATE_AVERAGE_LIST_UNVOICED_EXPERIMENTS = "input/experiments/unvoiced/table_list_experiments.vm";
             static final String TABLE_TEX_VM = "input/table_tex.vm";
        }
         static final String NEURAL_NETWORK_TOPOLOGY = "input/topology.txt";
         static final String RESULTS = "input/result.txt";
         static final String PHONELIST = "input/train_phonelist.txt";
         static final String MFC_PARAMS = "input/input_params.txt";
         static final String PATTERN_EPOCHS = "input/pattern.txt";
    }

    public interface GENERAL_OUTPUT {

         static final String OUTPUT_GRAPH = "graph.pdf";
         static final String OUTPUT_GRAPH_CLASS_A = "graphA.pdf";
         static final String OUTPUT_GRAPH_CLASS_B = "graphB.pdf";
         static final String OUTPUT_GENERAL_SUCCESS = "output.dat";
         static final String OUTPUT_SUCCESS_CLASS_A = "outputA.dat";
         static final String OUTPUT_SUCCESS_CLASS_B = "outputB.dat";
         static final String OUTPUT_TABLE_TEX = "table.tex";
         static final String APATTERN_SUCCESS = "ApatternSuccess.pdf";
         static final String BPATTERN_SUCCESS = "BpatternSuccess.pdf";
         static final String OUTPUT_REDUCTIONB = "reductionB.pdf";
    }

    public interface EPSTOPDF {

         static final String EPSTOPDF_GRAPH_A_EPS = "epstopdf graphA.eps";
         static final String EPSTOPDF_GRAPH_B_EPS = "epstopdf graphB.eps";
         static final String EPSTOPDF_GRAPH_GENERAL_EPS = "epstopdf graph.eps";
         static final String APATTERN_SUCCESS_EPS = "epstopdf ApatternSuccess.eps";
         static final String BPATTERN_SUCCESS_EPS = "epstopdf BpatternSuccess.eps";
         static final String EPSTOPDF_REDUCTIONB = "epstopdf reductionB.eps";
    }

    public interface GNUPLOT {

         static final String GNUPLOT_GRAPH_A_GP = "gnuplot graphA.gp";
         static final String GNUPLOT_GRAPH_B_GP = "gnuplot graphB.gp";
         static final String GNUPLOT_GRAPH_GENERAL_GP = "gnuplot graph.gp";
         static final String APATTERN_SUCCESS_GP = "gnuplot ApatternSuccess.gp";
         static final String BPATTERN_SUCCESS_GP = "gnuplot BpatternSuccess.gp";
         static final String GNUPLOT_REDUCTIONB = "gnuplot reductionB.gp";
    }
}

