package Clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos López
 */
public class Interface extends JFrame
{
    private JLabel etiqueta;
    private JPanel panelPrincipal, panel1, panel2, panel3, panel4,panel5,panel6, panel7;
    private JButton boton[]= new JButton[13];
    private JButton botonDis, botonDep, botonRet, botonCerrar;
    private JTextField campoTexto2;
    private JPasswordField campoTexto1;
    private JMenuBar barra;
    private JMenu archivo, usuarios, ayuda;
    private JMenuItem cuentaNueva, cerrar, acercade, registro,guardar;
    private Banco bank = new Banco();
    private int clienteActual = -1, func = 0;
    private Cliente clienteTemporal;
    
    /*
    Constructor de la Clase
    */
    public Interface()
    {
        //Configuracion de la ventana
        this.setSize(900,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Bank ATM");
        setLocationRelativeTo(null);
        //Menu
        barra = new JMenuBar();
        formatoMenus();
        setJMenuBar(barra);
        //Panel Principal
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        this.getContentPane().add(panelPrincipal);
        //Paneles
        paneles();
        //Metodos de la interfaz
        botonbloq();
        oyente();
        bank.crearArchivoTexto();
        bank.leerArchivo();
    }
    
    /*
    Método para la creación y formato de los paneles
    */
    private void paneles()
    {
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(300, 0));
        panel1.setLayout(new GridLayout(2,1,5,4));
        panelPrincipal.add(panel1,BorderLayout.WEST);
        
        panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        panelPrincipal.add(panel6,BorderLayout.CENTER);
        
        panel7 = new JPanel();
        panel7.setLayout(new BorderLayout());
        panel7.setPreferredSize(new Dimension(0, 50));
        panel6.add(panel7,BorderLayout.SOUTH);
        formatoEtiquetas();
        
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,1,5,5));
        formatoBotonesFuncion();
        panel1.add(panel2);
        
        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel1.add(panel3);
        
        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(4,3,5,5));
        formatoBotonesTeclado();
        panel3.add(panel4,BorderLayout.CENTER);
        
        
        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(0, 50));
        panel5.setLayout(new GridLayout(1,1,5,5));
        panel3.add(panel5,BorderLayout.NORTH);
        formatoTextField();
        
        
        
    }
    /*
    Método para formato de los botones del teclado
    */
    private void formatoBotonesTeclado()
    {
        for(int i = 1; i < 13; i++)
        {
            boton[i] = new JButton();
            if(i==10)
            {
                boton[i].setText("0");
            }
            else if(i==11)
            {
                boton[i].setText("CLEAR");
            }
            else if(i==12)
            {
                boton[i].setText("ENTER");
            }
            else
            {
                boton[i].setText(""+i);
            }
            
            panel4.add(boton[i]);
        }
    }
    /*
    Método para formato de los botones de funcion
    */
    private void formatoBotonesFuncion()
    {
        botonDis = new JButton("Mostrar");
        panel2.add(botonDis);
        
        botonDep = new JButton("Depósito");
        panel2.add(botonDep);
        
        botonRet = new JButton("Retiro");
        panel2.add(botonRet);
        
        botonCerrar = new JButton("Salir de la cuenta");
        panel7.add(botonCerrar,BorderLayout.EAST);
        botonDis.setEnabled(false);
        botonDep.setEnabled(false);
        botonRet.setEnabled(false);
    }
    private void formatoTextField()
    {
        campoTexto1 = new JPasswordField();
        num();
        panel5.add(campoTexto1);
        
        campoTexto2 = new JTextField();
        campoTexto2.setEditable(false);
        panel7.add(campoTexto2,BorderLayout.CENTER);
    }
    
    private void formatoEtiquetas()
    {
        etiqueta = new JLabel("Ingrese su código");
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(Color.WHITE);
        etiqueta.setFont(new Font("Microsoft YaHei",Font.PLAIN,24));
        panel6.add(etiqueta,BorderLayout.CENTER);
        
    }
    
    private void formatoMenus()
    {
        archivo = new JMenu("Archivo");
        usuarios = new JMenu("Usuarios");
        ayuda = new JMenu("Ayuda");
        cuentaNueva = new JMenuItem("Crear Cuenta");
        cerrar = new JMenuItem("Salir");
        acercade = new JMenuItem("Acerca de");
        registro = new JMenuItem("Registro");
        guardar = new JMenuItem("Guardar");
        
        barra.add(archivo);
        barra.add(usuarios);
        barra.add(ayuda);
        usuarios.add(cuentaNueva);
        archivo.add(guardar);
        archivo.add(cerrar);
        ayuda.add(acercade);
        //usuarios.add(registro);
    }
    
    
    private void num()
    {
    campoTexto1.addKeyListener(new KeyAdapter()
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            char c = e.getKeyChar();
            if(!Character.isDigit(c) && c != '.')
            {
                e.consume();
            }
            if(c == '.' && campoTexto1.getText().contains("."))
            {
                e.consume();
            }
        }
    });
    }
    
    private void botonbloq()
    {
        if (campoTexto1.getText().isEmpty() || clienteActual > -1 && func == 0)
            {
                boton[12].setEnabled(false);
            }
        else
            {
                boton[12].setEnabled(true);
            }
    }
    
    private void oyente()
    {
        //"Escucha del teclado", utiliza el metodo botonbloq para bloquear el boton de comprobar si no hay nada en los TextFields
        KeyListener habilitador = new KeyListener() 
        {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            
            @Override
            public void keyReleased(KeyEvent e) {
            botonbloq();
            }
        };
        campoTexto1.addKeyListener(habilitador);
        
        
        //Escucha de los botones
        ActionListener clear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                campoTexto1.setText("");
                botonbloq();
            }
        };
        boton[11].addActionListener(clear);
        
        ActionListener Cerrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                botonDis.setEnabled(false);
                botonDep.setEnabled(false);
                botonRet.setEnabled(false);
                campoTexto1.setText("");
                etiqueta.setText("Ingrese su código");
                clienteActual=-1;
                clienteTemporal = null;
                func = 0;
                botonbloq();
            }
        };
        botonCerrar.addActionListener(Cerrar);
        
        ActionListener comprobante = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                
                if(func == 0)
                {
                int pinComp =  Integer.parseInt(campoTexto1.getText());
                clienteTemporal = bank.getClienteConPin(pinComp);
                campoTexto2.setText("");
                if(clienteTemporal != null)
                {
                    etiqueta.setText("Bienvenido " + clienteTemporal.getNombre() +" "+  clienteTemporal.getApellido());
                    clienteActual = 0;
                    botonDis.setEnabled(true);
                    botonDep.setEnabled(true);
                    botonRet.setEnabled(true);
                    campoTexto1.setText("");
                    boton[12].setEnabled(false);
                }
                else
                {
                    campoTexto2.setText("Pin incorrecto");
                }
                    
                }
                else if(func == 1)
                {
                    double saldo = Double.parseDouble(campoTexto1.getText());
                    clienteTemporal.getCuenta().deposito(saldo);
                    etiqueta.setText("<html><body>El deposito se ha realizado con exito.</body></html>" );
                    func = 0;
                }
                else if(func == 2)
                {
                    double saldo = Double.parseDouble(campoTexto1.getText());
                    if(saldo > clienteTemporal.getCuenta().getSaldo())
                    {
                        etiqueta.setText("<html><body>El monto a retirar es mayor al saldo disponible.</body></html>" );
                    }
                    else
                    {
                        clienteTemporal.getCuenta().retiro(saldo);
                        etiqueta.setText("<html><body>El retiro se ha realizado con exito.</body></html>" );
                    }
                }
                campoTexto1.setText("");
                botonbloq();
            }
        };
        boton[12].addActionListener(comprobante);
        
        ActionListener mostrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                etiqueta.setText("<html><body>Nombre: " + clienteTemporal.getNombre()+"<br>Apellido: " + clienteTemporal.getApellido() + "<br>Saldo actual: " + clienteTemporal.getCuenta().getSaldo()+ "</body></html>" );
                func = 0;
            }
        };
        botonDis.addActionListener(mostrar);
        
        ActionListener depositar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                etiqueta.setText("Ingrese la cantidad a depositar");
                func = 1;
            }
        };
        botonDep.addActionListener(depositar);
        
        
        ActionListener retirar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                etiqueta.setText("Ingrese la cantidad a retirar");
                func = 2;
            }
        };
        botonRet.addActionListener(retirar);
        
        
        for(int i = 1; i < 10; i++)
        {
            int k = i;
            ActionListener teclado = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String concatenador = campoTexto1.getText() + String.valueOf(k);
                campoTexto1.setText(concatenador + "");
                botonbloq();
            }
            };
            boton[i].addActionListener(teclado);
        }
        
        ActionListener cero = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                    int k = 0;
                    String concatenador = campoTexto1.getText() + String.valueOf(k);
                    campoTexto1.setText(concatenador + "");
                    botonbloq();
            }
            };
            boton[10].addActionListener(cero);
            
        //Escucha de los menus
        ActionListener m1 = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String name = "", lastname = "", cadenaPin = "0";
                name = JOptionPane.showInputDialog("Introduzca su nombre: ");
                lastname = JOptionPane.showInputDialog("Introduzca su apellido: ");
                cadenaPin = JOptionPane.showInputDialog("Introduzca su nuevo pin: ");
                if(name == null || lastname == null || cadenaPin == null || name.length() <= 0 || lastname.length() <= 0 || cadenaPin.length() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Error");
                }
                else
                {
                    int pin  = Integer.parseInt(cadenaPin);
                    boolean cap = bank.verificaPin(pin);
                    if(cap == true)
                    {
                        JOptionPane.showMessageDialog(null, "El pin ya ha sido utilizado");
                    }
                    else
                    {
                        bank.addClientes(name, lastname, pin);
                        JOptionPane.showMessageDialog(null, "La cuenta ha sido creada con exito");
                    }
                }
            }
            };
            cuentaNueva.addActionListener(m1);
        ActionListener m2 = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
            };
            cerrar.addActionListener(m2);
        
        ActionListener m3 = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, "Proyecto de Programación 3\nCarlos López\n2-750-1354\nLic. Ingeniería Informática - 2022","Acerca de",JOptionPane.PLAIN_MESSAGE);
            }
            };
            acercade.addActionListener(m3);
        
        ActionListener m5 = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                bank.escribirArchivo();
                campoTexto2.setText("Los datos fueron guardados de manera exitosa");
            }
            };
            guardar.addActionListener(m5);
            /*
            ActionListener m4 = new ActionListener() 
            {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JFrame ventanaTabla = new JFrame("Tabla de Usuarios");
                ventanaTabla.setBounds(0,0,500,500);
                ventanaTabla.setVisible(true);
                
                String [] columnas = {"Nombre", "Apellido", "pin", "saldo"};
                DefaultTableModel tabla1 = new DefaultTableModel();
                tabla1.setColumnIdentifiers(columnas);
                ventanaTabla.setModel()
            }
            };
            registro.addActionListener(m4);
            */
    }
}