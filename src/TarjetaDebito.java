public class TarjetaDebito {
    //Atributos
    Cuenta cuenta;
    Cliente cliente;

    //Constructor
    public TarjetaDebito(Cuenta cuenta, Cliente cliente) {
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    //Métodos Get/Set
    public Cuenta getCuenta() {
        return cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCuenta(Cuenta cta) {
        cuenta = cta;
    }

    public void setCliente(Cliente cte) {
        cliente = cte;
    }
}
