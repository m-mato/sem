package cz.muni.pa165.sem.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Vit Hovezak
 */
public class CalendarEditor extends PropertyEditorSupport {

    private final String format;

    public CalendarEditor(String format) {
        this.format = format;
    }

    public void setAsText(String value) {
        if (value.equals("")) {
            setValue(null);
            return;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            setValue(calendar);
        } catch (ParseException e) {
            setValue(null);
        }
    }

    public String getAsText() {
        Calendar calendar = (Calendar) this.getValue();
        if (calendar == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }

}
