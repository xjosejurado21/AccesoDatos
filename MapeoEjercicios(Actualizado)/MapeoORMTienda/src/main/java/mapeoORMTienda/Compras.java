package mapeoORMTienda;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compras")
public class Compras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcompras")
	private int id;

	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_fabricante", nullable = false)
	private FabricanteTienda fabricante;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;
	
	@Column(name = "unidades")
	private int unidadews;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public FabricanteTienda getFabricante() {
		return fabricante;
	}

	public void setFabricante(FabricanteTienda fabricante) {
		this.fabricante = fabricante;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getUnidadews() {
		return unidadews;
	}

	public void setUnidadews(int unidadews) {
		this.unidadews = unidadews;
	}

	public Compras(Date fecha, FabricanteTienda fabricante, Producto producto, int unidadews) {
		this.fecha = fecha;
		this.fabricante = fabricante;
		this.producto = producto;
		this.unidadews = unidadews;
	}

	@Override
	public String toString() {
		return "Compras [id=" + id + ", fecha=" + fecha + ", fabricante=" + fabricante + ", producto=" + producto
				+ ", unidadews=" + unidadews + "]";
	}
	
	
}
