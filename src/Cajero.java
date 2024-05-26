import java.security.SecureRandom;
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

    /*MÉTODO PARA RETIRAR
     * Recibe de parámetros:
        1. Cuenta del cliente que hará el retiro
        2. Tipo de cuenta
        3. Monto a retirar
    *Compara si el monto a retirar es mayor al saldo disponible,
        envia un recibo de cancelacion de la operacion y finaliza.
    *Si el monto es menor, resta del saldo disponible el monto a
        retirar e imprime el ticket.
     */
    public void Retirar(Cuenta cuenta, String tipo, double monto) {
        Transaccion retiro = new Retiro(generarID("R"), LocalDate.now(), monto);
        if (monto > cuenta.saldo) {
            System.out.println("\nLo sentimos, no dispone de saldo suficiente");
            imprimirTicket(retiro, cuenta, false);
        } else {
            cuenta.saldo -= monto;
            imprimirTicket(retiro, cuenta, true);
        }
    }

    /*MÉTODO PARA TRANSFERIR
     * Recibe de parámetros:
        1. Cuenta del cliente que hará la transferencia
        2. Cuenta a quien se dirige la transferencia
        3. Tipo de cuenta
        4. Monto a retirar
    *Compara si el monto a transferir es mayor al saldo disponible,
        envia un recibo de cancelacion de la operacion y finaliza.
    *Si el monto es menor, resta del saldo disponible el monto a
        retirar, se lo suma a la cuenta destino e imprime el ticket.
     */
    public void Transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, String tipo, double monto) {
        Transaccion transferir = new Transferencia(generarID("T"), LocalDate.now(), monto, cuentaDestino);
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

    /*METODO PARA IMPRIMIR EL TICKET
        variable booleana indica el estado de la transaccion:
            'true'  Transaccion     'false' Cancelacion
        Dependieno del valor booleano, imprime las líneas de
        texto requeridas.
        Muestra datos de la transaccion, cliente, cajero y el monto.
     */
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

    /*GENERADOR DE FOLIOS
        Método que retorna un string del folio.
        Utiliza un generador aleatorio de caracteres para formar
        cadenas alfanuméricas de 9 digitos.
        La estructura del folio es:
            -1er caracter: Inicial de la transacción
                'T' transferencia     'R' retiro
            -Resto de caracteres: generador aleatorio
     */
    SecureRandom random = new SecureRandom();
    public String generarID(String tran) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder resultado = new StringBuilder(9);
        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(caracteres.length());
            resultado.append(caracteres.charAt(index));
        }
        return tran+resultado.toString();
    }
}