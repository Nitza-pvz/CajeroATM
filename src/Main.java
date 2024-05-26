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
    static Cajero atm;
    static ArrayList<Cliente> clientes;
    static ArrayList<Cuenta> cuentas;
    static ArrayList<TarjetaDebito> tarjetas;
    static boolean estado = true;

    /*PASOS DEL CAJERO AUTOMATICO
            1. Ingreso del numero de cuenta.
            2. Ingreso del tipo de cuenta (Cuenta de Cheques o Cuenta de Ahorros).
            3. Selección del tipo de Transaccion:
                3.0. Ingresa el monto
                3.1. Retira el dinero en efectivo
                3.2. Ingresa la cuenta destino e inicia transferencia.
            4. Imprime ticket
            5. Finaliza y regresa al paso 1.
    */
    public static void main(String[] args) {
        //DATOS PRECARGADOS
        init();
        String numCuenta;
        /*  Ciclo que mantiene actividad en el cajero.Se mantendrá
            activo y repetitivo para el siguiente cliente. Dado que
            el cliente cometa algun error, simplemente pasa a la
            siguiente iteracion del ciclo.         
         */
        do {
            //PANTALLA INICIAL DEL CAJERO
            System.out.println("-----------------------------------------");
            System.out.println("Bienvenido al cajero automático "+atm.banco);
            //Ingreso del número de cuenta
            System.out.println("Ingrese su número de cuenta: ");
            numCuenta = entrada.next();
            //Selección del tipo de cuenta
            System.out.println("Seleccione el tipo de cuenta: ");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta de Cheques");
            int opTipo = 0;
            String tipoCta = "";
            /*Ciclo para que solo tenga las dos opciones que se presentan.
             *Segun la opcion elegida, la almacena en un String para buscar
                si existe.
            */
            do {
                opTipo = entrada.nextInt();
                if (opTipo == 1)
                    tipoCta = "Cuenta de Ahorro";
                if (opTipo == 2)
                    tipoCta = "Cuenta de Cheques";
            } while (opTipo != 1 && opTipo != 2);
        //Ingreso del NIP para acceso a la cuenta
        //System.out.println("Ingrese su NIP: ");
        //int nip = entrada.nextInt();

            /* DETERMINAR SI LA CUENTA EXISTE
                    Instancia de la clase Cuenta que llama al método
                    buscarCuenta(Cuenta), para verificar si existe.
                    Si existe, muestra el nombre y saldo disponible del cliente.
                    Sino, indica que no existe al cliente y rompe la iteracion
                    del ciclo para dar lugar a otra entrada de datos al cliente.
            */
            Cuenta cuentaOr = buscarCuenta(numCuenta);
            if (cuentaOr == null) {
                System.out.println("Su cuenta no existe\nPorfavor intentelo de nuevo\n\n");
                continue;
            }
            System.out.println("-----------------------------------------");
            System.out.println("Bienvenido "+cuentaOr.cliente.nombre);
            System.out.println("SALDO: $"+cuentaOr.saldo);

            /*  SELECCION DEL TIPO DE TRANSACCION
                    Dado que en ambas transacciones debe ingresar un monto,
                    se ingresa primero antes de llamar al método.
                        1. Transferencia. Ingresa la cuenta destino y el ciclo
                        determina si existe, para llamar al método Tranferir de
                        Cajero. En caso contrario, cancela el proceso.
                        2. Retiro. Simplemente llama al método de Retirar de la
                        clase Cajero.
                        ?. Cancela el proceso.
            */
            System.out.println("Seleccione el tipo de transacción:");
            System.out.println("1. Transferencia");
            System.out.println("2. Retiro de Efectivo");
            int op = entrada.nextInt();
            if (op != 1 && op != 2) {
                System.out.println("\nOpción no válida... Saliendo\n\n");
                continue;
            }
            //Ingreso del monto
            System.out.print("Ingrese el monto: ");
            double monto = entrada.nextDouble();
            if (op == 1) {
                System.out.println("Ingrese el número de cuenta de destino: ");
                String cuentaDestino = entrada.next();
                Cuenta ctaDestino = buscarCuenta(cuentaDestino);
                if (ctaDestino == null) {
                    System.out.println("La cuenta no existe... Saliendo\n\n");
                    continue;
                }
                //System.out.println("Saldo antes: "+ctaDestino.saldo);
                atm.Transferir(cuentaOr, ctaDestino, ctaDestino.tipo, monto);
                //System.out.println("Saldo despues: "+ctaDestino.saldo);
            }
            if (op == 2)  {
                atm.Retirar(cuentaOr, cuentaOr.tipo, monto);
            }
        } while (estado);
        entrada.close();
    }

    public static Cuenta buscarCuenta(String numCuenta) {
        /*Método que verifica si la cuenta existe.
         *Retorna la instancia de la cuenta. */
        Cuenta cuentaOr = null;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.numeroCuenta.equals(numCuenta)) {
                cuentaOr = cuenta;
                break;
            }
        }
        return cuentaOr;
    }

    public static void init() {
        /*Se inicializan datos precargados para las pruebas*/
        //Cajero
        atm = new Cajero("Av. Álvaro Obregón #258", "BBVA");
        //Clientes
        clientes = new ArrayList<>();
        Cliente cte01 = new Cliente("20170859", "Nitza Vega", "Bahía de Altamira #1616");
        Cliente cte02 = new Cliente("17170599", "Juan Ramirez", "República #258");
        Cliente cte03 = new Cliente("19170741", "María Soto", "Pascual Orozco #987");
        clientes.add(cte01);    clientes.add(cte02);    clientes.add(cte03);
        //Cuentas
        cuentas = new ArrayList<>();
        Cuenta cta01 = new Cuenta("Cuenta de Ahorro", cte01, "9876543210", 2500);
        Cuenta cta02 = new Cuenta("Cuenta de Cheques", cte02, "1234567890", 3200);
        Cuenta cta03 = new Cuenta("Cuenta de Ahorro", cte03, "6549873210", 505);
        Cuenta cta04 = new Cuenta("Cuenta de Cheques", cte01, "9876543210", 125);
        cuentas.add(cta01); cuentas.add(cta02); cuentas.add(cta03); cuentas.add(cta04);
        //Tarjetas
        tarjetas = new ArrayList<>();
        TarjetaDebito tar01 = new TarjetaDebito(cta01, cte01);
        TarjetaDebito tar02 = new TarjetaDebito(cta02, cte02);
        TarjetaDebito tar03 = new TarjetaDebito(cta03, cte03);
        TarjetaDebito tar04 = new TarjetaDebito(cta04, cte01);
        tarjetas.add(tar01);    tarjetas.add(tar02);    tarjetas.add(tar03);    tarjetas.add(tar04);
    }
}