import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Window w = new Window();
		w.setBounds(0,0,700,600);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
	}
}