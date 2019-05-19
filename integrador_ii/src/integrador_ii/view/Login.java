package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = -7061486212416495829L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setBounds(23, 178, 114, 25);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(149, 178, 114, 25);
		contentPane.add(btnCancel);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(43, 12, 66, 15);
		contentPane.add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(43, 51, 79, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(139, 12, 124, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 43, 124, 23);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/home/edislon/Imagens/voMataSpliter.jpeg"));
		label.setBounds(281, 12, 202, 199);
		contentPane.add(label);
	}
}
