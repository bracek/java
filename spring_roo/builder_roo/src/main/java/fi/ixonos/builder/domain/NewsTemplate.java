package fi.ixonos.builder.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import fi.ixonos.builder.domain.Platform;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import fi.ixonos.builder.domain.Projects;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity
public class NewsTemplate {

    @NotNull
    @Size(min = 1)
    private String category_url;

    @OneToOne
	@JoinColumn(name = "platform")
    private Platform platform;

    // Owning side (this table has the FK column)
    @ManyToOne
    private Projects project;
}
