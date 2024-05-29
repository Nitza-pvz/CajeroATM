import java.time.LocalDate;

public class Transferencia extends Transaccion {
    //Atributos
    private Cuenta cuentaDestino;

    //Constructor
    public Transferencia(String IDTransaccion, LocalDate fecha, double monto, Cuenta cuentaDestino) {
		super(IDTransaccion, fecha, "Transferencia", monto);
        this.cuentaDestino = cuentaDestino;
	}

    //Métodos Get
    public String getCuentaDestino() {
        return cuentaDestino.getNumeroCuenta();
    }
}