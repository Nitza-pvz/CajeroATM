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
import java.util.Scanner;

public class Main {
	static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------");
        System.out.println("Ingrese su Nombre:	(Catalina)");
        String Nombre = entrada.nextLine();
        System.out.println("Ingrese su Numero de cuenta:	(23140972)");
        String Num_cuenta = entrada.nextLine();
        System.out.println("Ingrese su Nip:		(8002)");
        int Nip = entrada.nextInt();
        System.out.println("----------------------------------------------------");
        if(Nombre.equals("Catalina") && Num_cuenta.equals("23140972") && Nip==8002) {
        	System.out.println("===============================================");
        	System.out.print(Nombre);
            Clase_Abstractaa mesajero = new Consulta();
            mesajero.setSaldo(1700);
            mesajero.Operaciones();
        }else {
        	System.out.println("===============================================");
            System.out.println("Alguno de sus datos es erroneo, intente denuevo");
        	System.out.println("===============================================");
   
        }
    }
    public String getNombre(){
        return getNombre();
    }
}