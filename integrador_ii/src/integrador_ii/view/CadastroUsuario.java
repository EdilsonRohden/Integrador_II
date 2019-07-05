package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import integrador_ii.models.Pessoa;
import integrador_ii.models.Usuario;
import integrador_ii.services.PessoaService;
import integrador_ii.services.UsuarioService;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroUsuario extends JInternalFrame {
	private static final long serialVersionUID = 2865229064350768750L;
	private JTextField txtIdPessoa;
	private JTextField txtLogin;
	private JPasswordField passwordTxt;
	private JPasswordField passwordTxtRetry;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroUsuario() {
		setTitle("Cadastro de Usuario");
		setBounds(100, 100, 385, 277);
		getContentPane().setLayout(null);
		
		UsuarioService usuarioService = new UsuarioService();
		PessoaService pessoaService = new PessoaService();
		List<Pessoa> pessoas = pessoaService.getPessoas();
		
		JCheckBox chckbxAdm = new JCheckBox("ADM");
		chckbxAdm.setBounds(156, 149, 66, 23);
		getContentPane().add(chckbxAdm);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				Integer id = Integer.parseInt(txtIdPessoa.getText());
				String login = txtLogin.getText();
				String senha1 = passwordTxt.getText();
				String senha2 = passwordTxtRetry.getText();
				boolean adm = chckbxAdm.isSelected();
				
				if (login.isEmpty() || !(senha1.equals(senha2)) || id == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Dados invalidos!");
				}else {
					usuarioService.salvar(id, login, senha1, adm);
					dispose();
				}
				
	
			}
		});
		btnNewButton.setBounds(12, 201, 114, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(156, 201, 114, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblPessoa = new JLabel("Pessoa:");
		lblPessoa.setBounds(12, 12, 66, 15);
		getContentPane().add(lblPessoa);
		
		txtIdPessoa = new JTextField();
		txtIdPessoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					
					Integer id = Integer.parseInt(txtIdPessoa.getText());
					Usuario usuario = usuarioService.getUsuarioById(id);
					
					if(usuario != null) {
						for (int i = 0; i < model.getSize(); i++) {
							if(model.getElementAt(i).toString().equals(usuario.getNome())) {
								model.setSelectedItem(model.getElementAt(i));
								break;
							}
						}
						chckbxAdm.setSelected(usuario.isAdm());
					}
					
				} catch (Exception ex) {

				}
			}
		});
		txtIdPessoa.setBounds(156, 5, 65, 22);
		getContentPane().add(txtIdPessoa);
		txtIdPessoa.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String escolhido = model.getSelectedItem().toString();
				for (Pessoa pessoa : pessoas) {
					if (escolhido.equals(pessoa.getNome())) {
						txtIdPessoa.setText(Integer.toString(pessoa.getId()));
						break;
					}
				}				
			}
		});
		comboBox.setBounds(231, 2, 129, 25);
		for (Pessoa pessoa : pessoas) {
			model.addElement(pessoa.getNome());
		}
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(12, 52, 66, 15);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(12, 82, 66, 15);
		getContentPane().add(lblSenha);
		
		JLabel lblSenhaNovamente = new JLabel("Senha novamente:");
		lblSenhaNovamente.setBounds(12, 113, 134, 15);
		getContentPane().add(lblSenhaNovamente);
		
		JLabel lblAdministrador = new JLabel("Administrador:");
		lblAdministrador.setBounds(12, 153, 114, 15);
		getContentPane().add(lblAdministrador);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(156, 50, 204, 19);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(156, 77, 204, 25);
		getContentPane().add(passwordTxt);
		
		passwordTxtRetry = new JPasswordField();
		passwordTxtRetry.setBounds(156, 107, 204, 25);
		getContentPane().add(passwordTxtRetry);

	}
}
