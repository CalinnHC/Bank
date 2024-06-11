package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Carlos López
 */
public class Banco 
{
    private Cliente inicio, fin;
    private int numeroDeClientes = 0;
    private File archivo;
    
    public Banco()
    {
        inicio = null;
        fin = null;
    }
    public void addClientes(String nombre, String apellido, int pin)
    {
        inicio = new Cliente(nombre, apellido, pin, numeroDeClientes,inicio);
        if(fin == null)
        {
            fin = inicio;
        }
        numeroDeClientes++;
    }
    
    public int getnumeroDeClientes()
    {
        return numeroDeClientes;
    }
    public Cliente getClienteConIndice(int indice)
    {
        Cliente recorre = inicio;
        while (recorre!= null || recorre.getNumeroDeCliente() == indice)
        {
             recorre = recorre.siguiente;
        }
        if(recorre.getNumeroDeCliente() == indice)
        {
            return recorre;
        }
        else
        {
        }
        return recorre;
    }
    public Cliente getClienteConPin(int pinAux)
    {
        
        if(numeroDeClientes != 0)
        {
            Cliente recorre = inicio;
            while (recorre != null && recorre.getPin() != pinAux)
                {
                    recorre = recorre.siguiente;
                }
            if(recorre == null)
            {
                return null;
            }
            else if(recorre.getPin() == pinAux)
                {
                    return recorre;
                }
            else
                {
                    System.out.println("Estado desconocido");
                    return null;
                }
        }
        return null;
    }
    public boolean verificaPin(int pin)
    {
        boolean c = false;
        if(getClienteConPin(pin) == null)
        {
            c = false;
            return c;
        }
        else
        {
           c = true; 
           return c;
        }
    }
    
    public void crearArchivoTexto()
    {
        archivo = new File("Datos.txt");
        try
        {
            if(archivo.createNewFile())
            {
                System.out.println("Archivo creado con exito");
            }
            else
            {
                System.out.println("Error al crear archivo");
            }
        }
        catch(IOException excepcion)
        {
            excepcion.printStackTrace(System.out);
        }
    }
    public void escribirArchivo()
    {
        try
        {
            Cliente recorre = inicio;
            FileWriter escritura = new FileWriter("Datos.txt", false);
            while (recorre != null)
                {escritura.write("\n" + recorre.getNombre() + ";" + recorre.getApellido() + ";" + recorre.getPin() + ";" + recorre.getCuenta().getSaldo());
                    recorre = recorre.siguiente;
                    
                }
            escritura.close();
        }
        catch(IOException excepcion)
        {
            excepcion.printStackTrace(System.out);
        }
    }
    public void leerArchivo()
    {
        try
        {
            String contenido;
            FileReader lector = new FileReader("Datos.txt");
            BufferedReader lectura = new BufferedReader(lector);
            if(lectura.readLine() != null)
            {
                contenido = lectura.readLine();
                while(contenido != null)
                {
                    
                    System.out.println(contenido + "");
                    String n;
                    String a;
                    int p;
                    double s;
                    StringTokenizer token = new StringTokenizer(contenido,";" );
                    n = token.nextToken();
                    a = token.nextToken();
                    p = Integer.parseInt(token.nextToken());
                    s = Double.parseDouble(token.nextToken());
                    inicio = new Cliente(n, a, p, numeroDeClientes,inicio,s);
                    if(fin == null)
                    {
                        fin = inicio;
                    }
                    numeroDeClientes++;
                    contenido = lectura.readLine();
                }
            }
        }
        catch(IOException excepcion)
        {
            excepcion.printStackTrace(System.out);
        }
    }
    
}
