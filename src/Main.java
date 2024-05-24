/*---------------Proyecto Cajero Automático--------------
Crear proyecto para simular funcionalidad de cajero automático con las siguientes características:
    1.-Del cajero automático nos interesa conocer su localización y banco al que pertenece. 
    2.-En el cajero automático se podrán hacer solo dos transacciones Retirar y Transferir, 
        de las cuales se debe registrar identificador de la transacción, fecha, tipo 
        (Retiro, Transferencia) y monto, en el caso de las transferencias también se requiere 
        registrar la cuenta destino.
    3.-Para poder realizar alguna transacción en el cajero automático los clientes del banco al que pertenece 
        el cajero deben tener una tarjeta de débito, del cliente se requiere registrar un identificador, nombre 
        y dirección, de la tarjeta de débito la cuenta y el cliente dueño de dicha tarjeta. 
    4.-De las cuentas de los clientes se debe registrar el tipo (Cuenta de Ahorro o Cuenta de cheques.), cliente 
        y número de cuenta.
    5.-Crear diagrama de clases y posteriormente las clases en java, 
    6.-Todas la clases creadas deben tener sus constructores y métodos Get y Set.
    7.-Crear método para Retirar el cual debe recibir como parámetro cuenta, tipo y monto.
    8.-Crear método para Transferir el cual debe recibir como parámetro cuenta origen, cuenta destino, tipo y monto.
    9.-Crear método para imprimir ticket de la transacción en donde muestre los datos del cliente, banco, 
        tipo de transacción y monto.
    10.-Crear clase Main para mostrar la funcionalidad del cajero, el programa debe simular el proceso de un cajero para 
        las dos transacciones disponibles desde solicitar el ingreso de la tarjeta, seleccionar tipo de cuenta(Cuenta de Ahorro
        o Cuenta de cheques),  transacción a realizar (Retiro, Transferencia) y monto, así como imprimir el ticket con el 
        resumen de la transacción (Punto 8).
    11.-El proyecto debe tener por lo menos una clase abstracta, herencia de dicha clase y una interface.*/
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
        //Cajero
        Cajero atm = new Cajero("Av. Álvaro Obregón #258", "BBVA");
        //Clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cte01 = new Cliente("20170859", "Nitza Vega", "Bahía de Altamira #1616");
        Cliente cte02 = new Cliente("17170599", "Juan Ramirez", "República #258");
        Cliente cte03 = new Cliente("19170741", "María Soto", "Pascual Orozco #987");
        clientes.add(cte01);    clientes.add(cte02);    clientes.add(cte03);
        //Cuentas
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta cta01 = new Cuenta("Cuenta de Ahorro", cte01, "9876543210", 2500);
        Cuenta cta02 = new Cuenta("Cuenta de Cheques", cte02, "1234567890", 3200);
        Cuenta cta03 = new Cuenta("Cuenta de Ahorro", cte03, "6549873210", 55);
        Cuenta cta04 = new Cuenta("Cuenta de Cheques", cte01, "9638527410", 125);
        cuentas.add(cta01); cuentas.add(cta02); cuentas.add(cta03); cuentas.add(cta04);
        //Tarjetas
        ArrayList<TarjetaDebito> tarjetas = new ArrayList<>();
        TarjetaDebito tar01 = new TarjetaDebito(cta01, cte01);
        TarjetaDebito tar02 = new TarjetaDebito(cta02, cte02);
        TarjetaDebito tar03 = new TarjetaDebito(cta03, cte03);
        TarjetaDebito tar04 = new TarjetaDebito(cta04, cte01);
        tarjetas.add(tar01);    tarjetas.add(tar02);    tarjetas.add(tar03);    tarjetas.add(tar04);
        
        //Pantalla del cajero
        System.out.println("-----------------------------------------");
        System.out.println("Bienvenido al cajero automático "+atm.banco);
        System.out.println("Ingrese su número de cuenta: ");
        String numCuenta = entrada.nextLine();
        //System.out.println("Ingrese su NIP: ");
        //int nip = entrada.nextInt();
        Cuenta cuentaOr = null;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.numeroCuenta.equals(numCuenta)) {
                System.out.println("-----------------------------------------");
                System.out.println("Bienvenido "+cuenta.cliente.nombre);
                cuentaOr = cuenta;
                break;
            }
        }
        System.out.println("Seleccione el tipo de transacción:");
        System.out.println("1. Transferencia");
        System.out.println("2. Retiro de Efectivo");
        int op = entrada.nextInt();
        System.out.print("Ingrese el monto: ");
        double monto = entrada.nextDouble();
        if (op == 1) {
            System.out.print("Ingrese el número de cuenta de destino: ");
            String cuentaDestino = entrada.nextLine();
            for (Cuenta cuenta : cuentas) {
                if (cuenta.numeroCuenta.equals(cuentaDestino)) {
                    atm.Transferir(cuentaOr, cuenta, cuenta.tipo, monto);
                    break;
                }
            }
        } else if (op == 2)  {
            atm.Retirar(cuentaOr, cuentaOr.tipo, monto);
        } else {
            System.out.println("Opción no válida... Saliendo");
        }
    }
}