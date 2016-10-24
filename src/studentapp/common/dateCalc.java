/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentapp.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class dateCalc {
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public dateCalc() {
    }
    
 public  List<String> datesBetween(LocalDate start, LocalDate end) {
    List<String> ret = new ArrayList<>();
    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
        ret.add(formatter.format(date));
    }
    return ret;
}
    
}
