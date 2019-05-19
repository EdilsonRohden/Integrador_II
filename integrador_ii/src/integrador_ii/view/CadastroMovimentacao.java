package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import integrador_ii.models.Cliente;
import integrador_ii.models.Conta;
import integrador_ii.models.Movimento;
import integrador_ii.services.ClienteService;
import integrador_ii.services.ContaService;
import integrador_ii.services.MovimentoService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroMovimentacao extends JInternalFrame {
	private JTextField txrIdCliente;
	private JTextField txtData;
	private JTextField txtValor;
	private DefaultComboBoxModel<String> modelConta = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMovimentacao frame = new CadastroMovimentacao();
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
	public CadastroMovimentacao() {
		setTitle("Cadastro de Movimentação");
		setBounds(100, 100, 362, 300);
		getContentPane().setLayout(null);
		
		MovimentoService mvService = new MovimentoService();
		List<Movimento> movimentos = mvService.getMovimentos();
		
		ClienteService clienteService = new ClienteService();
		List<Cliente> clientes = clienteService.getClientes();
		
		for (Cliente cliente : clientes) {
			modelCliente.addElement(cliente.getNome());
		}
		
		ContaService contaService = new ContaService();
		List<Conta> contas = contaService.getContas();
		
		for (Conta conta : contas) {
			modelConta.addElement(conta.getIdP()+"."+conta.getIdS()+"-"+conta.getDescricaoS());
		}
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(12, 12, 66, 15);
		getContentPane().add(lblCliente);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(12, 75, 66, 15);
		getContentPane().add(lblData);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(12, 106, 66, 15);
		getContentPane().add(lblValor);
		
		txrIdCliente = new JTextField();
		txrIdCliente.setBounds(96, 10, 66, 19);
		getContentPane().add(txrIdCliente);
		txrIdCliente.setColumns(10);
		
		JComboBox comboBoxCliente = new JComboBox();
		comboBoxCliente.setBounds(183, 3, 157, 24);
		comboBoxCliente.setModel(modelCliente);
		getContentPane().add(comboBoxCliente);
		
		txtData = new JTextField();
		txtData.setBounds(96, 75, 124, 19);
		getContentPane().add(txtData);
		txtData.setText((new Date()).toString());
		txtData.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(96, 106, 124, 19);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					long dataMovimento = Date.parse(txtData.getText());
					double valor = Double.parseDouble(txtValor.getText());
					String nome = modelCliente.getSelectedItem().toString();
					int idCliente;
					for (Cliente cliente : clientes) {
						if(cliente.getNome().equals(nome)) {
							idCliente = cliente.getId();
						}
					}
					
					
				}catch (Exception e) {
					
				}
			}
		});
		btnSalvar.setBounds(12, 231, 114, 25);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(226, 228, 114, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblConta = new JLabel("Conta:");
		lblConta.setBounds(12, 39, 66, 15);
		getContentPane().add(lblConta);
		
		JComboBox comboBoxConta = new JComboBox();
		comboBoxConta.setBounds(96, 34, 244, 24);
		comboBoxConta.setModel(modelConta);
		getContentPane().add(comboBoxConta);

	}

}
