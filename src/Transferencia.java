import java.time.LocalDate;

public class Transferencia extends Transaccion {
    //Atributos
    Cuenta cuentaDestino;

    //Constructor
    public Transferencia(String IDTransaccion, LocalDate fecha, double monto, Cuenta cuentaDestino) {
		super(IDTransaccion, fecha, "Transferencia", monto);
        this.cuentaDestino = cuentaDestino;
	}

    //Métodos Get
    public String getCuentaDestino() {
        return cuentaDestino.numeroCuenta;
    }

    /*@Override
    public void Transacciones() {
    			System.out.print("Cuanto quieres Transferir: ");
                Transferencia();
                if (transferencia <= getSaldo()) {
                    transacciones = getSaldo();
                    setSaldo(transacciones - transferencia);
                    System.out.println("====================================");
                    System.out.println("=================BBVA===============");
                    System.out.println("				Debito				");
                    System.out.println("Se relalizo una Consulta el 31/10/2023");
                    System.out.println("A la cuenta "+numero_cuenta);
                    System.out.println("transferiste : " + transferencia);
                    System.out.println("Tu saldo actual es: " + getSaldo());
                    System.out.println("====================================");
                } else {
                    System.out.println("=====================");
                    System.out.println("Saldo insuficiente");
                    System.out.println("=====================");
               
        	
                }
    			
    }*/
}