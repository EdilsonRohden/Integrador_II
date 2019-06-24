package integrador_ii.view;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import integrador_ii.models.Movimento;
import integrador_ii.models.Usuario;
import integrador_ii.services.MovimentoService;
import integrador_ii.services.UsuarioService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MontaRelatorioMovimentoAlteracao extends JInternalFrame {
	private static final long serialVersionUID = -8767192411533485869L;
	
	private JTextField txtIdMovimentacao;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MontaRelatorioMovimentoAlteracao frame = new MontaRelatorioMovimentoAlteracao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MontaRelatorioMovimentoAlteracao() {}
	
	public MontaRelatorioMovimentoAlteracao(Container container) {
		
		MovimentoService movimentoService = new MovimentoService();
		UsuarioService usuarioService = new UsuarioService();
		List<Usuario> usuarios = usuarioService.getUsuarios();
		
		setBounds(100, 100, 382, 217);
		getContentPane().setLayout(null);
		
		JLabel lblMovimentacao = new JLabel("Id Movimentacao:");
		lblMovimentacao.setBounds(12, 12, 123, 15);
		getContentPane().add(lblMovimentacao);
		
		txtIdMovimentacao = new JTextField();
		txtIdMovimentacao.setBounds(166, 10, 96, 19);
		getContentPane().add(txtIdMovimentacao);
		txtIdMovimentacao.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario Responsavel:");
		lblUsuario.setBounds(12, 51, 146, 15);
		getContentPane().add(lblUsuario);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(166, 41, 195, 24);
		getContentPane().add(comboBox);
		
		JButton btnGerarRelatrio = new JButton("Gerar Relatório");
		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String idMovimentacao = txtIdMovimentacao.getText();
				Integer idUsuario = null;
				if( model.getSelectedItem().toString() != "TODOS" ) {
					for (Usuario usuario : usuarios) {
						if( model.getSelectedItem().toString() == usuario.getNome() ) {
							idUsuario = usuario.getId();
						}
					}
				}
				
				List<Movimento> relatorio = movimentoService.relatorioMovimentacao(idMovimentacao, idUsuario);

				RelatorioMovimentoAlteracao telaRelatorioMovimentoAlteracao = new RelatorioMovimentoAlteracao(relatorio);
				
				container.add(telaRelatorioMovimentoAlteracao);
				telaRelatorioMovimentoAlteracao.setVisible(true);
			}
		});
		btnGerarRelatrio.setBounds(12, 111, 137, 25);
		getContentPane().add(btnGerarRelatrio);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(224, 111, 114, 25);
		getContentPane().add(btnCancelar);
		
		model.addElement("TODOS");
		for (Usuario usuario : usuarios) {
			model.addElement(usuario.getNome());
		}
		comboBox.setModel(model);
		
	}
}
