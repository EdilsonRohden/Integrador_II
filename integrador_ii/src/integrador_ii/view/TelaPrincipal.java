package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import integrador_ii.models.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = -4376914606786126210L;
	private JLayeredPane contentPane;
	public static Usuario usuario;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal(Usuario usuario) {
		TelaPrincipal.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 438);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCliente telaCliente = new CadastroCliente();
				contentPane.add(telaCliente);
				telaCliente.setVisible(true);
			}
		});
		mnCadastros.add(mntmClientes);
		
		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TelaPrincipal.usuario.isAdm()) {
					CadastroUsuario telaUsuario = new CadastroUsuario();
					contentPane.add(telaUsuario);
					telaUsuario.setVisible(true);					
				}else {
					JOptionPane.showMessageDialog(contentPane, "Você ó plebe, não tem acesso a tal área do sistema.");
				}
			}
		});
		mnCadastros.add(mntmUsuarios);
		
		JMenuItem mntmContas = new JMenuItem("Contas");
		mntmContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroConta telaConta = new CadastroConta();
				contentPane.add(telaConta);
				telaConta.setVisible(true);
			}
		});
		mnCadastros.add(mntmContas);
		
		JMenuItem mntmMovimentao = new JMenuItem("Movimentação");
		mntmMovimentao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroMovimentacao telaMovimentacao = new CadastroMovimentacao();
				contentPane.add(telaMovimentacao);
				telaMovimentacao.setVisible(true);
			}
		});
		mnCadastros.add(mntmMovimentao);
		
		JMenuItem mntmCidade = new JMenuItem("Cidade");
		mntmCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCidade telaCidade = new CadastroCidade();
				contentPane.add(telaCidade);
				telaCidade.setVisible(true);
			}
		});
		mnCadastros.add(mntmCidade);
		
		JMenuItem mntmEstado = new JMenuItem("Estado");
		mntmEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroEstado telaEstado = new CadastroEstado();
				contentPane.add(telaEstado);
				telaEstado.setVisible(true);
			}
		});
		mnCadastros.add(mntmEstado);
		
		JMenuItem mntmPessoas = new JMenuItem("Pessoas");
		mntmPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroPessoa telaPessoa = new CadastroPessoa();
				contentPane.add(telaPessoa);
				telaPessoa.setVisible(true);
			}
		});
		mnCadastros.add(mntmPessoas);
		
		JMenu mnRelatrios = new JMenu("Relatórios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmPlanoDeContas = new JMenuItem("Plano de Contas");
		mnRelatrios.add(mntmPlanoDeContas);
		
		JMenu mnLogoff = new JMenu("Logoff");
		mnLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		menuBar.add(mnLogoff);
		
		JMenuItem mntmLogoff = new JMenuItem("logoff");
		mntmLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		mnLogoff.add(mntmLogoff);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnLogoff.add(mntmSair);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
	}

}
