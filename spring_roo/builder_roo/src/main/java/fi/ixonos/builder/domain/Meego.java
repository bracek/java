package fi.ixonos.builder.domain;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Meego {

	@NotNull
	@Size(min = 1, max = 100)
	private String home_page;

	@NotNull
	@Size(min = 1, max = 60)
	private String short_description;

	@NotNull
	@Size(min = 1, max = 250)
	private String long_description;

	@NotNull
	@Size(min = 1, max = 30)
	private String display_name;

	@OneToOne
	@JoinColumn(name = "project")
	private Projects project;

	@Override
	public String toString() {
		return super.toString(); // avoids circular references
	}

	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

}
