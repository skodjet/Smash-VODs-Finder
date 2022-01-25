package VideoFinder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchWindow extends JFrame implements ActionListener {
	ImageIcon smashLogo = new ImageIcon(System.getProperty("user.dir") + "\\UltimateLogo.png");
	JButton characterSubmitButton;
	JTextField characterInput;
	String characterSearch;
	boolean searched = false;
	ArrayList<String> textLines = new ArrayList<String>();
	ArrayList<Video> outputVideos = new ArrayList<Video>();
	String videoFile;

	SearchWindow() {
		this.setTitle("Smash VODs Finder v1.0");
		this.setSize(1000,700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(new Color(65,65,65));
		this.setLayout(null);
		
		
		//Smash Ultimate logo and title text.
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
		header.setBounds(150, 0, 650, 325);
		
		JLabel inputText = new JLabel();
		inputText.setText("Choose a character to search for:");
		inputText.setFont(new Font("Biome",Font.PLAIN,20));
		inputText.setForeground(Color.WHITE);
		inputText.setVerticalAlignment(JLabel.TOP);
		inputText.setHorizontalAlignment(JLabel.LEFT);
		
		//The panel that contains all input fields for main screen.
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(new Color(65,65,65));
		inputPanel.setBounds(200, 260, 600, 300);
		inputPanel.setLayout(new FlowLayout());
		
		//Character input text box
		this.characterInput = new JTextField();
		this.characterInput.setPreferredSize(new Dimension(130,30));
		this.characterInput.setBounds(310,0,130,30);
		
		//Character input search button
		this.characterSubmitButton = new JButton("Search");
		this.characterSubmitButton.setFocusable(false);
		this.characterSubmitButton.addActionListener(this);
		
		
		
		
		this.add(header);
		this.add(inputPanel);
		inputPanel.add(inputText);
		inputPanel.add(characterInput);
		inputPanel.add(characterSubmitButton);
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==characterSubmitButton) {
			this.characterSearch = this.characterInput.getText().trim();  //trim removes leading and trailing whitespace from user input.
			this.characterInput.setText(null);
			this.searched = true;
			
			
			Scanner vodsScanner;
			try {
				String vodsPath = System.getProperty("user.dir") + "\\VODs"; // *NOTE: Strange line here typing + "\\src\\VODs" works for Eclipse but not command prompt
				System.out.println(vodsPath);
				
				File[] dir = new File(vodsPath).listFiles();
				
				
				for(int i = 0; i < dir.length; i++) {
					vodsScanner = new Scanner(new FileInputStream(dir[i]));
					
					
					while (vodsScanner.hasNext()) {
						String[] line = vodsScanner.nextLine().split("(\t)");
						
						for(int j = 0; j < line.length; j++) {
							this.textLines.add(line[j]);
						}
					}
				}
			
				
			} catch (FileNotFoundException fnfe) {
				// TODO Auto-generated catch block
				fnfe.printStackTrace();
				System.out.println("File not found!");
			}
			
			//System.out.println(this.textLines.toString());
			
			
			for(int j = 0; j < this.textLines.size(); j++) {
				
				/* The parentheses prevent in .contains() prevent unwanted searches. For example, 
				 * Captain Falcon VODS showing when "Falco" is searched.
				*/
				if (this.textLines.get(j).contains("(" + characterSearch + ")")) {
					Video video = new Video();
					
					//Sets the title and URL of the video for each matching search
					video.setTitle(this.textLines.get(j)); 
					video.setURL(this.textLines.get(j + 1));
					
					//Adds the matching video to the outputVideos ArrayList 
					outputVideos.add(video);
					
					
				}
			}
			
			
			OutputWindow output = new OutputWindow(this.outputVideos);
			this.characterSearch = null;
			this.outputVideos = new ArrayList<Video>();
			this.textLines = new ArrayList<String>();
		}
		
	}
}
