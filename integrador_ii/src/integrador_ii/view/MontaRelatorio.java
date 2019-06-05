package integrador_ii.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import integrador_ii.models.Cliente;
import integrador_ii.models.PlanoDeConta;
import integrador_ii.services.ClienteService;
import integrador_ii.services.PlanoDeContaService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class MontaRelatorio extends JInternalFrame {
	private static final long serialVersionUID = -6244116170121045583L;
	private JTextField txtDataIni;
	private JTextField txtDataFin;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	private PlanoDeContaService planoDeContas = new PlanoDeContaService();
	private ClienteService clienteService = new ClienteService();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MontaRelatorio frame = new MontaRelatorio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MontaRelatorio(JLayeredPane contentPane) {
		setBounds(100, 100, 371, 244);
		getContentPane().setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(12, 12, 66, 15);
		getContentPane().add(lblCliente);
		
		JLabel lblDataIni = new JLabel("Data Ini.:");
		lblDataIni.setBounds(12, 58, 66, 15);
		getContentPane().add(lblDataIni);
		
		txtDataIni = new JTextField();
		txtDataIni.setBounds(113, 56, 124, 19);
		getContentPane().add(txtDataIni);
		txtDataIni.setColumns(10);
		
		txtDataFin = new JTextField();
		txtDataFin.setBounds(113, 107, 124, 19);
		getContentPane().add(txtDataFin);
		txtDataFin.setColumns(10);
		
		JLabel lblDataFin = new JLabel("Data Fin.:");
		lblDataFin.setBounds(12, 109, 81, 15);
		getContentPane().add(lblDataFin);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(113, 7, 219, 24);
		
		List<Cliente> clientes = clienteService.getClientes();
		for (Cliente cliente : clientes) {
			model.addElement(cliente.getId() + "-" + cliente.getNome());
		}
		
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int idCliente = Integer.parseInt(model.getSelectedItem().toString().split("-")[0]);
					
					
					Date dataIni = Date.valueOf(txtDataIni.getText());
					Date dataFin = Date.valueOf(txtDataFin.getText());
					
					ArrayList<PlanoDeConta> dados = planoDeContas.getRelatorio(idCliente, dataIni, dataFin);
					
					if(dados != null) {
						RelatorioDeContas telaRelatorioDeContas = new RelatorioDeContas(dados);
						contentPane.add(telaRelatorioDeContas);
						telaRelatorioDeContas.setVisible(true);
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getContentPane(), "Dados invalidos!");
				}
			}
		});
		btnVerificar.setBounds(12, 174, 114, 25);
		getContentPane().add(btnVerificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(219, 174, 114, 25);
		getContentPane().add(btnCancelar);

	}

	public MontaRelatorio() {}
}
