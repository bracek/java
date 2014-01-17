package fi.ixonos.builder.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findProjectsesByNameEquals" })
public class Projects {

	@NotNull
	@Size(min = 1, max = 30)
	private String name;

	@NotNull
	@Size(min = 3, max = 30)
//	@Pattern(regexp = "b[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}b")
	private String email;

    // Inverse side ("Projects" table has the FK column)
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "project")
	private Symbian symbian;

//	Inverse side ("Projects" table has the FK column)
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "project")
	private Meego meego;

//	Inverse side ("Projects" table has the FK column)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private Set<NewsTemplate> news_template = new HashSet<NewsTemplate>();

	public Symbian getSymbian() {
		return symbian;
	}

	public void setSymbian(final Symbian symbian) {
		this.symbian = symbian;
	}

	public Meego getMeego() {
		return meego;
	}

	public void setMeego(final Meego meego) {
		this.meego = meego;
	}
}
