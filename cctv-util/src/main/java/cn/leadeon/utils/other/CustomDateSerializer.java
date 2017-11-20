package cn.leadeon.utils.other;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期转JSON
 *
 * @author LiJunJun
 * @since 2017.08.30
 */
public class CustomDateSerializer extends JsonSerializer<Date>
{
    
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = format.format(date);
        gen.writeString(formattedDate);
    }
}
