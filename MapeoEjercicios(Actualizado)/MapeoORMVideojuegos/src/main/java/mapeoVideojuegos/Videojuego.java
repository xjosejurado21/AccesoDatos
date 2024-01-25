package mapeoVideojuegos;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "tvideojuegos")
public class Videojuego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idjuego")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "anio")
	private int año;

	@Column(name = "empresa")
	private String empresa;

	@Column(name = "precio")
	private double precio;

	@Column(name = "sinopsis")
	private String sinopsis;

	@Column(name = "plataforma")
	private String plataforma;

	// Constructor por defecto requerido por Hibernate
	public Videojuego() {
	}

	public Videojuego(String nombre, int año, String empresa, double precio, String sinopsis, String plataforma) {

		this.nombre = nombre;
		this.año = año;
		this.empresa = empresa;
		this.precio = precio;
		this.sinopsis = sinopsis;
		this.plataforma = plataforma;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", año=" + año + ", empresa=" + empresa + ", precio="
				+ precio + ", sinopsis=" + sinopsis + ", plataforma=" + plataforma + "]";
	}


}
