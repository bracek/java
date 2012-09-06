package cz.heroult.pavel.bohatstvoKnihoven.kap16;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.math.*;
import java.awt.*;
import java.awt.event.*;

public class BigDecimalGUIZaokrouhlovani extends Frame {
  TextField vstupTF;
  TextField vystupTF;
  Scrollbar vstupSB;
  TextField scaleTF;
  CheckboxGroup zaokCHB;
  int scale = 0;
  int zaok = BigDecimal.ROUND_CEILING;
  BigDecimal vstupBD = new BigDecimal("123.45");
  int minSB;
  int maxSB;
  int actSB;

  int[] typI = {BigDecimal.ROUND_CEILING,   BigDecimal.ROUND_FLOOR,
                BigDecimal.ROUND_UP,        BigDecimal.ROUND_DOWN,
                BigDecimal.ROUND_HALF_UP,   BigDecimal.ROUND_HALF_DOWN,
                BigDecimal.ROUND_HALF_EVEN, BigDecimal.ROUND_UNNECESSARY };
  String[] typS = {"ROUND_CEILING",   "ROUND_FLOOR",
                   "ROUND_UP",        "ROUND_DOWN",
                   "ROUND_HALF_UP",   "ROUND_HALF_DOWN",
                   "ROUND_HALF_EVEN", "ROUND_UNNECESSARY" };

  public BigDecimalGUIZaokrouhlovani() {
    this.setTitle(getClass().getName());
    this.setLayout(new GridLayout(7, 2, 10, 10));
    vstupTF = new TextField(vstupBD.toString());
    this.add(vstupTF);
    vstupTF.addActionListener(new AL1());

    vystupTF = new TextField(6);
    vystupTF.setEditable(false);
    zobrazVysledek();
    this.add(vystupTF);

    zjisti();
    vstupSB = new Scrollbar(Scrollbar.HORIZONTAL, actSB, 0, minSB, maxSB);
    this.add(vstupSB);
    vstupSB.addAdjustmentListener(new ADL());
    this.add(new Label("movePointLeft / Right"));

    this.add(new Label("Scale", Label.RIGHT));
    scaleTF = new TextField("" + scale);
    this.add(scaleTF);
    scaleTF.addActionListener(new AL2());

    zaokCHB = new CheckboxGroup();
    for (int i = 0;  i < typI.length;  i++) {
      Checkbox b = new Checkbox(typS[i], zaokCHB, false);
      this.add(b);
      b.addItemListener(new IL());
      if (i == 0) 
        b.setState(true);
    }    

    this.pack();

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }

  class AL1 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      vstupBD = new BigDecimal(vstupTF.getText());
      zobrazVysledek();
    }
  }

  class AL2 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      scale = Integer.parseInt(scaleTF.getText());
      zobrazVysledek();
    }
  }

  class ADL implements AdjustmentListener {
    public void adjustmentValueChanged(AdjustmentEvent e) {
      int i = vstupSB.getValue();
      int j = actSB - i;
      if (j < 0)
        vstupBD = vstupBD.movePointRight(Math.abs(j));
      else
        vstupBD = vstupBD.movePointLeft(Math.abs(j));
      vstupTF.setText(vstupBD.toString());
      zjisti();
      vstupSB.setValues(actSB, 0, minSB, maxSB);
      zobrazVysledek();
    }
  }

  class IL implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      Checkbox b = zaokCHB.getSelectedCheckbox();
      String s = b.getLabel();
      for (int i = 0;  i < typI.length;  i++) {
        if (s.equals(typS[i]) == true) {
          zaok = typI[i];
          break;
        }
      }
      zobrazVysledek();
    }
  }

  void zobrazVysledek() {
    try {
      BigDecimal vystupBD = vstupBD.setScale(scale, zaok);
      vystupTF.setText(vystupBD.toString());
    }
    catch (ArithmeticException e) {
      vystupTF.setText("zaokrouhleni nutne");
    }
  }

  void zjisti() {
    String s = vstupBD.toString();
    if (s.charAt(0) == '-')
      s = s.substring(1);
    minSB = 0;
    maxSB = s.length() + 1;
    if ((actSB = s.indexOf('.')) == -1)
      actSB = maxSB - 2;
  }

  public static void main(String[] args) {
    new BigDecimalGUIZaokrouhlovani().setVisible(true);
  }
}

