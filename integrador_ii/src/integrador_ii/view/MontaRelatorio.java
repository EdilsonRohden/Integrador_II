package integrador_ii.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import integrador_ii.models.Cliente;
import integrador_ii.models.DateLabelFormatter;
import integrador_ii.models.PlanoDeConta;
import integrador_ii.services.ClienteService;
import integrador_ii.services.PlanoDeContaService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class MontaRelatorio extends JInternalFrame {
	private static final long serialVersionUID = -6244116170121045583L;
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
	
		SqlDateModel modelDateIni = new SqlDateModel();
		SqlDateModel modelDateFin = new SqlDateModel();
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanelIni = new JDatePanelImpl(modelDateIni, p);
		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelDateFin, p);
		
		JDatePickerImpl datePickerIni = new JDatePickerImpl(datePanelIni, new DateLabelFormatter());
		
		datePickerIni.setBounds(113, 56, 219, 25);
		getContentPane().add(datePickerIni);

		JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
		
		datePickerFin.setBounds(113, 107, 219, 25);
		getContentPane().add(datePickerFin);	
		
		JLabel lblDataFin = new JLabel("Data Fin.:");
		lblDataFin.setBounds(12, 109, 81, 15);
		getContentPane().add(lblDataFin);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(113, 7, 219, 24);
		
		List<Cliente> clientes = clienteService.getClientes();
		for (Cliente cliente : clientes) {
			model.addElement(cliente.getId() + "-" + cliente.getNome());
		}
		
		comboBox.setModel(model);
		getContentPane().add(comboBox);
		
		JButton btnVerificar = new JButton("Gerar Relatório");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int idCliente = Integer.parseInt(model.getSelectedItem().toString().split("-")[0]);
					
					
					Date dataIni = modelDateIni.getValue();
					Date dataFin = modelDateFin.getValue();
					
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
		btnVerificar.setBounds(12, 174, 140, 25);
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
