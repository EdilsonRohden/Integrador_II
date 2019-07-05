package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import integrador_ii.services.CidadeService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CadastroCidade extends JInternalFrame {
	private static final long serialVersionUID = 5973703882264062329L;
	private JTextField txtNome;
	private JTextField txtCdIbge;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCidade frame = new CadastroCidade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroCidade() {
		
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setResizable(true);
		
		setTitle("Cadastro de Cidade");
		setBounds(100, 100, 280, 177);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 66, 15);
		getContentPane().add(lblNome);
		
		JLabel lblCodigoIbge = new JLabel("Codigo IBGE:");
		lblCodigoIbge.setBounds(12, 39, 94, 15);
		getContentPane().add(lblCodigoIbge);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(12, 72, 66, 15);
		getContentPane().add(lblEstado);
		
		txtNome = new JTextField();
		txtNome.setBounds(113, 10, 124, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCdIbge = new JTextField();
		txtCdIbge.setBounds(113, 37, 124, 19);
		getContentPane().add(txtCdIbge);
		txtCdIbge.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(113, 67, 124, 19);
		CidadeService cidadeService = new CidadeService();
		model = cidadeService.getSiglas();
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CidadeService cidadeService = new CidadeService();
				if (!cidadeService.salvar(txtNome.getText(), txtCdIbge.getText(), model.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Não foi possivel realizar operação!");
				}else {
					dispose();
				}
			}
		});
		btnSalvar.setBounds(12, 108, 99, 25);
		getContentPane().add(btnSalvar);
		
		JButton btnCalcelar = new JButton("Calcelar");
		btnCalcelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCalcelar.setBounds(143, 108, 94, 25);
		getContentPane().add(btnCalcelar);

	}
}
