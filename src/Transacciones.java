import java.util.Date;

public abstract class Transacciones {
    //Atributos
    String IDTransaccion;
	Date fecha;
    String tipo;
    double monto;

    //Constructor
    public Transacciones(String IDTransaccion, Date fecha, String tipo, double monto) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.IDTransaccion = IDTransaccion;
        this.monto = monto;
    }

    public void Operaciones() {
        int bandera = 0;
        int seleccion = 0;
        String Nombre = "";
        do {
            do {
                System.out.println(" Buenas tardes esta en un cajero automatico de BBVA");
                System.out.println(" Hoy es 31/10/2023");
                System.out.println(" Porfavor seleccione una opción:");
                System.out.println("    1. Consulta de saldo.");
                System.out.println("    2. Retiro de efectivo.");
                System.out.println("    3. Deposito de efectivo.");
                System.out.println("    4. Hacer una transferencia");
                System.out.println("    5. Salir.");
                //seleccion = entrada.nextInt();

                if (seleccion >= 1 && seleccion <= 5) {
                    bandera = 1;
                } else {
                    System.out.println("=================================================");
                    System.out.println("Opción no disponible, vuelva a intentar porfavor.");
                    System.out.println("=================================================");
                }
            } while (bandera == 0);
            
            if(seleccion == 1){
            	Transacciones mensajero = new Consulta();
                mensajero.Transacciones();
            }else if(seleccion == 2){
            	Transacciones mensajero = new Retiro();
                mensajero.Transacciones();
            } else if(seleccion == 3){
            	Transacciones mensajero = new Deposito();
                mensajero.Transacciones();
            } else if(seleccion == 4){
            	Transacciones mensajero = new Transferencia();
                mensajero.Transacciones();
            } else if(seleccion == 5){
                System.out.println("==========================");
                System.out.println("Gracias, vuelva pronto.");
                System.out.println("==========================");
                bandera = 2;
            }
        } while (bandera != 2);
    }

    //Métodos Get
    public String getIDTransaccion() {
        return IDTransaccion;
    } 
    
    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }
}
