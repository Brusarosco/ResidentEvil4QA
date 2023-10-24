package com.mycompany.residentevil4qa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ResidentEvil4QA {

    private static int currentQuestion = 0;
    private static int score = 0;

    private static JFrame frame;
    private static JPanel questionPanel;
    private static JPanel answerPanel;
    private static JPanel scorePanel;
    private static JLabel questionLabel;
    private static JLabel scoreLabel;
    private static List<JRadioButton> answerButtons;
    private static ButtonGroup buttonGroup;
    private static JButton nextButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        if (frame != null) {
            frame.dispose();
        }

        frame = new JFrame("Resident Evil 4 Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        questionPanel = new JPanel(new FlowLayout());
        questionLabel = new JLabel();
        questionLabel.setText("Pergunta: " + questions[currentQuestion]);
        questionPanel.add(questionLabel);

        answerPanel = new JPanel(new GridLayout(0, 1));
        answerButtons = new ArrayList<>();
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < answers[currentQuestion].length; i++) {
            JRadioButton answerButton = new JRadioButton(answers[currentQuestion][i]);
            answerButton.addActionListener(new AnswerListener());
            answerPanel.add(answerButton);
            answerButtons.add(answerButton);
            buttonGroup.add(answerButton);
        }

        scorePanel = new JPanel();
        scoreLabel = new JLabel("Pontuação: " + score);
        scorePanel.add(scoreLabel);

        nextButton = new JButton("Próxima Pergunta");
        nextButton.addActionListener(new NextQuestionListener());

        frame.add(questionPanel, BorderLayout.NORTH);
        frame.add(answerPanel, BorderLayout.CENTER);
        frame.add(scorePanel, BorderLayout.SOUTH);
        frame.add(nextButton, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
    }

    private static class AnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < answerButtons.size(); i++) {
                if (answerButtons.get(i).isSelected() && i == correctAnswers[currentQuestion]) {
                    score++;
                    break;
                }
            }
        }
    }

    private static class NextQuestionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                createAndShowGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Jogo encerrado! Pontuação: " + score + " de " + questions.length);
                System.exit(0);
            }
        }
    }

    private static String[] questions = {
        "Qual é o nome do protagonista de Resident Evil 4?",
        "Qual é a organização que sequestrou a filha do presidente?",
        "Qual é a localização geográfica principal em Resident Evil 4?",
        "Quem é o antagonista principal em Resident Evil 4?",
        "Qual é a principal ameaça biológica em Resident Evil 4?"
    };

    private static String[][] answers = {
        {"Chris Redfield", "Leon Kennedy", "Jill Valentine", "Claire Redfield"},
        {"S.T.A.R.S.", "Umbrella Corporation", "Los Illuminados", "BSAA"},
        {"Raccoon City", "Arklay Mountains", "Ilha Rockfort", "Espanha rural"},
        {"Albert Wesker", "William Birkin", "Ada Wong", "Osmund Saddler"},
        {"Zumbis", "Vírus T", "Las Plagas", "Plantas mutantes"}
    };

    private static int[] correctAnswers = {1, 2, 3, 3, 2};
}

