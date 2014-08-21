package com.globallogic.att.view.youtube;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Miroslav Katrak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
public class YoutubeListModelRequest implements Serializable {

    @XmlElement
    @QueryParam("query")
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public static class Builder implements Serializable {

        public final YoutubeListModelRequest request;

        private Builder() {
            super();
            request = new YoutubeListModelRequest();
        }

        public Builder query(String query) {
            request.setQuery(query);
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
