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

        //Variables locales
        String numCuenta, tipoCta = "";
        int opTran, opTipo;

        /*  Ciclo que mantiene actividad en el cajero.Se mantendrá
            activo y repetitivo para el siguiente cliente. Dado que
            el cliente cometa algun error, simplemente pasa a la
            siguiente iteracion del ciclo.         
         */
        do {
            //PANTALLA INICIAL DEL CAJERO
            System.out.println("-----------------------------------------");
            System.out.println("Bienvenido al cajero automático "+atm.getBanco());
            //Ingreso del número de cuenta
            System.out.println("Ingrese su número de cuenta: ");
            numCuenta = entrada.next();
            //Selección del tipo de cuenta
            System.out.println("Seleccione el tipo de cuenta: ");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta de Cheques");
            opTipo = 0;
            
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
            Cuenta cuentaOr = buscarCuenta(numCuenta, tipoCta);
            if (cuentaOr == null) {
                System.out.println("Su cuenta no existe\nPorfavor intentelo de nuevo\n\n");
                continue;
            }
            System.out.println("-----------------------------------------");
            System.out.println("Bienvenido "+cuentaOr.getCliente().getNombre());
            System.out.println("SALDO: $"+cuentaOr.getSaldo());

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
            opTran = entrada.nextInt();
            if (opTran != 1 && opTran != 2) {
                System.out.println("\nOpción no válida... Saliendo\n\n");
                continue;
            }
            //Ingreso del monto
            System.out.print("Ingrese el monto: ");
            double monto = entrada.nextDouble();
            if (opTran == 1) {
                System.out.println("Ingrese el número de cuenta de destino: ");
                String cuentaDestino = entrada.next();
                Cuenta ctaDestino = buscarCuenta(cuentaDestino);
                if (ctaDestino == null) {
                    System.out.println("La cuenta no existe... Saliendo\n\n");
                    continue;
                }
                atm.Transferir(cuentaOr, ctaDestino, ctaDestino.getTipo(), monto);
            }
            if (opTran == 2)  {
                atm.Retirar(cuentaOr, cuentaOr.getTipo(), monto);
            }
            System.out.println("\nGracias por visitarnos, que tenga un buen día :)\n\n");
        } while (estado);
        entrada.close();
    }

    public static Cuenta buscarCuenta(String numCuenta) {
        /*Método que verifica si la cuenta existe.
         *Retorna la instancia de la cuenta.
         *SOLO determina si en Número de Cuenta exite.
         */
        Cuenta cuentaOr = null;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                cuentaOr = cuenta;
                break;
            }
        }
        return cuentaOr;
    }

    public static Cuenta buscarCuenta(String numCuenta, String tipoCta) {
        /*Método que verifica si la cuenta existe.
         *Retorna la instancia de la cuenta. 
         *Identifica si existe con el Número de Cuenta
            y si coincide con el Tipo de Cuenta*/
        Cuenta cuentaOr = null;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                if (cuenta.getTipo().equals(tipoCta)) {
                    cuentaOr = cuenta;
                    break;
                }
            }
        }
        return cuentaOr;
    }

    public static void init() {
        /*Se inicializan datos precargados*/
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