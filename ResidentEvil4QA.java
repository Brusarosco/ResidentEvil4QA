package com.mycompany.residentevil4qa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResidentEvil4QA {

    private static int currentQuestion = 0;
    private static int score = 0;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel questionPanel;
    private static JLabel questionLabel;
    private static ButtonGroup buttonGroup;
    private static JRadioButton[] answerButtons;
    private static JButton submitButton;
    private static JTextArea correctAnswersTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        if (frame != null) {
            frame.dispose();
        }

        frame = new JFrame("Resident Evil 4 Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel);

        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionLabel = new JLabel("<html><div style='text-align: center;'>" + questions[currentQuestion] + "</div></html>");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionPanel.add(questionLabel);
        mainPanel.add(questionPanel);

        buttonGroup = new ButtonGroup();
        answerButtons = new JRadioButton[answers[currentQuestion].length];
        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i < answers[currentQuestion].length; i++) {
            answerButtons[i] = new JRadioButton(answers[currentQuestion][i]);
            answerPanel.add(answerButtons[i]);
            buttonGroup.add(answerButtons[i]);
        }

        mainPanel.add(answerPanel);

        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Enviar Resposta");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitAnswerListener());
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel);

        correctAnswersTextArea = new JTextArea(10, 40); // Configura o tamanho da caixa de texto
        correctAnswersTextArea.setEditable(false); // Impede a edição do texto
        mainPanel.add(correctAnswersTextArea);

        frame.setPreferredSize(new Dimension(600, 400)); // Aumenta a altura da janela
        frame.pack();
        frame.setVisible(true);
    }

    private static class SubmitAnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            submitButton.setEnabled(false);

            for (int i = 0; i < answerButtons.length; i++) {
                if (answerButtons[i].isSelected()) {
                    if (i == correctAnswers[currentQuestion]) {
                        score++;
                    }
                    break;
                }
            }

            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                createAndShowGUI();
            } else {
                displayCorrectAnswers();
            }
        }
    }

    private static void displayCorrectAnswers() {
        correctAnswersTextArea.setText("Respostas Corretas:\n\n");
        for (int i = 0; i < questions.length; i++) {
            correctAnswersTextArea.append("Pergunta " + (i + 1) + ": " + questions[i] + "\n");
            correctAnswersTextArea.append("Resposta Correta: " + answers[i][correctAnswers[i]] + "\n\n");
        }

        correctAnswersTextArea.append("Pontuação Final: " + score + " de " + questions.length);
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
