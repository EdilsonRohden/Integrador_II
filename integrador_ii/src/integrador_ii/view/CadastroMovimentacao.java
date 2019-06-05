package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroMovimentacao extends JInternalFrame {
	private static final long serialVersionUID = -8971273233348356712L;
	private JTextField txrIdCliente;
	private JTextField txtData;
	private JTextField txtValor;
	private DefaultComboBoxModel<String> modelConta = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<String>();
	private JTextField txtDescricao;

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
	public CadastroMovimentacao() {
		setTitle("Cadastro de Movimentação");
		setBounds(100, 100, 362, 300);
		getContentPane().setLayout(null);
		
		MovimentoService mvService = new MovimentoService();
		
		ClienteService clienteService = new ClienteService();
		List<Cliente> clientes = clienteService.getClientes();
		
		for (Cliente cliente : clientes) {
			modelCliente.addElement(cliente.getNome());
		}
		
		ContaService contaService = new ContaService();
		List<Conta> contas = contaService.getContas();
		
		for (Conta conta : contas) {
			modelConta.addElement(conta.getId()+ "/ " +conta.getIdP()+ "-" + conta.getDescricaoP()  
					+ "."+conta.getIdS()+" - "+conta.getDescricaoS());
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
		txtData.setBounds(96, 75, 244, 19);
		getContentPane().add(txtData);
		txtData.setText("");
		txtData.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(96, 106, 124, 19);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date dataMovimento = txtData.getText().isEmpty() ? null : Date.valueOf(txtData.getText());
					String descricao = txtDescricao.getText();
					double valor = Double.parseDouble(txtValor.getText());
					String nome = modelCliente.getSelectedItem().toString();
					int idCliente = 0;
					int idConta = Integer.parseInt(modelConta.getSelectedItem().toString().split("/")[0]);
					for (Cliente cliente : clientes) {
						if(cliente.getNome().equals(nome)) {
							idCliente = cliente.getId();
						}
					}
					mvService.salvar(new Cliente(idCliente), new Movimento(idConta, dataMovimento, valor, descricao));
					dispose();
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(getContentPane(), "Dados invalidos!");
					e.printStackTrace();
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
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(96, 146, 244, 19);
		getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(12, 148, 76, 15);
		getContentPane().add(lblDescrio);

	}
}
