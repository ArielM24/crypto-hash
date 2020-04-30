import javax.swing.*;
import java.io.File;

public class Window extends JFrame {
	private JButton btnGenerate, btnCheck, btnSelect;
	private JLabel lblFile, lblHash;
	private JTextField tfFile;
	private JTextArea taFile;
	private File f;
	private JFileChooser fc;
	private Hash h;

	public Window(){
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hash");
		initComp();
	}

	private void initComp(){
		btnSelect = new JButton("Select File");
		btnSelect.setBounds(10,10,150,30);
		btnSelect.addActionListener(e->btnSelectClick());
		add(btnSelect);

		btnGenerate = new JButton("Generate hash");
		btnGenerate.setBounds(260,10,150,30);
		btnGenerate.addActionListener(e->btnGenerateClick());
		add(btnGenerate);

		btnCheck = new JButton("Check hash");
		btnCheck.setBounds(510,10,150,30);
		btnCheck.addActionListener(e->btnCheckClick());
		add(btnCheck);

		lblFile = new JLabel("File: ");
		lblFile.setBounds(10,50,100,30);
		add(lblFile);

		tfFile = new JTextField();
		tfFile.setBounds(70,50,600,30);
		add(tfFile);

		lblHash = new JLabel("Hash:");
		lblHash.setBounds(10,90,500,30);
		add(lblHash);

		taFile = new JTextArea();
		taFile.setBounds(10,130,680,420);
		taFile.setEditable(false);

		JScrollPane sp = new JScrollPane(taFile);
		sp.setBounds(10,130,680,420);
		add(sp);

		fc = new JFileChooser();
		h = new Hash();
	}

	private void btnSelectClick(){
		fc.showOpenDialog(this);
		f = fc.getSelectedFile();
		if(f != null){
			try{
				tfFile.setText(f.getAbsolutePath());
				taFile.setText(h.readFile(f));
			}catch(Exception ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "File error","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void btnGenerateClick(){
		try{
			h.generateHashFile(f);
			lblHash.setText("Hash: "+h.getHash(f));
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "File error","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnCheckClick(){
		try{
			if(h.checkHashFile(f)){
				JOptionPane.showMessageDialog(null,"Hash is correct!");
			}else{
				JOptionPane.showMessageDialog(null,"Hash is incorrect!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "File error","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}