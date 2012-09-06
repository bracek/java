package fi.ixonos.builder.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import fi.ixonos.builder.domain.NewsTemplate;
import javax.persistence.OneToOne;

@RooJavaBean
@RooToString
@RooEntity
public class Platform {

    @NotNull
    @Size(min = 3)
    private String platform_name;

    @OneToOne
    private NewsTemplate news_template;
}
