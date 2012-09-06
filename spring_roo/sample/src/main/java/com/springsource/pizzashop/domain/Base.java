package com.springsource.pizzashop.domain;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooJson(deepSerialize = true)
public class Base {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @RooUploadedFile(contentType = "image/png")
    @Lob
    private byte[] icon;

    @RooUploadedFile(contentType = "image/png")
    @Lob
    private byte[] application_80_80;
}
