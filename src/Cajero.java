import java.time.LocalDate;

public class Cajero {
    //Atributos
    String ubicacion;
    String banco;

    //Constructor
    public Cajero (String ubicacion, String banco) {
        this.banco = banco;
        this.ubicacion = ubicacion;
    }

    public void Retirar(Cuenta cuenta, String tipo, double monto) {
        Transaccion retiro = new Retiro("", LocalDate.now(), monto);
        
    }

    public void Transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, String tipo, double monto) {
        Transaccion transferir = new Transferencia("", LocalDate.now(), monto, cuentaDestino);
    }

    //Métodos Get/Set
    public String getUbicacion() {
        return ubicacion;
    }

    public String getBanco() {
        return banco;
    }

    public void setUbicacion(String ubi) {
        ubicacion = ubi;
    }

    public void setBanco(String ban) {
        banco = ban;
    }

    public void imprimirTicket() {

    }
}