import java.time.LocalDate;
public class Retiro extends Transaccion {

	//Constructor
	public Retiro(String IDTransaccion, LocalDate fecha, double monto) {
		super(IDTransaccion, fecha, "Retiro", monto);
	}
}
