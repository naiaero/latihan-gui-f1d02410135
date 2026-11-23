import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HalamanMenu extends JFrame implements ActionListener {
    private final TiketF1[] daftarTiket = {
        new TiketF1("Grandstand", 2500000),
        new TiketF1("Paddock Club", 15000000),
        new TiketF1("Paddock Pass", 25000000)
    };

    private JButton[] tombolKategori;

    public HalamanMenu() {
        setTitle("Menu Utama");
        setSize(420, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelUtama = new JPanel(new BorderLayout(0, 25));
        panelUtama.setBackground(new Color(21, 21, 30));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBackground(new Color(21, 21, 30));

        JLabel lblJudul = new JLabel("FORMULA 1 TICKETING");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblJudul.setForeground(new Color(225, 6, 0));
        lblJudul.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Pilih kategori tiket yang diinginkan:");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSub.setForeground(Color.WHITE);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblJudul);
        panelHeader.add(Box.createRigidArea(new Dimension(0, 8)));
        panelHeader.add(lblSub);

        JPanel panelMenu = new JPanel(new GridLayout(3, 1, 0, 12));
        panelMenu.setBackground(new Color(21, 21, 30));

        tombolKategori = new JButton[daftarTiket.length];
        for (int i = 0; i < daftarTiket.length; i++) {
            TiketF1 tiket = daftarTiket[i];
            String labelTombol = String.format("%s (Rp%,.0f)", tiket.getKategori(), tiket.getHargaSatuan());
            
            tombolKategori[i] = new JButton(labelTombol);
            tombolKategori[i].setFont(new Font("SansSerif", Font.BOLD, 13));
            tombolKategori[i].setForeground(Color.WHITE);
            tombolKategori[i].setBackground(new Color(43, 43, 60));
            tombolKategori[i].setFocusPainted(false);
            tombolKategori[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

            tombolKategori[i].addActionListener(this);

            panelMenu.add(tombolKategori[i]);
        }

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelMenu, BorderLayout.CENTER);

        add(panelUtama);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < tombolKategori.length; i++) {
            if (e.getSource() == tombolKategori[i]) {
                new HalamanPembayaran(daftarTiket[i]).setVisible(true);
                this.dispose();
                break;
            }
        }
    }
}
