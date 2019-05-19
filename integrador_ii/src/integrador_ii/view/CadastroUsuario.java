package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import integrador_ii.models.Usuario;
import integrador_ii.services.Usuarioservice;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroUsuario extends JInternalFrame {
	private JTextField txtIdPessoa;
	private JTextField txtLogin;
	private JPasswordField passwordTxt;
	private JPasswordField passwordTxtRetry;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public CadastroUsuario() {
		setTitle("Cadastro de Usuario");
		setBounds(100, 100, 385, 277);
		getContentPane().setLayout(null);
		
		Usuarioservice usuarioService = new Usuarioservice();
		List<Usuario> usuarios = usuarioService.getUsuarios();
		
		JCheckBox chckbxAdm = new JCheckBox("ADM");
		chckbxAdm.setBounds(156, 149, 66, 23);
		getContentPane().add(chckbxAdm);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Integer id = Integer.parseInt(txtIdPessoa.getText());
				String login = txtLogin.getText();
				String senha1 = passwordTxt.getText();
				String senha2 = passwordTxtRetry.getText();
				boolean adm = chckbxAdm.isSelected();
				
				if (login.isEmpty() || !(senha1.equals(senha2)) || id == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Dados invalidos!");
					
					return;
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
		txtIdPessoa.setBounds(156, 5, 65, 22);
		getContentPane().add(txtIdPessoa);
		txtIdPessoa.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(231, 2, 129, 25);
		for (Usuario usuario : usuarios) {
			model.addElement(usuario.getNome());
		}
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				String escolhido = model.getSelectedItem().toString();
				for (Usuario usuario : usuarios) {
					if (escolhido.equals(usuario.getNome())) {
						txtIdPessoa.setText(Integer.toString(usuario.getId()));
						break;
					}
				}
			}
		});
		
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
