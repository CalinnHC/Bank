package Clases;

/**
 *
 * @author Carlos López
 */
public class Cuenta 
{
    private double saldo;
    
    public Cuenta(double monto)
    {
        saldo = monto;
    }
    public double getSaldo()
    {
        return saldo;
    }
    public void deposito(double monto)
    {
        saldo = saldo + monto;
    }
    public void retiro(double monto)
    {
        saldo = saldo - monto;
    }
}
