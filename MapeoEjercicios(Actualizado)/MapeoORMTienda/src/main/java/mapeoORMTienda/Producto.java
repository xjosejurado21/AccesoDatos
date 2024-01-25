package mapeoORMTienda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")

public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private double precio;

	@ManyToOne
	@JoinColumn(name = "codigo_fabricante", nullable = false)
	private FabricanteTienda fabricante;

	public Producto() {

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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	

	public FabricanteTienda getFabricante() {
		return fabricante;
	}

	public void setFabricante(FabricanteTienda fabricante) {
		this.fabricante = fabricante;
	}

	public Producto(String nombre, double precio, FabricanteTienda fabricante) {
		this.nombre = nombre;
		this.precio = precio;

		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", codigo_fabricante="
				+ ", fabricante=" + fabricante + "]";
	}

}
