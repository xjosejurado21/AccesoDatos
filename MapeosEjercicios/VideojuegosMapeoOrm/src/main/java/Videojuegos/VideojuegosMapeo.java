package Videojuegos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tvideojuegos")
public class VideojuegosMapeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJuego")
    private int idJuego;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "anio")
    private int anio;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "precio")
    private double precio;
    @Column(name = "sinopsis")
    private String sinopsis;
    @Column(name = "plataforma")
    private String plataforma;


    // Constructor por defecto requerido por Hibernate
    public VideojuegosMapeo () {
    }


	public VideojuegosMapeo(String nombre, int anio, String empresa, double precio, String sinopsis,
			String plataforma) {
		super();
		this.nombre = nombre;
		this.anio = anio;
		this.empresa = empresa;
		this.precio = precio;
		this.sinopsis = sinopsis;
		this.plataforma = plataforma;
	}


	public int getIdJuego() {
		return idJuego;
	}


	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getAnio() {
		return anio;
	}


	public void setAnio(int anio) {
		this.anio = anio;
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
		return "VideojuegosMapeo [idJuego=" + idJuego + ", nombre=" + nombre + ", anio=" + anio + ", empresa=" + empresa
				+ ", precio=" + precio + ", sinopsis=" + sinopsis + ", plataforma=" + plataforma + "]";
	}
	


    
}

