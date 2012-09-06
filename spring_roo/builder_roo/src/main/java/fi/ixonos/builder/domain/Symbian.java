package fi.ixonos.builder.domain;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Symbian {

	@OneToOne
	@JoinColumn
	private Projects project;

	private java.lang.String filename;

	@NotNull
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;

	@Transient
	@Size(max = 100)
	private String url;

	@Override
	public String toString() {
		return super.toString(); // avoids circular references
	}

	@SuppressWarnings("unused")
	@PreRemove
	private void preRemove() {
		this.getProject().setSymbian(null);
	}

	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

}
