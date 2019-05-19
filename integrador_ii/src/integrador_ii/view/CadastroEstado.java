package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import integrador_ii.services.EstadoService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroEstado extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtSigla;
	private JButton btnSalvar;
	private JButton btnCalcelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroEstado frame = new CadastroEstado();
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
	public CadastroEstado() {
		setTitle("Cadastro de Estado");
		setBounds(100, 100, 248, 175);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(12, 12, 66, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sigla:");
		lblNewLabel_1.setBounds(12, 49, 66, 15);
		getContentPane().add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(96, 10, 124, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtSigla = new JTextField();
		txtSigla.setBounds(96, 47, 124, 19);
		getContentPane().add(txtSigla);
		txtSigla.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EstadoService estadoService = new EstadoService();
				if(!estadoService.salvar(txtNome.getText(), txtSigla.getText().toUpperCase())) {
					JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar Estado.");
				}else {
					dispose();
				}
				
			}
		});
		btnSalvar.setBounds(12, 101, 90, 25);
		getContentPane().add(btnSalvar);
		
		btnCalcelar = new JButton("Calcelar");
		btnCalcelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCalcelar.setBounds(114, 101, 98, 25);
		getContentPane().add(btnCalcelar);

	}
}
