package org.pragmatikroo.roodocman.domain;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Document {

    private static final Log log = LogFactory.getLog(Document.class);

    @NotNull
    @Size(max = 30)
    private java.lang.String name;

    @NotNull
    @Size(max = 500)
    private java.lang.String description;

    private java.lang.String filename;

    @NotNull
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    private java.lang.String contentType;

    private java.lang.Long size;
    
    @Transient
    @Size(max = 100)
    private String url ;
}
