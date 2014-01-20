package com.blog.samples.web.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * The Class DateSerializer.
 */
public class DateSerializer extends JsonSerializer<Date> {

    /*
     * (non-Javadoc)
     * 
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(final Date value_p,
 final JsonGenerator gen,
 final SerializerProvider prov_p) throws IOException, JsonProcessingException {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HHmmss");
        final String formattedDate = formatter.format(value_p);
        gen.writeString(formattedDate);
    }
}
