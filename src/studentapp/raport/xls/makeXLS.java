/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.raport.xls;

import javafx.collections.ObservableList;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Orientation;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import studentapp.common.common;
import studentapp.db.data.graph;
import studentapp.db.data.trafic;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class makeXLS {

    private graph g;
    private String data;
    private String student;
    private String tema;
    private String tema_time;
    private String t1;
    private String t2;
    private String[] time = {"одна", "дві", " ", "00", "30"};
    private int r = 6;
    private common com;
    public makeXLS() {
    }
    
    public void setGraphic(String master,int s,int shee, int group) throws IOException, BiffException, ParseException, SQLException{
        String [] m = master.split(" ");
        Workbook workbook = Workbook.getWorkbook(new File("g.xls"));
        Sheet sheet = workbook.getSheet(m[0].toUpperCase());
        if (sheet == null) {
            sheet = workbook.getSheet(0);
        }
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
    
    public void makeTabel(Object instruktor, Object Car, String Group, List<String> days, ObservableList<trafic> tabelData) throws BiffException, IOException, WriteException{
        Workbook wb;
        WritableWorkbook new_wb;
        WritableSheet sheet;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        NumberFormat nf = new NumberFormat("#,#");
        // set month of tabel
        String month = new common().month[Integer.valueOf(days.get(0).substring(3,5))-1];
        String firstDay = days.get(0).substring(0,2);
        String lastDay = days.get(days.size()-1).substring(0,2);
        String year = days.get(0).substring(6);
        wb = Workbook.getWorkbook(makeXLS.class.getResourceAsStream("/studentapp/raport/xls/tmp/tabel.xls"));
        new_wb = Workbook.createWorkbook(new File("tmp.xls"),wb);
        sheet = new_wb.getSheet(0); 
        WritableFont wf = new WritableFont(WritableFont.ARIAL,12);
        WritableCellFormat cell_left = new WritableCellFormat(wf);
        WritableCellFormat cell = new WritableCellFormat(wf);
        WritableCellFormat cell_data = new WritableCellFormat(wf);
        //number format
        WritableCellFormat cell_nf = new WritableCellFormat(wf,nf);
        cell_nf.setAlignment(jxl.format.Alignment.CENTRE);
        cell_nf.setVerticalAlignment(VerticalAlignment.CENTRE);
        cell_nf.setBorder(Border.ALL, BorderLineStyle.THIN);
        // end number format
        cell_data.setAlignment(jxl.format.Alignment.CENTRE);
        cell_data.setVerticalAlignment(VerticalAlignment.CENTRE);
        cell_data.setOrientation(Orientation.PLUS_90);
        cell_data.setBorder(Border.ALL, BorderLineStyle.THIN);
        WritableCellFormat cell_merge = new WritableCellFormat(wf);
        cell_merge.setAlignment(jxl.format.Alignment.CENTRE);
        cell_merge.setVerticalAlignment(VerticalAlignment.CENTRE);
        cell_merge.setBorder(Border.ALL, BorderLineStyle.THIN);
        cell.setAlignment(jxl.format.Alignment.CENTRE);
        cell.setVerticalAlignment(VerticalAlignment.CENTRE);
        int row = 2;
        sheet.addCell(new Label(2, row, instruktor.toString().toUpperCase(), cell_left));
        sheet.addCell(new Label(4, row+2,Car.toString() , cell_left));
        sheet.addCell(new Label(21, row+2,Group , cell_left));
        sheet.addCell(new Label(9, row, ("за час з "+firstDay+" "+month+" по "+lastDay+" "+month+" "+year+"р.").toUpperCase(), cell_left));
        int col = 1;
        int sum=0;
        for(int i=0;i<=days.size()-1;i++){
           sheet.addCell(new Label(col, 7, days.get(i), cell_data));
            String day = days.get(i);
            sheet.addCell(new Label(col, 8,"" , cell_merge));
            sheet.addCell(new Label(col, 9,"" , cell_merge));
            sheet.addCell(new Label(col, 10,"" , cell_merge));
            sheet.addCell(new Label(col, 11,"" , cell_merge));
            sheet.addCell(new Label(col, 12,"" , cell_merge));
         for(int e=0;e<=tabelData.size()-1;e++){
             String dayTabel = sdf.format(tabelData.get(e).getData_tr());
             if(dayTabel.equals(day)){
                // Double t = Double.valueOf(tabelData.get(e).getTime_drive().replace(".",","));
                 sheet.addCell(new Label(col, 8,tabelData.get(e).getTime_drive().replace(".",","), cell_nf)); //.substring(0,tabelData.get(e).getTime_drive().length()-2)
                 sum=sum+Integer.valueOf(tabelData.get(e).getTime_drive().substring(0,tabelData.get(e).getTime_drive().length()-2));
             }
          }
           col++;
        }
        sheet.mergeCells(1,6,col-1,6);
        sheet.addCell(new Label(1,6,"ЧИСЛА ГОДИН ПО ДНЯХ МІСЯЦЯ",cell_merge));
        sheet.mergeCells(col,6,col,7);
        sheet.setColumnView(col,10);
        sheet.addCell(new Label(col,6,"ВСЬОГО",cell_merge));
        sheet.addCell(new Label(col, 8, Integer.toString(sum) , cell_merge));
        sheet.addCell(new Label(col, 9,"" , cell_merge));
        sheet.addCell(new Label(col, 10,"" , cell_merge));
        sheet.addCell(new Label(col, 11,"" , cell_merge));
        sheet.addCell(new Label(col, 12,"" , cell_merge));
        sheet.addCell(new Label(1, 17,Integer.toString(sum) , cell_left)); // переделать
           new_wb.write();
           new_wb.close();

    }

    
  
    
}
