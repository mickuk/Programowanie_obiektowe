package mini;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Okno extends JFrame implements ActionListener, ChangeListener {
    private JButton startButton;
    private JSlider speedSlider;
    private JPanel countdownPanel;
    private int countdownCount;
    private int countdownSpeed;
    private int finishedCountdowns;

    public Okno() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Okno do odliczania");
        setLayout(new BorderLayout());

        countdownPanel = new JPanel();
        countdownPanel.setLayout(new BoxLayout(countdownPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(countdownPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        startButton = new JButton("START");
        startButton.addActionListener(this);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 3, 1);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(startButton);
        controlPanel.add(new JLabel("Szybkość:"));
        controlPanel.add(speedSlider);

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        countdownCount = 0;
        countdownSpeed = speedSlider.getValue();
        finishedCountdowns = 0;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (countdownCount < 5) {
                countdownCount++;
                startCountdown();
            }
            if (countdownCount == 5) {
                startButton.setEnabled(false);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == speedSlider) {
            countdownSpeed = speedSlider.getValue();
        }
    }

    private void startCountdown() {
        JLabel countdownLabel = new JLabel();
        countdownPanel.add(countdownLabel);

        Thread countdownThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 10; i >= 0; i--) {
                    try {
                        Thread.sleep(countdownSpeed * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countdownLabel.setText(Integer.toString(i));
                }
                synchronized (Okno.this) {
                    finishedCountdowns++;
                    if (finishedCountdowns == countdownCount) {
                        startButton.setText("Koniec");
                    }
                }
            }
        });

        countdownThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Okno okno = new Okno();
                okno.setSize(600, 400);
                okno.setVisible(true);
            }
        });
    }
}