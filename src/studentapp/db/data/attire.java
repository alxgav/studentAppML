/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.db.data;

import java.time.LocalDate;

/**
 *
 * @author Алексей
 */
public class attire {
    private LocalDate attire_date;

    public attire() {
    }
     public attire(LocalDate attire_date) {
         this.attire_date = attire_date;
    }
    

    public LocalDate getAttire_date() {
        return attire_date;
    }

    public void setAttire_date(LocalDate attire_date) {
        this.attire_date = attire_date;
    }
    
    
}
