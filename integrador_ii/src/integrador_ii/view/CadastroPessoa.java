package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import integrador_ii.services.CidadeService;
import integrador_ii.services.PessoaService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroPessoa extends JInternalFrame {
	private JTextField txtNome;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoa frame = new CadastroPessoa();
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
	public CadastroPessoa() {
		setTitle("Cadastro Pessoa");
		setBounds(100, 100, 294, 198);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 41, 66, 15);
		getContentPane().add(lblNome);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(12, 68, 66, 15);
		getContentPane().add(lblCidade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(96, 63, 169, 20);
		CidadeService cidadeService = new CidadeService();
		model = cidadeService.getCidadesComboBox();
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		txtNome = new JTextField();
		txtNome.setBounds(97, 39, 168, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] splitado = model.getSelectedItem().toString().split("-");
				Integer codIbge = Integer.parseInt(splitado[0]);
				PessoaService pessoaService = new PessoaService();
				pessoaService.salvar(txtNome.getText(), txtId.getText(), codIbge);
			}
		});
		btnSalvar.setBounds(12, 119, 114, 25);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(151, 119, 114, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 14, 66, 15);
		getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(96, 12, 169, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);

	}

}
