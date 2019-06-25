package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import integrador_ii.models.Cliente;
import integrador_ii.models.Pessoa;
import integrador_ii.services.ClienteService;
import integrador_ii.services.PessoaService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroCliente extends JInternalFrame {
	private static final long serialVersionUID = 9017093165608855941L;
	private JTextField txtIdPessoa;
	private JTextField txtBairro;
	private JTextField txtCep;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CadastroCliente() {
		setTitle("Cadastro de Clientes");
		setBounds(100, 100, 377, 259);
		getContentPane().setLayout(null);
		
		PessoaService pessoaService = new PessoaService();
		List<Pessoa> pessoas = pessoaService.getPessoas();
		
		JLabel lblPessoa = new JLabel("Pessoa:");
		lblPessoa.setBounds(12, 12, 66, 15);
		getContentPane().add(lblPessoa);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String nome = model.getSelectedItem().toString();
				for (Pessoa pessoa : pessoas) {
					if(pessoa.getNome().equals(nome)) {
						txtIdPessoa.setText(pessoa.getId().toString());
						break;
					}
				}				
			}
		});
		comboBox.setBounds(191, 7, 159, 24);
		for (Pessoa pessoa : pessoas) {
			model.addElement(pessoa.getNome());
		}
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 39, 66, 15);
		getContentPane().add(lblBairro);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 66, 66, 15);
		getContentPane().add(lblCep);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 121, 66, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 94, 66, 15);
		getContentPane().add(lblTelefone);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(113, 37, 237, 19);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		
		JCheckBox chckbxAtivo = new JCheckBox("Ativo");
		if(!TelaPrincipal.usuario.isAdm()) {
			chckbxAtivo.setEnabled(false);			
		}
		chckbxAtivo.setSelected(true);
		chckbxAtivo.setBounds(12, 144, 126, 23);
		getContentPane().add(chckbxAtivo);

		
		txtCep = new JTextField();
		txtCep.setBounds(113, 64, 237, 19);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(113, 92, 237, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(113, 119, 237, 19);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		txtIdPessoa = new JTextField();
		txtIdPessoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(!txtIdPessoa.getText().isEmpty()) {
					try {
						Integer id = Integer.parseInt(txtIdPessoa.getText());
						ClienteService clienteService = new ClienteService();
						Cliente cliente = clienteService.getCliente(id);
						if (cliente != null) {
							txtBairro.setText(cliente.getBairro());
							txtCep.setText(cliente.getCep());
							txtTelefone.setText(cliente.getFone());
							txtEmail.setText(cliente.getEmail());
							chckbxAtivo.setSelected(!cliente.isExcluido());
						}
					} catch (Exception ex) {
						
					}
				}
			}
		});
		txtIdPessoa.setBounds(113, 10, 66, 19);
		getContentPane().add(txtIdPessoa);
		txtIdPessoa.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idpessoa = txtIdPessoa.getText();
					String bairro = txtBairro.getText();
					String cep = txtCep.getText();
					String fone = txtTelefone.getText();
					String email = txtEmail.getText();
					
					if(idpessoa.isEmpty() || bairro.isEmpty() || cep.isEmpty() || fone.isEmpty() || email.isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "Dados invalidos!");
					}else {
						ClienteService clienteService = new ClienteService();
						clienteService.salvar(idpessoa, bairro, cep, fone, email, !chckbxAtivo.isSelected());
						dispose();						
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(12, 190, 114, 25);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(236, 190, 114, 25);
		getContentPane().add(btnCancelar);
		
	}
}
