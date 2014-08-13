package com.globallogic.kaacoo.view.presidentList;

import com.globallogic.kaacoo.model.President;

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
public class PresidentListModelResponse implements Serializable {

    @XmlElement
    private List<President> presidents;

    public List<President> getPresidents() {
        return presidents;
    }

    public void setPresidents(List<President> presidents) {
        this.presidents = presidents;
    }

    public static class Builder implements Serializable {

        public final PresidentListModelResponse response;

        private Builder() {
            super();
            response = new PresidentListModelResponse();
        }

        public Builder presidents(List<President> presidents) {
            response.setPresidents(presidents);
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
