package studentapp.common;

import java.text.SimpleDateFormat;

/**
 * Created by Алексей on 29.06.2016.
 */
public class CustomDate extends java.sql.Date {

    public CustomDate(long date){
        super(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(this);
    }
}
