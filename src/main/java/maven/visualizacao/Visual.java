package maven.visualizacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import maven.infra.Conexao;
import maven.servicos.AtualizarBoletos;
import maven.servicos.Vizualizar;

public class Visual {

	public void aplicacao() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		LocalDate date = LocalDate.now();
		int mesAtual = date.getMonthValue();
		Conexao.getConexao();
		AtualizarBoletos atlz = new AtualizarBoletos();
		Vizualizar vizualizar = new Vizualizar();

		boolean sair = false;
		System.out.println("Bem vindo ao Controle de Contas!\n");
		System.out.println("Digite o nome do Usuario: ");
		String usuario = entrada.next();

		while (!sair) {
			Integer opcao = 1;

			System.out.println("O que voce deseja fazer: ");
			System.out.println((opcao++) + " - Ver contas do mes:");
			System.out.println((opcao++) + " - Ver contas de outro mes:");
			System.out.println((opcao++) + " - Ver contas do mes em atraso:");
			System.out.println((opcao++) + " - Aicionar Contas:  ");
			System.out.println((opcao++) + " - Pagar Contas:  ");
			System.out.println((opcao++) + " - Outras Opções:");
			opcao = 0;
			int escolha = entrada.nextInt();

			if (escolha == (opcao += 1)) { // ver contas do mes

				vizualizar.boletosDoMes(usuario, mesAtual);

			}
			if (escolha == (opcao += 1)) { // ver contas de outro mes
				System.out.println("digite o numero do mes: ");
				int mesEscolhido = entrada.nextInt();
				System.out.println();
				
				vizualizar.boletosDoMes(usuario, mesEscolhido);
				
			}

			if (escolha == (opcao += 1)) { // contas em atraso:
				vizualizar.boletosVencidos(usuario, mesAtual);
			}

			if (escolha == (opcao += 1)) {// adicionar contas
				System.out.print("Descricao da conta: ");
				String descricao = entrada.next();
				System.out.println();

				System.out.print("Qual o valor: ");
				double valor = entrada.nextDouble();
				System.out.println();

				System.out.print("Qual o vencimento: ");
				int vencimento = entrada.nextInt();
				System.out.println();

				System.out.print("Qual a recorrencia desse pagamento? ");
				int recorrencia = entrada.nextInt();
				System.out.println();

				if (recorrencia > 12) {
					recorrencia = 12;
				} else if (recorrencia < 0) {
					recorrencia = 1;
				}

				atlz.adicionarBoleto(usuario, descricao, valor, vencimento, recorrencia);
				System.out.println("\nConta adicionada com sucesso!\n");
				vizualizar.boletosDoMes(usuario, mesAtual);
				System.out.println();

			}

			if (escolha == (opcao += 1)) { // pagar contas
				vizualizar.boletosDoMes(usuario, mesAtual);

				System.out.println("\nDiga o id da conta a ser paga: ");
				atlz.pagarBoleto(usuario, entrada.nextInt());

				System.out.println("\nConta paga con sucesso!\n");
				vizualizar.boletosDoMes(usuario, mesAtual);
//				cnx.mostrarContasAPagar(usuario);

			}
			if (escolha == (opcao += 1)) {
				System.out.println("O que voce deseja fazer:\n ");
				System.out.println("Digite 4 para EXCLUIR uma conta. ");
				System.out.println("Digite 5 para ver as contas pagas no mes.");

				if (escolha == (opcao += 1)) {
					vizualizar.boletosDoMes(usuario, mesAtual);
//					cnx.mostrarContasMes(usuario);

					System.out.println("\nDiga o id da conta a ser EXCLUIDA: ");
					atlz.excluirBoleto(usuario, entrada.nextInt());
					// excluirBoletos.excluirBoleto(usuario, entrada.nextInt());
//					cnx.excluirContas(usuario, entrada.nextInt());
					System.out.println("\nConta EXCLUIDA con sucesso!\n");
					vizualizar.boletosDoMes(usuario, mesAtual);

					// cnx.mostrarContasMes(usuario);

				}
			}
			System.out.println("\nDeseja Continuar? S/n ");

			if (!entrada.next().equalsIgnoreCase("s")) {
				sair = true;
			}

		}
		System.out.println("Ate mais!");

		entrada.close();
	}

}
