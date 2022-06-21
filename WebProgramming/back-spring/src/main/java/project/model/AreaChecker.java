package project.model;

import org.springframework.stereotype.Component;
import project.entities.EntryEntity;
import project.pojo.request.DotRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AreaChecker {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public EntryEntity checkEntry(DotRequest dotRequest)  {
        boolean entryValue = checkGetInto(dotRequest.getX(), dotRequest.getY(), dotRequest.getR());
        Date date = new Date();
        return new EntryEntity(dotRequest.getX(), dotRequest.getY(), dotRequest.getR(), entryValue, date);
    }


    public boolean checkGetInto(float x, float y, float r) {
        if(x>0 && y>0) {
            return false;
        } else if(x>=0 && y<=0) {
            return (x <= r && -y <= r);
        } else if(x<0 && y<0) {
            return (-x <= r && -y <= r/2);
        } else if(x<0 && y>0) {
            return (Math.sqrt(x * x + y * y) <= r);
        } else {
            return false;
        }
    }

}
