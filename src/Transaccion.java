import java.time.LocalDate;

public abstract class Transaccion {
    //Atributos
    String IDTransaccion;
	LocalDate fecha;
    String tipo;
    double monto;

    //Constructores
    public Transaccion(String IDTransaccion, LocalDate fecha, String tipo, double monto) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.IDTransaccion = IDTransaccion;
        this.monto = monto;
    }

    //Métodos Get
    public String getIDTransaccion() {
        return IDTransaccion;
    } 
    
    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getCuentaDestino() {
        return getCuentaDestino();
    }
}
