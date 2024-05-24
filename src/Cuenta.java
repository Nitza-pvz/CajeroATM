public class Cuenta {
    //Atributos
    String tipo;
    Cliente cliente;
    String numeroCuenta;
    double saldo;

    //Constructor
    public Cuenta (String tipo, Cliente cliente, String numeroCuenta, double saldo) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    //Métodos Get/Set
    public String getTipo() {
        return tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double s) {
        saldo = s;
    }
}
