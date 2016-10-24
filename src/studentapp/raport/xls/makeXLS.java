/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.raport.xls;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import studentapp.common.common;
import studentapp.db.data.graph;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import studentapp.db.data.trafic;

/**
 *
 * @author Алексей
 */
public class makeXLS {

    graph g;
    String data;
    String student;
    String tema;
    String tema_time;
    String t1;
    String t2;
    String [] time ={"одна","дві"," ","00","30"};
    int r =6;
    common com;
    public makeXLS() {
    }
    
    public void setGraphic(String master,int s,int shee, int group) throws IOException, BiffException, ParseException, SQLException{
        String [] m = master.split(" ");
        Workbook workbook = Workbook.getWorkbook(new File("g.xls"));
        Sheet sheet = workbook.getSheet(m[0].toUpperCase());
        com = new common();
     //   SimpleDateFormat format =new SimpleDateFormat("dd.MM.yyyy");
        for(int row=1;row<=s;row++){ //s количество студентов
            student = sheet.getCell(1,row+5).getContents();
           
            for(int col=5;col<=shee;col++){ //shee количество колонок
                DateCell dc = (DateCell) sheet.getCell(col, 5);
                data = new SimpleDateFormat("dd.MM.yyyy").format(dc.getDate()) ;
               tema = sheet.getCell(col, r).getContents();
               String [] t = tema.split("/");
               if(!"".equals(tema)){
                   tema_time=t[1];
                   tema=t[0];
                
                   if("1".equals(tema_time)){
                        t1= time[0];
                        t2 = time[3];
                   }
                    if("2".equals(tema_time)){
                        t1= time[1];
                        t2 = time[3];
                   }
                     if("0.5".equals(tema_time)){
                        t1= time[2];
                        t2 = time[4];
                   }
                   System.out.println(student+": "+data+" :"+tema+": "+tema_time+" :"+t1+" :"+t2);
                   g = new graph(data,master,student,tema,tema_time,group,t1,t2);
                   com.graph.create(g);
               }
               
               
            }
            r++;
        }
        workbook.close();
       
    }
    
    public void makeTabel(String instruktor, String Car, String Group,List <trafic> trafic,List<String> days) throws BiffException, IOException, WriteException{
        Workbook wb;
        WritableWorkbook new_wb;
        WritableSheet sheet;
        wb = Workbook.getWorkbook(makeXLS.class.getResourceAsStream("/studentapp/raport/xls/tmp/tabel.xls"));
        new_wb = Workbook.createWorkbook(new File("tmp.xls"),wb);
        sheet = new_wb.getSheet(0); 
        WritableFont wf = new WritableFont(WritableFont.ARIAL,12);
        WritableCellFormat cell_left = new WritableCellFormat(wf);
        WritableCellFormat cell = new WritableCellFormat(wf);
        cell.setAlignment(jxl.format.Alignment.CENTRE);
        cell.setVerticalAlignment(VerticalAlignment.CENTRE);
        int row = 2;
        sheet.addCell(new Label(2, row, instruktor.toUpperCase(), cell_left)); 
        sheet.addCell(new Label(4, row+2,Car , cell_left));
        sheet.addCell(new Label(21, row+2,Group , cell_left)); 
        int col = 1;
        for(int i=0;i<=days.size()-1;i++){
           sheet.addCell(new Label(col, 6, days.get(i), cell_left));
           //sheet.addCell(new Label(col, 8, trafic.get(i).getTime_drive(), cell_left));
          //  System.out.println(trafic.get(i).getTime_drive());
           col++;
        }
       new_wb.write();
       new_wb.close();
        
    }
    
  
    
}
