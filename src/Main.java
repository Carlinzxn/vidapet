import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Variáveis básicas do animal e da consulta (sem POO)
        String nomeAnimal = "";
        int especie = 0; // 1 - Cão | 2 - Gato
        int idadeAnimal = 0;
        String nomeTutor = "";
        String telefoneTutor = "";

        String nomeVet = "";
        String crmvVet = "";
        int diaSemana = 0; // 2=Seg até 6=Sex
        int hora = 0;
        int minuto = 0;

        // 0 = Sem consulta | 1 = Agendada | 2 = Realizada | 3 = Cancelada
        int status = 0;
        String observacao = "";

        boolean rodarSistema = true;

        while (rodarSistema) {
            System.out.println("\n=== VIDAPET - MENU INICIAL ===");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Cancelar consulta");
            System.out.println("3 - Finalizar consulta (Veterinário)");
            System.out.println("4 - Mostrar consulta");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    if (status == 1) {
                        System.out.println("Já existe uma consulta agendada no momento.");
                    } else {
                        System.out.print("Nome do animal: ");
                        nomeAnimal = entrada.nextLine();

                        System.out.print("Espécie (1 para Cão, 2 para Gato): ");
                        especie = entrada.nextInt();

                        if (especie != 1 && especie != 2) {
                            System.out.println("Espécie inválida. A clínica atende apenas cães ou gatos.");
                        } else {
                            System.out.print("Idade do animal (em anos): ");
                            idadeAnimal = entrada.nextInt();
                            entrada.nextLine();

                            System.out.print("Nome do tutor: ");
                            nomeTutor = entrada.nextLine();

                            System.out.print("Telefone do tutor: ");
                            telefoneTutor = entrada.nextLine();

                            System.out.print("Nome do veterinário: ");
                            nomeVet = entrada.nextLine();

                            System.out.print("CRMV do veterinário: ");
                            crmvVet = entrada.nextLine();

                            System.out.print("Dia da semana (2=Seg, 3=Ter, 4=Qua, 5=Qui, 6=Sex): ");
                            diaSemana = entrada.nextInt();

                            System.out.print("Hora (8 às 17): ");
                            hora = entrada.nextInt();

                            System.out.print("Minuto (0 ou 30): ");
                            minuto = entrada.nextInt();
                            entrada.nextLine();

                            // Regra de horário comercial (Seg a Sex, 08:00 às 17:30)
                            if (diaSemana < 2 || diaSemana > 6 || hora < 8 || hora > 17 || (minuto != 0 && minuto != 30)) {
                                System.out.println("Horário inválido! Atendemos de segunda a sexta, das 08:00 às 17:30.");
                            } else {
                                status = 1;
                                observacao = "";
                                System.out.println("Consulta agendada com sucesso! Duração: 30 minutos.");
                            }
                        }
                    }
                    break;

                case 2:
                    if (status == 1) {
                        status = 3;
                        System.out.println("Consulta cancelada.");
                    } else if (status == 3) {
                        System.out.println("Essa consulta já estava cancelada.");
                    } else if (status == 2) {
                        System.out.println("Não é possível cancelar uma consulta já realizada.");
                    } else {
                        System.out.println("Nenhuma consulta agendada.");
                    }
                    break;

                case 3:
                    if (status != 1) {
                        System.out.println("Apenas consultas agendadas podem ser finalizadas.");
                    } else {
                        System.out.print("Digite seu CRMV para confirmar: ");
                        String crmvDigitado = entrada.nextLine();

                        if (!crmvDigitado.equals(crmvVet)) {
                            System.out.println("Erro: você não pode finalizar a consulta de outro veterinário.");
                        } else {
                            System.out.print("Observações do atendimento: ");
                            observacao = entrada.nextLine();
                            status = 2;
                            System.out.println("Consulta marcada como realizada!");
                        }
                    }
                    break;

                case 4:
                    if (status == 0) {
                        System.out.println("Nenhuma consulta cadastrada.");
                    } else {
                        System.out.println("\n--- DADOS DA CONSULTA ---");
                        System.out.println("Animal: " + nomeAnimal + " | Idade: " + idadeAnimal + " anos");

                        if (especie == 1) {
                            System.out.println("Espécie: Cão");
                        } else {
                            System.out.println("Espécie: Gato");
                        }

                        System.out.println("Tutor: " + nomeTutor + " (" + telefoneTutor + ")");
                        System.out.println("Veterinário: " + nomeVet + " - CRMV: " + crmvVet);
                        System.out.println("Horário: " + hora + ":" + minuto);

                        if (status == 1) {
                            System.out.println("Situação: Agendada");
                        } else if (status == 2) {
                            System.out.println("Situação: Realizada");
                            System.out.println("Observações: " + observacao);
                        } else if (status == 3) {
                            System.out.println("Situação: Cancelada");
                        }
                    }
                    break;

                case 0:
                    rodarSistema = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        entrada.close();
    }
}