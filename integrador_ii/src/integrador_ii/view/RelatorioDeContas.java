package integrador_ii.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import integrador_ii.models.PlanoDeConta;

import javax.swing.JScrollPane;

public class RelatorioDeContas extends JInternalFrame {
	private static final long serialVersionUID = -4150981096929907999L;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioDeContas frame = new RelatorioDeContas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public RelatorioDeContas(ArrayList<PlanoDeConta> dados) {
		setBounds(100, 100, 901, 600);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 867, 544);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Vector< Vector<String> > dataTable = new Vector<Vector<String>>();
		for (PlanoDeConta planoDeConta : dados) {
			Vector<String> linha = new Vector<String>();
			
			linha.add(planoDeConta.getTipoConta());
			linha.add(Double.toString(planoDeConta.getTotalizadores()));
			dataTable.add(linha);
		}
		
		Vector<String> identificadorDeColunas = new Vector<String>();
		identificadorDeColunas.add("Tipo de Conta");
		identificadorDeColunas.add("Totalizadores");
		
		tableModel.setDataVector(dataTable, identificadorDeColunas);
		
		table.setModel(tableModel);
		

	}
	public RelatorioDeContas() {}
}
