package mapeoORMTienda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "fabricante")

public class FabricanteTienda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private int id;

	@Column(name = "nombre")
	private String nombre;

// Constructor por defecto requerido por Hibernate
	public FabricanteTienda() {

	}

//Constructor para crear un objeto de tipo Clientes con un nombre
	public FabricanteTienda(String nombre) {
		this.nombre = nombre;
	}

// Getter y Setter para el atributo id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

// Getter y Setter para el atributo nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

// Metodo para imprimir la informacion del objeto fabricante
	@Override
	public String toString() {
		return "FabricanteTienda [id=" + id + 
				", nombre=" + nombre + "]";
	}

}
