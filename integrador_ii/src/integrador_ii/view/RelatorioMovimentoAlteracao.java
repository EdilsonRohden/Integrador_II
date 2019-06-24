package integrador_ii.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import integrador_ii.models.Movimento;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioMovimentoAlteracao extends JInternalFrame {

	private static final long serialVersionUID = -3209492029761228508L;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioMovimentoAlteracao frame = new RelatorioMovimentoAlteracao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public RelatorioMovimentoAlteracao() {}

	public RelatorioMovimentoAlteracao(List<Movimento> relatorio) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 867, 544);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBounds(0, 0, 440, 268);
		

		
		
		Vector< Vector<String> > dataTable = new Vector<Vector<String>>();
		for (Movimento movimento : relatorio) {
			Vector<String> linha = new Vector<String>();
			linha.add(movimento.getAlteracaoAt(0).getTipo());
			linha.add(movimento.getAlteracaoAt(0).getData().toString());
			linha.add(movimento.getAlteracaoAt(0).getAutor().getNome());
			linha.add(Double.toString(movimento.getValor()));
			linha.add(movimento.getDataMovimento().toString());
			dataTable.add(linha);
			
		}
		
		Vector<String> identificadorDeColunas = new Vector<String>();
		identificadorDeColunas.add("Tipo de Alteração");
		identificadorDeColunas.add("Data da Alteração");
		identificadorDeColunas.add("Usuario Responsavel");
		identificadorDeColunas.add("Valor do Movimento");
		identificadorDeColunas.add("Data do Movimento");
		
		tableModel.setDataVector(dataTable, identificadorDeColunas);
		table.setModel(tableModel);
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				dispose();
			}
		});
	}
}
