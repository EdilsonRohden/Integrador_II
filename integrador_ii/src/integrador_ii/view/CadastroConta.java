package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;


import integrador_ii.models.Conta;
import integrador_ii.services.ContaService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroConta extends JInternalFrame {
	private static final long serialVersionUID = 3426010029033819751L;
	private JTextField txtIdS;
	private JTextField txtDescricaoS;
	private JTextField txtId;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroConta frame = new CadastroConta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroConta() {
		setTitle("Cadastro de Contas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		ContaService contaService = new ContaService();
		List<Conta> contas = contaService.getContasPrimeiroNivel();
		for (Conta conta : contas) {
			model.addElement(conta.getIdP()+"-"+conta.getDescricaoP());
		}
		
		
		JLabel lblPrimeiroNivel = new JLabel("Primeiro Nivel:");
		lblPrimeiroNivel.setBounds(12, 42, 106, 15);
		getContentPane().add(lblPrimeiroNivel);
		
		JLabel lblSegundoNivel = new JLabel("Segundo Nivel:");
		lblSegundoNivel.setBounds(12, 73, 106, 15);
		getContentPane().add(lblSegundoNivel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(136, 37, 261, 20);
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		txtIdS = new JTextField();
		txtIdS.setBounds(136, 71, 50, 19);
		getContentPane().add(txtIdS);
		txtIdS.setColumns(10);
		
		txtDescricaoS = new JTextField();
		txtDescricaoS.setBounds(198, 71, 199, 19);
		getContentPane().add(txtDescricaoS);
		txtDescricaoS.setColumns(10);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setBounds(12, 12, 92, 15);
		getContentPane().add(lblIdentificador);
		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if(!txtId.getText().isEmpty()) {
						int id = Integer.parseInt(txtId.getText());
						Conta conta = contaService.getContaById(id);
						
						txtDescricaoS.setText(conta.getDescricaoS());
						txtIdS.setText(Integer.toString(conta.getIdS()));
						model.setSelectedItem(conta.getIdP()+"-"+conta.getDescricaoP());						
					}
					
				}catch(Exception exeption) {}
			}
		});
		txtId.setBounds(136, 10, 64, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtId.getText();
				String idP = model.getSelectedItem().toString().split("-")[0];
				String idS = txtIdS.getText();
				String descricaoS = txtDescricaoS.getText();
				
				if(idP.isEmpty() || idS.isEmpty() || descricaoS.isEmpty()) {
					JOptionPane.showMessageDialog(getContentPane(), "Dados invalidos!");
				}else {					
					contaService.salvar(id, idP, idS, descricaoS);
					dispose();
				}
				
			}
		});
		btnSalvar.setBounds(12, 219, 114, 25);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(283, 219, 114, 25);
		getContentPane().add(btnCancelar);

	}

}











