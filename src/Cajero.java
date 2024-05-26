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
        if (monto > cuenta.saldo) {
            System.out.println("\nLo sentimos, no dispone de saldo suficiente");
            imprimirTicket(retiro, cuenta, false);
        } else {
            cuenta.saldo -= monto;
            imprimirTicket(retiro, cuenta, true);
        }
    }

    public void Transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, String tipo, double monto) {
        Transaccion transferir = new Transferencia("", LocalDate.now(), monto, cuentaDestino);
        if (monto > cuentaOrigen.saldo) {
            System.out.println("\nLo sentimos, no dispone de saldo suficiente");
            imprimirTicket(transferir, cuentaOrigen, false);
        } else {
            cuentaOrigen.saldo -= monto;
            cuentaDestino.saldo += monto;
            imprimirTicket(transferir, cuentaOrigen, true);
        }
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

    public void imprimirTicket(Transaccion t, Cuenta cta, boolean status) {
        System.out.println("\n**\tBBVA BANCOMER, S.A.\t**");
        System.out.println("CAJERO ATM / BANCO: "+banco.toUpperCase());
        System.out.println("UBICADO EN: "+ubicacion.toUpperCase());
        System.out.println("FECHA: "+t.getFecha());
        if (!status) {
            System.out.println("TIPO DE TRANSACCION: CANCELACIÓN");
            System.out.println("SALDO INSUFICIENTE");
            System.out.println("ESTATUS: CANCELADA");
        } else {
            System.out.println("TIPO DE TRANSACCION: "+t.tipo.toUpperCase());
            System.out.println("ESTATUS: APROBADA");
        }
        System.out.println("FOLIO: "+t.getIDTransaccion());
        System.out.println("CUENTA: "+cta.getNumeroCuenta());
        System.out.println("CLIENTE: "+cta.cliente.nombre.toUpperCase());
        if (t.tipo.equals("Transferencia") && status) {
            System.out.println("CUENTA DESTINO: "+t.getCuentaDestino());
        }
        if (!status) {
            System.out.println("SALDO DISPONIBLE: $"+cta.getSaldo());
        } else {
            System.out.println("IMPORTE: $"+t.getMonto());
            System.out.println("SALDO: $"+cta.getSaldo());
        }
        System.out.println("---------- BBVA BANCOMER ----------\n");
    }
}