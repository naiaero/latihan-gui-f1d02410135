import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HalamanPembayaran extends JFrame implements ActionListener, KeyListener {
    private TiketF1 tiketTerpilih;

    private JPanel panelUtama;
    private JPanel panelTombol;

    private JTextField txtJumlah;
    private JButton btnBayar;
    private JButton btnKembali;

    public HalamanPembayaran(TiketF1 tiketTerpilih) {
        this.tiketTerpilih = tiketTerpilih;

        setTitle("Menu Pembayaran");
        setSize(420, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panelUtama = new JPanel(new BorderLayout(0, 20));
        panelUtama.setBackground(new Color(21, 21, 30));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(25, 40, 30, 40));

        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBackground(new Color(21, 21, 30));

        JLabel lblJudul = new JLabel("HALAMAN PEMBAYARAN");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblJudul.setForeground(new Color(225, 6, 0));
        lblJudul.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblKategori = new JLabel("Kategori: " + tiketTerpilih.getKategori());
        lblKategori.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblKategori.setForeground(Color.WHITE);
        lblKategori.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblHarga = new JLabel(String.format("Harga Satuan: Rp %,.0f", tiketTerpilih.getHargaSatuan()));
        lblHarga.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblHarga.setForeground(Color.LIGHT_GRAY);
        lblHarga.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblJudul);
        panelHeader.add(Box.createRigidArea(new Dimension(0, 10)));
        panelHeader.add(lblKategori);
        panelHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        panelHeader.add(lblHarga);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(new Color(21, 21, 30));

        JLabel lblInput = new JLabel("Masukkan Jumlah Tiket:");
        lblInput.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblInput.setForeground(Color.WHITE);
        lblInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtJumlah = new JTextField("1");
        txtJumlah.setFont(new Font("SansSerif", Font.BOLD, 16));
        txtJumlah.setHorizontalAlignment(JTextField.CENTER);
        txtJumlah.setMaximumSize(new Dimension(140, 35));

        txtJumlah.addKeyListener(this);

        panelForm.add(lblInput);
        panelForm.add(Box.createRigidArea(new Dimension(0, 8)));
        panelForm.add(txtJumlah);

        panelTombol = new JPanel(new GridLayout(1, 2, 12, 0));
        panelTombol.setBackground(new Color(21, 21, 30));
        panelTombol.setPreferredSize(new Dimension(280, 42));

        btnKembali = new JButton("KEMBALI");
        btnKembali.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnKembali.setForeground(Color.WHITE);
        btnKembali.setBackground(new Color(60, 60, 75));
        btnKembali.setFocusPainted(false);
        btnKembali.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBayar = new JButton("BAYAR");
        btnBayar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBayar.setForeground(Color.WHITE);
        btnBayar.setBackground(new Color(225, 6, 0));
        btnBayar.setFocusPainted(false);
        btnBayar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnKembali.addActionListener(this);
        btnBayar.addActionListener(this);

        panelTombol.add(btnKembali);
        panelTombol.add(btnBayar);

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelForm, BorderLayout.CENTER);
        panelUtama.add(panelTombol, BorderLayout.SOUTH);

        add(panelUtama);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBayar) {
            prosesPembayaran();
        } else if (e.getSource() == btnKembali) {
            new HalamanMenu().setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private void prosesPembayaran() {
        try {
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah tiket minimal 1!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalBayar = tiketTerpilih.hitungTotal(jumlah);
            String pesan = String.format(
                "Pembayaran Berhasil!\n\nKategori : %s\nJumlah   : %d tiket\nTotal    : Rp%,.0f",
                tiketTerpilih.getKategori(), jumlah, totalBayar
            );

            JOptionPane.showMessageDialog(this, pesan, "Transaksi Sukses", JOptionPane.INFORMATION_MESSAGE);

            new HalamanMenu().setVisible(true);
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
