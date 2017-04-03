/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.raport.xls;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import studentapp.db.data.graph;
import studentapp.db.data.trafic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Алексей
 */
public class GenerateXMLFields {

    public static void main(String[] args) throws XDocReportException,IOException {
    
    // 1) Create FieldsMetadata by setting Velocity as template engine
    FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());
                
    // 2) Load fields metadata from Java Class
    fieldsMetadata.load("trafic", trafic.class);
    // Here load is called with true because model is a list of Developer.
   fieldsMetadata.load("graph", graph.class, true);
  // fieldsMetadata.load("cars", cars.class, true);
    
    // 3) Generate XML fields in the file "project.fields.xml".
    // Extension *.fields.xml is very important to use it with MS Macro XDocReport.dotm
    // FieldsMetadata#saveXML is called with true to indent the XML.
    File xmlFieldsFile = new File("report.fields.xml");
    fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
  }
    
    
}
