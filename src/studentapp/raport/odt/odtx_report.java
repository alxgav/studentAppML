/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.raport.odt;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import studentapp.message.error.error;

import java.io.*;

/**
 *
 * @author Алексей
 */
public class odtx_report {
     private IXDocReport report;
    public odtx_report() {
        
        
    }
    public IXDocReport loadODT(String template) throws XDocReportException{
        try {
            InputStream in = odtx_report.class.getResourceAsStream("/studentapp/raport/odt/tmp/"+template+".odt");
            report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
            
        } catch (IOException | XDocReportException ex) {
            new error().errorMessage(ex.toString());
        }
       return report; 
    }
    public OutputStream outODT() throws FileNotFoundException{
       
            return  new FileOutputStream(new File("template.odt"));
        
    }
    public void convertToPdf(){

    }
    
}
