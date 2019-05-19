package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.postgresql.ssl.DefaultJavaSSLFactory;

import integrador_ii.models.Pessoa;
import integrador_ii.services.ClienteService;
import integrador_ii.services.PessoaService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroCliente extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9017093165608855941L;
	private JTextField txtIdPessoa;
	private JTextField txtBairro;
	private JTextField txtCep;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public CadastroCliente() {
		setTitle("Cadastro de Clientes");
		setBounds(100, 100, 379, 241);
		getContentPane().setLayout(null);
		
		PessoaService pessoaService = new PessoaService();
		List<Pessoa> pessoas = pessoaService.getPessoas();
		
		JLabel lblPessoa = new JLabel("Pessoa:");
		lblPessoa.setBounds(12, 12, 66, 15);
		getContentPane().add(lblPessoa);
		
		txtIdPessoa = new JTextField();
		txtIdPessoa.setBounds(113, 10, 66, 19);
		getContentPane().add(txtIdPessoa);
		txtIdPessoa.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
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
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String idpessoa = txtIdPessoa.getText();
				String bairro = txtBairro.getText();
				String cep = txtCep.getText();
				String fone = txtTelefone.getText();
				String email = txtEmail.getText();
				
				ClienteService clienteService = new ClienteService();
				clienteService.salvar(idpessoa, bairro, cep, fone, email);
				
				
			}
		});
		btnSalvar.setBounds(12, 169, 114, 25);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(166, 169, 114, 25);
		getContentPane().add(btnCancelar);

	}

}
