package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "consulta")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsulta;
	
	// ForeignKey 
	@ManyToOne 
	// name equivale a como se va a llamar la columna en la BD
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
	private Paciente paciente;

	// ForeignKey
	@ManyToOne 
	// name equivale a como se va a llamar la columna en la BD
	@JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_medico"))
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_especialidad"))
	private Especialidad especialidad;
	
	@Column(name = "num_consultorio", length = 3, nullable = true)
	private String numConsultorio;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha; // || java.time.LocalDateTime
	
	/**
	 * La anotación @OneToMany no altera la base de datos, solamente es a nivel lógico,
	 * Éste recurso se usa cuando en las reglas de negocio se requiere un Maestro-Detalle (Factura)
	 * además, porque en la BD no puedo pone una List/Set como columna.	
	 * 
	 * La anotación @mappedBy es el atributo que se encuentra en la otra Clase/Tabla
	 * por la cual se está haciendo la relación. NO confundir con el nombre de esta clase
	 * que precisamente se llama igual.
	 * 
	 * cascadeType.ALL ejecuta todos los principios A.C.I.D.
	 * - Atomicity		->		Se hacen todos los cambios o no se hace ninguno.
	 * - Consistency	->		La data debe ser consistente antes y despues de la transacción. 
	 * - Isolation		->		Ningún otro proceso puede cambiar la data mientras la transacción está corriendo.
	 * - Durability		->		Los cambios hechos en una transacción deben persistir.
	 * 
	 * orphanRemoval - Permite en un futuro editar para eliminar la lista, en este caso los detalles de la consulta
	 * 
	 */
	@OneToMany(mappedBy = "consulta", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DetalleConsulta> detalleConsulta;

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getNumConsultorio() {
		return numConsultorio;
	}

	public void setNumConsultorio(String numConsultorio) {
		this.numConsultorio = numConsultorio;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public List<DetalleConsulta> getDetalleConsulta() {
		return detalleConsulta;
	}

	public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
		this.detalleConsulta = detalleConsulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConsulta == null) ? 0 : idConsulta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (idConsulta == null) {
			if (other.idConsulta != null)
				return false;
		} else if (!idConsulta.equals(other.idConsulta))
			return false;
		return true;
	}
	
	

}
