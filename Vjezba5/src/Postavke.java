import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Postavke extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean dirty=false;
	/**
	 * Create the dialog.
	 */
	public Postavke() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	JDialog dialog = (JDialog)e.getSource();
		    	dialog.setDefaultCloseOperation(Zatvaranje());
		    }
		});
		setBounds(100, 100, 450, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {78, 200};
		gbl_contentPanel.rowHeights = new int[] {35, 35, 35, 50};
		gbl_contentPanel.columnWeights = new double[]{0.0,1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Host:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
			
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Port:");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			kontrola(textField);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
			kontrola(textField_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Korisnik:");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 2;
			contentPanel.add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
			kontrola(textField_2);
		}
		LoadConfigData();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Snimi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						UserConfig.setHost(textField.getText());
						UserConfig.setPort(Integer.parseInt(textField_1.getText()));
						UserConfig.setKorisnik(textField_2.getText());
						UserConfig.saveParamChanges(); 
						dirty=false;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Odustani");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!dirty)
						{
							dispose();
						}
						else
						{
							if(Zatvaranje()==2)
								dispose();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private int Zatvaranje()
	{
		if(!dirty) {return (int)JDialog.DISPOSE_ON_CLOSE;}
		
		int result = JOptionPane.showOptionDialog(contentPanel,	
			"Da li ste sigurni da želite zatvoriti prozor bez snimanja promjena?", "Pitanje!",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
			null, new String[] { "Da", "Ne" }, "Ne");
		
		if (result == JOptionPane.YES_OPTION)
			return (int)JDialog.DISPOSE_ON_CLOSE;
		return (int)JDialog.DO_NOTHING_ON_CLOSE;
	}
	
	private void LoadConfigData()
	{
		UserConfig.loadParams();
		textField.setText(UserConfig.getHost());
		textField_1.setText(Integer.toString(UserConfig.getPort()));
		textField_2.setText(UserConfig.getKorisnik());	
	}
	
	private void kontrola(JTextField textField)
	{
		textField.getDocument().addDocumentListener(new DocumentListener() {				
			@Override
			public void changedUpdate(DocumentEvent arg0) {	dirty=true; }
			@Override
			public void insertUpdate(DocumentEvent arg0) {  }
			@Override
			public void removeUpdate(DocumentEvent arg0) {  }
		});
		textField.addFocusListener(new FocusListener()
		{
			String spremText;
			@Override
			public void focusGained(FocusEvent arg0) {
				spremText=textField.getText();			
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!spremText.equals(textField.getText()))
				{
					dirty=true;
				}			
			}		
		});
	}
}
