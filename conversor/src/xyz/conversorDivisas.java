package xyz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;

public class conversorDivisas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox cmb;
	private JTextField txt;
	private JLabel lbl;
	private JButton btn;
	
	public enum Moneda{
		soles_dolar,
		soles_euros,
		soles_librasesterlinas,
		soles_yenjapones,
		soles_wonsurcoreano,		
		dolar_soles,
		euros_soles,
		librasesterlinas_soles,
		yenjapones_soles,
		wonsurcoreano_soles,		
	}
	
	public double dolar = 3.73;
	public double euros = 3.97;
	public double librasesterlinas = 4.57;
	public double yenjapones = 0.025;
	public double wonsurcoreano = 0.0028;
	
	public double valorInput = 0.00;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conversorDivisas frame = new conversorDivisas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public conversorDivisas() {
		setTitle("Conversor");
		setForeground(new Color(208, 245, 167));
		setFont(new Font("Georgia", Font.BOLD, 18));
		setBackground(new Color(42, 42, 71));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(54, 73, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(26, 108, 195, 38);
		contentPane.add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmb.setForeground(Color.WHITE);
		cmb.setBackground(new Color(179, 164, 241));
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(26, 172, 195, 38);
		contentPane.add(cmb);
		
		btn = new JButton("Convertir");
		btn.setBackground(new Color(136, 160, 236));
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(26, 236, 195, 46);
		contentPane.add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBackground(new Color(136, 160, 236));
		lbl.setForeground(new Color(255, 255, 255));
		lbl.setBounds(27, 302, 194, 46);
		contentPane.add(lbl);
		
		JLabel Title = new JLabel("Conversor");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(new Color(208, 245, 167));
		Title.setFont(new Font("Segoe UI", Font.BOLD, 22));
		Title.setBackground(new Color(136, 160, 236));
		Title.setBounds(27, 38, 194, 46);
		contentPane.add(Title);
	}
	
	public void Convertir() {
		if (Validar(txt.getText())) {
			Moneda  moneda = (Moneda) cmb.getSelectedItem();
			switch (moneda) {
			
			case soles_dolar:
				SolesAMoneda(dolar);
				break;
			case soles_euros:
				SolesAMoneda(euros);
				break;
			case soles_librasesterlinas:
				SolesAMoneda(librasesterlinas);
				break;
			case soles_yenjapones:
				SolesAMoneda(yenjapones);
				break;
			case soles_wonsurcoreano:
				SolesAMoneda(wonsurcoreano);
				break;
			case dolar_soles:
				MonedaASoles(dolar);
				break;	
			case euros_soles:
				MonedaASoles(euros);
				break;	
			case librasesterlinas_soles:
				MonedaASoles(librasesterlinas);
				break;	
			case yenjapones_soles:
				MonedaASoles(yenjapones);
				break;	
			case wonsurcoreano_soles:
				MonedaASoles(wonsurcoreano);
				break;	
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + moneda);
		}			
			

		}
	}
	
	public void SolesAMoneda(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res)); 
 	}
	
	public void MonedaASoles(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res)); 
	}
	
	public String Redondear (double valor){
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}	
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solamente números");
			return false;		
		}
	}
}






