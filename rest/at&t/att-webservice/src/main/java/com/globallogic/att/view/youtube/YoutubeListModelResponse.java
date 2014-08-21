package com.globallogic.att.view.youtube;

import com.google.api.services.youtube.model.SearchResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Miroslav Katrak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
public class YoutubeListModelResponse implements Serializable {

    @XmlElement
    private List<SearchResult> results;

    public List<SearchResult> getItems() {
        return results;
    }

    public void setItems(List<SearchResult> resultsList) {
        this.results = resultsList;
    }

    public static class Builder implements Serializable {

        public final YoutubeListModelResponse response;

        private Builder() {
            super();
            response = new YoutubeListModelResponse();
        }

        public Builder items(List<SearchResult> results) {
            response.setItems(results);
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
