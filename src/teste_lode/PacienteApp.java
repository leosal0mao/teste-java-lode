package teste_lode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.text.MaskFormatter;

public class PacienteApp extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PacienteModel> pacientes = new ArrayList<>();

    public PacienteApp() {
        setTitle("Sistema de Gestão de Pacientes");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton cadastrarButton = new JButton("Cadastrar Paciente");
        JButton editarButton = new JButton("Editar Paciente");
        JButton listarButton = new JButton("Listar Pacientes");
        JButton excluirButton = new JButton("Excluir Paciente");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPaciente();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPaciente();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPacientes();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPaciente();
            }
        });

        add(cadastrarButton);
        add(editarButton);
        add(listarButton);
        add(excluirButton);
    }

    private void cadastrarPaciente() {
        JTextField nomeField = new JTextField();
        JFormattedTextField dataNascimentoField = null;
        try {
            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
            dateFormatter.setPlaceholderCharacter('_');
            dataNascimentoField = new JFormattedTextField(dateFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JTextField enderecoField = new JTextField();
        JTextField observacoesField = new JTextField();

        Object[] message = {
            "Nome:", nomeField,
            "Data de Nascimento (dd/MM/yyyy):", dataNascimentoField,
            "Endereço:", enderecoField,
            "Observações:", observacoesField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar Paciente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String dataNascimento = dataNascimentoField.getText();
            String endereco = enderecoField.getText();
            String observacoes = observacoesField.getText();

            if (isValidDate(dataNascimento)) {
            	PacienteModel paciente = new PacienteModel(nome, dataNascimento, endereco, observacoes);
                pacientes.add(paciente);
                JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Data de nascimento inválida! Use o formato dd/MM/yyyy.");
            }
        }
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(date);
            Date currentDate = new Date();
            if (parsedDate.after(currentDate)) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void editarPaciente() {
        String codigoStr = JOptionPane.showInputDialog("Digite o código do paciente a ser editado:");
        if (codigoStr != null) {
            int codigo = Integer.parseInt(codigoStr);
            for (PacienteModel paciente : pacientes) {
                if (paciente.getCodigo() == codigo) {
                    JTextField nomeField = new JTextField(paciente.getNome());
                    JFormattedTextField dataNascimentoField = null;
                    try {
                        MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
                        dateFormatter.setPlaceholderCharacter('_');
                        dataNascimentoField = new JFormattedTextField(dateFormatter);
                        dataNascimentoField.setText(paciente.getDataNascimento());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    JTextField enderecoField = new JTextField(paciente.getEndereco());
                    JTextField observacoesField = new JTextField(paciente.getObservacoes());

                    Object[] message = {
                        "Nome:", nomeField,
                        "Data de Nascimento (dd/MM/yyyy):", dataNascimentoField,
                        "Endereço:", enderecoField,
                        "Observações:", observacoesField
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Editar Paciente", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String dataNascimento = dataNascimentoField.getText();
                        if (isValidDate(dataNascimento)) {
                            paciente.setNome(nomeField.getText());
                            paciente.setDataNascimento(dataNascimento);
                            paciente.setEndereco(enderecoField.getText());
                            paciente.setObservacoes(observacoesField.getText());
                            JOptionPane.showMessageDialog(this, "Paciente editado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Data de nascimento inválida! Use o formato dd/MM/yyyy.");
                        }
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Paciente não encontrado!");
        }
    }

    private void listarPacientes() {
        StringBuilder lista = new StringBuilder("Pacientes cadastrados:\n");
        for (PacienteModel paciente : pacientes) {
            lista.append(paciente.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, lista.toString());
    }

    private void excluirPaciente() {
        String codigoStr = JOptionPane.showInputDialog("Digite o código do paciente a ser excluído:");
        if (codigoStr != null) {
            int codigo = Integer.parseInt(codigoStr);
            for (PacienteModel paciente : pacientes) {
                if (paciente.getCodigo() == codigo) {
                    pacientes.remove(paciente);
                    JOptionPane.showMessageDialog(this, "Paciente excluído com sucesso!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Paciente não encontrado!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PacienteApp().setVisible(true);
            }
        });
    }
}
