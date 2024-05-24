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
        cuenta.saldo -= monto;
        imprimirTicket(retiro, cuenta);
    }

    public void Transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, String tipo, double monto) {
        Transaccion transferir = new Transferencia("", LocalDate.now(), monto, cuentaDestino);
        cuentaOrigen.saldo -= monto;
        cuentaDestino.saldo += monto;
        imprimirTicket(transferir, cuentaOrigen);
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

    public void imprimirTicket(Transaccion t, Cuenta cta) {
        System.out.println("**\tBBVA BANCOMER, S.A.\t**");
        System.out.println("FECHA: "+t.getFecha());
        System.out.println(t.tipo.toUpperCase());
        System.out.println("\nCLIENTE: "+cta.cliente.nombre.toUpperCase());
        System.out.println("UBICADO EN: "+ubicacion.toUpperCase());
        System.out.println("\nFOLIO: "+t.getIDTransaccion());
        System.out.println("CUENTA: "+cta.getNumeroCuenta());
        if (t.tipo.equals("Transferencia")) {
            System.out.println("CUENTA DESTINO: "+t.getCuentaDestino());
        }
        System.out.println("IMPORTE: $"+t.getMonto());
        System.out.println("SALDO: $"+cta.getSaldo());
        System.out.println("\t\tBBVA BANCOMER");
    }
}