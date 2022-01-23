package VideoFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OutputWindow extends JFrame implements MouseListener {
	
	private ArrayList<Video> outputVideos = new ArrayList<Video>();
	JTable table;
	
	OutputWindow(ArrayList<Video> outputVideos2) {
		
		this.outputVideos = outputVideos2;
		
		this.setTitle("Results");
		this.setSize(1500,700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(new Color(65,65,65));
		this.setLayout(new BorderLayout()); //TEST
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		ImageIcon smashLogo = new ImageIcon(System.getProperty("user.dir") + "\\src\\UltimateLogo.png");
		
		//Same header from the SearchWindow class
		JLabel header = new JLabel();
		header.setText("Smash Ultimate VODS Finder");
		header.setIcon(smashLogo);
		header.setHorizontalTextPosition(JLabel.CENTER);
		header.setVerticalTextPosition(JLabel.BOTTOM);
		header.setForeground(Color.WHITE); //Sets font color
		header.setFont(new Font("Biome",Font.BOLD,30));
		header.setIconTextGap(-30); //Sets text position in relation to image for this label.
		header.setVerticalAlignment(JLabel.TOP);
		header.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		
		JPanel inputPanel = new JPanel(new BorderLayout());
		inputPanel.setBackground(Color.BLACK);
		inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		inputPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		inputPanel.setSize(600, 500);
		
		
		
		//Creates a table with 2 columns and a number of rows equal to the size of outputVideos
		 this.table = new JTable(new DefaultTableModel(new Object[]{"Title", "URL"}, 0));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for(int i = 0; i < outputVideos.size(); i++) {
			String title = outputVideos.get(i).getTitle();
			String URL = outputVideos.get(i).getURL();
			
			Object[] tableRow = {title,URL};
			
			if (!title.equals(null) && !URL.equals(null)) {
				model.addRow(tableRow);
			}
		}
		
		table.getColumnModel().getColumn(0).setPreferredWidth(600);
	    table.getColumnModel().getColumn(1).setPreferredWidth(100);
	    table.setRowHeight(50);
	    table.setFont(new Font("Courier New", Font.BOLD, 15));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.addMouseListener(this);
		
		
		this.add(header);
		this.add(inputPanel);
		inputPanel.add(scrollPane);
		this.setVisible(true);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Opens browser when a link is clicked.
		if(this.table.getSelectedColumn() == 1) {
			String browserLink = (String) this.table.getValueAt(this.table.getSelectedRow(), this.table.getSelectedColumn());
			openLink(browserLink);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Placeholder needed for MouseListener interface
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Placeholder needed for MouseListener interface
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Placeholder needed for MouseListener interface
		
	}

	public void openLink(String browserLink) { //Finds and opens video link
		try {
		    Desktop.getDesktop().browse(new URL(browserLink).toURI());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid link!");
		}

	}
	

}
