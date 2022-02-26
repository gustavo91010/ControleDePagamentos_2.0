package maven;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import maven.boleto.AdicionarBoletos;
import maven.boleto.BoletosAPagar;
import maven.boleto.ExcluirBoletos;
import maven.boleto.PagarBoletos;
import maven.infra.Conexao;

public class Visual {

	public void aplicacao() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		LocalDate date = LocalDate.now();
		int mesAtual = date.getMonthValue();
		Conexao.getConexao();
		BoletosAPagar boletosAPagar = new BoletosAPagar();
		AdicionarBoletos addBoletos = new AdicionarBoletos();
		PagarBoletos pagarBoletos= new PagarBoletos();


		boolean sair = false;
		System.out.println("Bem vindo ao Controle de Contas!\n");
		System.out.println("Digite o nome do Usuario: ");
		String usuario = entrada.next();

		while (!sair) {

			System.out.println("O que voce deseja fazer: ");
			System.out.println("1- Ver contas do mes:");
			System.out.println("2- Aicionar Contas:  ");
			System.out.println("3- Pagar Contas:  ");
			System.out.println("0- Outras Opções:");

			int hum = entrada.nextInt();

			if (hum == 1) { // ver contas do mes

//				cnx.mostrarContasVencidas(usuario);
//				System.out.println();
				boletosAPagar.vizualizar(usuario, mesAtual);

			}

			if (hum == 2) {// adicionar contas
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

				addBoletos.adicionarContas(usuario, descricao, valor, vencimento, recorrencia);
				System.out.println("\nConta adicionada com sucesso!\n");
				boletosAPagar.vizualizar(usuario, mesAtual);

			} 

			if (hum == 3) { // pagar contas
				boletosAPagar.vizualizar(usuario, mesAtual);
//				cnx.mostrarContasAPagar(usuario);

				System.out.println("\nDiga o id da conta a ser paga: ");
				pagarBoletos.vizualizar(usuario, entrada.nextInt());
//				cnx.pagarContas(usuario, entrada.nextInt());

				System.out.println("\nConta paga con sucesso!\n");
				boletosAPagar.vizualizar(usuario, mesAtual);
//				cnx.mostrarContasAPagar(usuario);

			}
			if (hum == 0) {
				System.out.println("O que voce deseja fazer:\n ");
				System.out.println("Digite 4 para EXCLUIR uma conta. ");
				System.out.println("Digite 5 para ver as contas pagas no mes.");

				if (hum == 4) {
					boletosAPagar.vizualizar(usuario, mesAtual);
//					cnx.mostrarContasMes(usuario);
					
					System.out.println("\nDiga o id da conta a ser EXCLUIDA: ");
					ExcluirBoletos excluirBoletos= new ExcluirBoletos();
					excluirBoletos.excluirBoleto(usuario, entrada.nextInt());
//					cnx.excluirContas(usuario, entrada.nextInt());
					System.out.println("\nConta EXCLUIDA con sucesso!\n");
					boletosAPagar.vizualizar(usuario, mesAtual);

					//					cnx.mostrarContasMes(usuario);

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
