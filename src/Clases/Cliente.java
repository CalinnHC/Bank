package Clases;

/**
 *
 * @author Carlos López
 */
public class Cliente 
{
    
    private String nombre;
    private String apellido;
    private int pin;
    private Cuenta cuenta;
    public int numeroDeCliente;
    public Cliente siguiente;
    private Cliente (String n, String a, int p, Cuenta c, int nC)
    {
        this.nombre = n;
        this.apellido = a;
        this.pin = p;
        this.cuenta = c;
        this.numeroDeCliente = nC;
    }
    //Inserta al inicio
    public Cliente(String n, String a, int p, int nC, Cliente cl)
    {
        this.nombre = n;
        this.apellido = a;
        this.pin = p;
        this.cuenta = new Cuenta(0);
        this.numeroDeCliente = nC;
        this.siguiente = cl;
    }
    public Cliente(String n, String a, int p, int nC, Cliente cl, double s)
    {
        this.nombre = n;
        this.apellido = a;
        this.pin = p;
        this.cuenta = new Cuenta(s);
        this.numeroDeCliente = nC;
        this.siguiente = cl;
    }
    public String getNombre()
    {
        return this.nombre;
    }
    public int getPin()
    {
        return this.pin;
    }
    public int getNumeroDeCliente()
    {
        return this.numeroDeCliente;
    }
    public String getApellido()
    {
        return this.apellido;
    }
    public Cuenta getCuenta()
    {
        return this.cuenta;
    }
    /*public String [] getDatos()
    {
        
        String n = nombre;
        String a = apellido;
        String p = String.valueOf(pin);
        String s = String.valueOf(cuenta.getSaldo());
        String [] data = {n,a,p,s};
        return data;
    }
    */
}
