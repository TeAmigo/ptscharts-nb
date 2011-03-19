/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ptscharts;

import java.awt.Font;
import javax.swing.UIManager;
import org.joda.time.DateTime;

/**
 *
 * @author rickcharon
 */
public class PtsChartFactory {

  public PtsSymbolInfos symInfos;

  public PtsChartFactory(PtsSymbolInfos symInfos) {
    this.symInfos = symInfos;
    Font f = new Font("dialog", Font.PLAIN, 18);
    UIManager.put("ToolTip.font", f);
  }




  public PtsChart createPtsChart(String sym,  String begindtIn, String enddtIn, int compressionFactorIn) {
    DateTime beginDt = new DateTime(begindtIn);
    DateTime endDt = new DateTime(enddtIn);
    return createPtsChart(sym, beginDt, endDt, compressionFactorIn);
  }
  
  public PtsChart createPtsChart(String sym,  DateTime beginDt, DateTime endDt, int compressionFactorIn) {
    PtsChartFrame frame = new PtsChartFrame(symInfos.getSymbolInfo(sym), beginDt, endDt, compressionFactorIn);
    return frame.getChart();
  }

}
