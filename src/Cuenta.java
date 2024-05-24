public class Cuenta {
    //Atributos
    String tipo;
    Cliente cliente;
    String numeroCuenta;

    //Constructor
    public Cuenta (String tipo, Cliente cliente, String numeroCuenta) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.numeroCuenta = numeroCuenta;
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
}
