package com.springsource.roo.pizzashop.domain;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Base {

    @NotNull
    @Size(min = 2)
    private String name;

    @RooUploadedFile(contentType = "image/png")
    @Lob
    private byte[] icon;
}
