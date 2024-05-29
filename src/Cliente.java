public class Cliente {
    //Atributos
    private String ID;
    private String nombre;
    private String direccion;

    //Constructor
    public Cliente (String ID, String nombre, String direccion) {
        this.ID = ID;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    //Métodos Get/Set
    public String getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setID(String id) {
        ID = id;
    }

    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setDireccion(String dir) {
        direccion = dir;
    }
}
