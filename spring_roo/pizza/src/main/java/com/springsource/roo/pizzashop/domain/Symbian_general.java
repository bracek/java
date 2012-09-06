package com.springsource.roo.pizzashop.domain;

import javax.persistence.Lob;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Symbian_general {

    @RooUploadedFile(contentType = "image/gif")
    @Lob
    private byte[] application_icon;
}
