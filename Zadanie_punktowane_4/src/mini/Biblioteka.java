package mini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Biblioteka extends JFrame {
    private List<Ksiazka> listaKsiazek;
    private JList<Ksiazka> listaPozycji;
    private JLabel labelAutor;
    private JLabel labelTytul;
    private JLabel labelRokWydania;
    private JCheckBox checkBoxWypozyczona;

    public Biblioteka() {
        super("Biblioteka");

        listaKsiazek = new ArrayList<>();

        // deserializacja danych z pliku biblioteka.bib
        try {
            FileInputStream fileIn = new FileInputStream("biblioteka.bib");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listaKsiazek = (List<Ksiazka>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Dane zostały wczytane z pliku biblioteka.bib");
        } catch (IOException | ClassNotFoundException e) {
            // Jeśli pliku biblioteka.bib nie ma, wczytamy dane z pliku bibdefault.txt
            System.out.println("Nie udało się wczytać pliku biblioteka.bib. Wczytuję dane z bibdefault.txt.");
            // zatem próbujemy z bibdefault.txt:
            try {
                BufferedReader reader = new BufferedReader(new FileReader("bibdefault.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");

                    if (data.length == 3) {
                        String autor = data[0].trim();
                        String tytul = data[1].trim();
                        int rokWydania = Integer.parseInt(data[2].trim());
                        listaKsiazek.add(new Ksiazka(autor, tytul, rokWydania));
                    }
                }
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // sortowanie listy książek po autorze
        listaKsiazek.sort(Comparator.comparing(Ksiazka::getAutor));

        // tworzenie elementów GUI
        listaPozycji = new JList<>(listaKsiazek.toArray(new Ksiazka[0]));
        listaPozycji.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPozycji.addListSelectionListener(e -> {
            Ksiazka ksiazka = listaPozycji.getSelectedValue();
            if (ksiazka != null) {
                labelAutor.setText("Autor: " + ksiazka.getAutor());
                labelTytul.setText("Tytuł: " + ksiazka.getTytul());
                labelRokWydania.setText("Rok wydania: " + ksiazka.getRokWydania());
                checkBoxWypozyczona.setSelected(ksiazka.jestWypozyczona());
            }
        });

        JScrollPane scrollPane = new JScrollPane(listaPozycji);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        labelAutor = new JLabel("Autor:");
        labelTytul = new JLabel("Tytuł:");
        labelRokWydania = new JLabel("Rok wydania:");
        checkBoxWypozyczona = new JCheckBox("Wypożyczona");
        JButton dodajButton = new JButton("Dodaj książkę");
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String autor = JOptionPane.showInputDialog("Podaj autora książki:");
                String tytul = JOptionPane.showInputDialog("Podaj tytuł książki:");
                String rokWydaniaStr = JOptionPane.showInputDialog("Podaj rok wydania książki:");

                try {
                    int rokWydania = Integer.parseInt(rokWydaniaStr);
                    Ksiazka nowaKsiazka = new Ksiazka(tytul, autor, rokWydania);
                    listaKsiazek.add(nowaKsiazka);
                    Collections.sort(listaKsiazek, Comparator.comparing(Ksiazka::getAutor));
                    listaPozycji.setListData(listaKsiazek.toArray(new Ksiazka[0]));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Biblioteka.this, "Nieprawidłowy rok wydania!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Wczytaj");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Biblioteka.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        FileInputStream fileIn = new FileInputStream(selectedFile);
                        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                        listaKsiazek = (List<Ksiazka>) objectIn.readObject();
                        objectIn.close();
                        fileIn.close();
                        listaPozycji.setListData(listaKsiazek.toArray(new Ksiazka[0]));
                        System.out.println("Dane zostały wczytane z pliku " + selectedFile.getName());
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JMenuItem saveItem = new JMenuItem("Zapisz");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(Biblioteka.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        FileOutputStream fileOut = new FileOutputStream(selectedFile);
                        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                        objectOut.writeObject(listaKsiazek);
                        objectOut.close();
                        fileOut.close();
                        System.out.println("Dane zostały zapisane do pliku " + selectedFile.getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);

        // dodawanie elementów do głównego okna
        setLayout(new FlowLayout());
        add(scrollPane);
        add(labelAutor);
        add(labelTytul);
        add(labelRokWydania);
        add(checkBoxWypozyczona);
        add(dodajButton);
        setJMenuBar(menuBar);

        // zapisywanie danych przy zamknięciu okna
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Zapisz dane do pliku biblioteka.bib
                try {
                    FileOutputStream fileOut = new FileOutputStream("biblioteka.bib");
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(listaKsiazek);
                    objectOut.close();
                    fileOut.close();
                    System.out.println("Dane zostały zapisane do pliku biblioteka.bib");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                super.windowClosing(e);
            }
        });

        // Konfiguracja okna
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // checkbox
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Biblioteka::new);
    }
}






