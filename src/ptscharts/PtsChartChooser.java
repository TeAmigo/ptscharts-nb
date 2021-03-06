/*********************************************************************
 * File path:     /share/JavaDev/PeTraSys/src/petrasys/PeTraSysCharts.java
 * Version:       
 * Description:   
 * Author:        Rick Charon <rickcharon@gmail.com>
 * Created at:    Wed Nov 24 12:10:02 2010
 * Modified at:   Wed Nov 24 13:49:43 2010
 ********************************************************************/

/*
 * ChartsForm.java
 *
 * Created on Apr 2, 2010, 5:19:20 AM
 */
package ptscharts;

import ptsutils.PtsDBops;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import javax.swing.JList;
import org.joda.time.DateTime;
//import petrasys.utils.Classes;
//import petrasys.utils.DBops;
//import petrasys.utils.DateOps;
//import petrasys.utils.MsgBox;
//import petrasys.utils.ReportFrame;

/**
 *
 * @author rickcharon
 */
public class PtsChartChooser extends javax.swing.JFrame {

  java.awt.Image appIcon = this.getToolkit().createImage("/share/icons/line-chart.png");
  //private ReportFrame reportFrame;
  private BlockingQueue<String> reportQueue;
  SimpleDateFormat dateStrFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
  private List<Class> indicatorsList;

  private class symbolInfo {

    String symbol;
    String exchange;
    int multiplier;
    int priceMagnifier;
    double minTick;
    String fullName;
  };
  //HashMap symbolInfos;
  symbolInfo workingSI;
  private Vector<String> symbols;
  PtsSymbolInfos syminfs;
  PtsChartFactory factory;

  public Vector<String> getSymbols() {
    return symbols;
  }

  public JList getSymbolsList() {
    return symbolsList;
  }

  private void initLists() {
    //getDistinctSymbolInfos();
    getDistinctSymbolNames();
    //populateIndicators();

  }

  /** Creates new form ChartsForm */
  public PtsChartChooser() {
    symbols = new Vector();
    syminfs = new PtsSymbolInfos();
    syminfs.getDistinctSymbolInfos();
    factory = new PtsChartFactory(syminfs);
    setIconImage(appIcon);
    //symbolInfos = new HashMap();
    initLists();
    initComponents();
  }

  public void getDistinctSymbolNames() {
    //return symbols;   Vector<String>
    try {
      ResultSet res = PtsDBops.distinctSymsProc().executeQuery();
      int rowSize = res.getRow();
      res.last();
      rowSize = res.getRow();
      res.first();
      rowSize = res.getRow();
      res.previous();
      rowSize = res.getRow();
      symbols.clear();
      while (res.next()) {
        symbols.add(res.getString("symbol"));
      }
      res.close();
    } catch (SQLException sqlex) {
      System.err.println("SQLException: " + sqlex.getMessage());
    }
  }

  public DateTime getBeginDate() {
    DateTime dt = new DateTime(beginDateChooser.getCalendar());
    return dt;
  }

  public void setBeginDate(String beginDt) {
    Calendar c = new GregorianCalendar();
    DateTime bdt = new DateTime(beginDt);
    beginDateChooser.setCalendar(bdt.toCalendar(null));
  }

  public DateTime getEndDate() {
    DateTime dt = new DateTime(endDateChooser.getCalendar());
    return dt;
  }

  public void setEndDate(String endDt) {
    Calendar c = new GregorianCalendar();
    DateTime bdt = new DateTime(endDt);
    endDateChooser.setCalendar(bdt.toCalendar(null));
  }

  public void setSelectedSymbol(String symIn) {
    symbolsList.setSelectedValue(symIn, true);
  }

  public void setCompressionFactor(int factorIn) {
    priceBarCompressionText.setText(Integer.toString(factorIn));
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jComboBox1 = new javax.swing.JComboBox();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    symbolsList = new javax.swing.JList(symbols);
    jLabel5 = new javax.swing.JLabel();
    beginDateLabel = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    endDateLabel = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    beginDateChooser = new com.toedter.calendar.JDateChooser();
    jLabel2 = new javax.swing.JLabel();
    endDateChooser = new com.toedter.calendar.JDateChooser();
    createChartButton = new javax.swing.JButton();
    priceBarsCompressionPanel2 = new javax.swing.JPanel();
    priceBarCompressionText = new javax.swing.JTextField();
    jLabel12 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    exitButton = new javax.swing.JButton();

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("PeTraSys - Charts");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("petrasys/Bundle"); // NOI18N
    jLabel1.setText(bundle.getString("PeTraSysTopFrame1.jLabel1.text")); // NOI18N

    symbolsList.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    symbolsList.setModel(new javax.swing.AbstractListModel() {
      public int getSize() { return symbols.size(); }
      public Object getElementAt(int i) { return symbols.elementAt(i); }
    });
    symbolsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    symbolsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        symbolsListValueChanged(evt);
      }
    });
    jScrollPane1.setViewportView(symbolsList);

    jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    jLabel5.setText(bundle.getString("PeTraSysTopFrame1.jLabel5.text")); // NOI18N

    beginDateLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 16));
    beginDateLabel.setText(bundle.getString("PeTraSysTopFrame1.beginDateLabel.text")); // NOI18N

    jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    jLabel3.setText(bundle.getString("PeTraSysTopFrame1.jLabel3.text")); // NOI18N

    endDateLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 16));
    endDateLabel.setText(bundle.getString("PeTraSysTopFrame1.endDateLabel.text")); // NOI18N

    jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    jLabel6.setText(bundle.getString("PeTraSysTopFrame1.jLabel6.text")); // NOI18N

    beginDateChooser.setDateFormatString("MM/dd/yyyy HH:mm:ss");
    beginDateChooser.setDoubleBuffered(false);
    beginDateChooser.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
    beginDateChooser.setName("beginDateChooser"); // NOI18N
    //beginDateChooser.setVisible(false);

    jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    jLabel2.setText(bundle.getString("PeTraSysTopFrame1.jLabel2.text")); // NOI18N

    endDateChooser.setDateFormatString("MM/dd/yyyy HH:mm:ss");
    endDateChooser.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
    //endDateChooser.setVisible(false);

    createChartButton.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
    createChartButton.setText("Create Chart");
    createChartButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createChartButtonActionPerformed(evt);
      }
    });

    priceBarsCompressionPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    priceBarsCompressionPanel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14));

    priceBarCompressionText.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    priceBarCompressionText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    priceBarCompressionText.setText(bundle.getString("PeTraSysTopFrame1.priceBarCompressionFactor.text")); // NOI18N

    jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    jLabel12.setText(bundle.getString("PeTraSysTopFrame1.jLabel9.text")); // NOI18N

    jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
    jLabel13.setText(bundle.getString("PeTraSysTopFrame1.jLabel8.text")); // NOI18N

    javax.swing.GroupLayout priceBarsCompressionPanel2Layout = new javax.swing.GroupLayout(priceBarsCompressionPanel2);
    priceBarsCompressionPanel2.setLayout(priceBarsCompressionPanel2Layout);
    priceBarsCompressionPanel2Layout.setHorizontalGroup(
      priceBarsCompressionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(priceBarsCompressionPanel2Layout.createSequentialGroup()
        .addGroup(priceBarsCompressionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(priceBarsCompressionPanel2Layout.createSequentialGroup()
            .addGap(91, 91, 91)
            .addGroup(priceBarsCompressionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel12)
              .addComponent(priceBarCompressionText, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addComponent(jLabel13))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    priceBarsCompressionPanel2Layout.setVerticalGroup(
      priceBarsCompressionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(priceBarsCompressionPanel2Layout.createSequentialGroup()
        .addComponent(jLabel13)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel12)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(priceBarCompressionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    exitButton.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
    exitButton.setText("Exit Program");
    exitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(13, 13, 13)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jLabel5)
              .addComponent(jLabel3)
              .addComponent(beginDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
              .addComponent(endDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          .addComponent(jLabel1))
        .addGap(35, 35, 35)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(beginDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(priceBarsCompressionPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(createChartButton))
        .addContainerGap(35, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(452, Short.MAX_VALUE)
        .addComponent(exitButton)
        .addGap(81, 81, 81))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addGap(3, 3, 3)
            .addComponent(beginDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addComponent(jLabel2)
            .addGap(3, 3, 3)
            .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(priceBarsCompressionPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(createChartButton))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(6, 6, 6)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endDateLabel))
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addGap(28, 28, 28)
        .addComponent(exitButton)
        .addGap(23, 23, 23))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents
    private void symbolsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_symbolsListValueChanged
      String sym = (String) symbolsList.getSelectedValue();
      try {
        ResultSet res = PtsDBops.minMaxDatesBySym(sym).executeQuery();
        if (res.next()) {
          Timestamp minD = res.getTimestamp(1);
          Timestamp maxD = res.getTimestamp(2);
          Calendar minCal = new GregorianCalendar();
          minCal.setTime(minD);
          Calendar maxCal = new GregorianCalendar();
          maxCal.setTime(maxD);
          SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
          String beginTime = dateFormat.format(minCal.getTime());
          String endTime = dateFormat.format(maxCal.getTime());
          //beginDateChooser.setCalendar(minCal);
          //endDateChooser.setCalendar(maxCal);
          beginDateLabel.setText(PtsDateOps.dbFormatString(minD));
          endDateLabel.setText(PtsDateOps.dbFormatString(maxD));
        }
        //int i = 1;
      } catch (SQLException ex) {
        System.err.println("SQLException in symbolsListValueChanged: " + ex.getMessage());
      }
}//GEN-LAST:event_symbolsListValueChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

  public void createChart() {
    String sym = (String) (symbolsList.getSelectedValue());
    int cf = Integer.parseInt(priceBarCompressionText.getText().trim());
    PtsChart chart = factory.createPtsChart(sym, getBeginDate(), getEndDate(), cf);
    chart.show();
  }

    private void createChartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createChartButtonActionPerformed
      createChart();
    }//GEN-LAST:event_createChartButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
      System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      setExtendedState(ICONIFIED);
    }//GEN-LAST:event_formWindowClosing

//    public void getDistinctSymbolInfos() {
//    try {
//      ResultSet res = PtsDBops.distinctSymbolInfos().executeQuery();
//      symbolInfos.clear();
//      symbols.clear();
//      while (res.next()) {
//        symbolInfo si = new symbolInfo();
//        si.symbol = res.getString("symbol");
//        si.exchange = res.getString("exchange");
//        si.multiplier = res.getInt("multiplier");
//        si.priceMagnifier = res.getInt("priceMagnifier");
//        si.minTick = res.getDouble("minTick");
//        si.fullName = res.getString("fullName");
//        symbolInfos.put(si.symbol, si);
//        //symbols.add(res.getString("symbol"));
//      }
//      res.close();
//    } catch (SQLException ex) {
//      Logger.getLogger(PtsChartChooser.class.getName()).log(Level.SEVERE, null, ex);
//    }
//  }
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        PtsChartChooser pcc = new PtsChartChooser();
        pcc.setBeginDate("2010-11-14T03:00");
        pcc.setEndDate("2010-12-18T07:00");
        DateTime dt = pcc.getEndDate();
        pcc.setSelectedSymbol("CAD");
        pcc.setCompressionFactor(60);
        pcc.setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private com.toedter.calendar.JDateChooser beginDateChooser;
  private javax.swing.JLabel beginDateLabel;
  private javax.swing.JButton createChartButton;
  private com.toedter.calendar.JDateChooser endDateChooser;
  private javax.swing.JLabel endDateLabel;
  private javax.swing.JButton exitButton;
  private javax.swing.JComboBox jComboBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextField priceBarCompressionText;
  private javax.swing.JPanel priceBarsCompressionPanel2;
  private javax.swing.JList symbolsList;
  // End of variables declaration//GEN-END:variables
}
