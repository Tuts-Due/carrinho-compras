import java.math.BigDecimal;
import java.util.Scanner;

import br.com.improving.carrinho.CarrinhoCompras;
import br.com.improving.carrinho.CarrinhoComprasFactory;
import br.com.improving.carrinho.Item;
import br.com.improving.carrinho.Produto;

/**
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);


		CarrinhoComprasFactory carrinhoComprasFactory = new CarrinhoComprasFactory();


		System.out.print("Digite a identificação do cliente: ");
		String identificacaoCliente = scanner.nextLine();


		CarrinhoCompras carrinhoCompras = carrinhoComprasFactory.criar(identificacaoCliente);

		while (true) {

			System.out.println("\nOpções:");
			System.out.println("1. Adicionar item");
			System.out.println("2. Remover item");
			System.out.println("3. Ver carrinho");
			System.out.println("4. Ver valor total do carrinho");
			System.out.println("5. Sair");

			// Lendo a escolha do usuário
			System.out.print("Escolha uma opção (1-5): ");
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (opcao) {
				case 1:

					adicionarItemAoCarrinho(scanner, carrinhoCompras);
					break;
				case 2:

					removerItemDoCarrinho(scanner, carrinhoCompras);
					break;
				case 3:

					exibirItensNoCarrinho(carrinhoCompras);
					break;
				case 4:

					exibirValorTotalDoCarrinho(carrinhoCompras);
					break;
				case 5:

					System.out.println("Saindo do programa.");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private static void adicionarItemAoCarrinho(Scanner scanner, CarrinhoCompras carrinhoCompras) {
		System.out.print("Digite o código do produto: ");
		Long codigoProduto = scanner.nextLong();
		scanner.nextLine(); // Limpar o buffer

		System.out.print("Digite a descrição do produto: ");
		String descricaoProduto = scanner.nextLine();

		Produto produto = new Produto(codigoProduto, descricaoProduto);

		System.out.print("Digite o valor unitário do produto: ");
		BigDecimal valorUnitario = scanner.nextBigDecimal();

		System.out.print("Digite a quantidade do produto: ");
		int quantidade = scanner.nextInt();

		carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade);
		System.out.println("Item adicionado ao carrinho.");
	}
	private static void exibirItensNoCarrinho(CarrinhoCompras carrinhoCompras) {
		System.out.println("\nItens no carrinho:");
		for (Item item : carrinhoCompras.getItens()) {
			System.out.println("Produto: " + item.getProduto().getDescricao() +
					", Quantidade: " + item.getQuantidade() +
					", Valor Unitário: " + item.getValorUnitario() +
					", Valor Total: " + item.getValorTotal());
		}
	}
	private static void removerItemDoCarrinho(Scanner scanner, CarrinhoCompras carrinhoCompras) {
		System.out.print("Digite o código do produto a ser removido: ");
		Long codigoProduto = scanner.nextLong();
		scanner.nextLine(); // Limpar o buffer

		Produto produto = new Produto(codigoProduto, "");

		if (carrinhoCompras.removerItem(produto)) {
			System.out.println("Item removido do carrinho!");
		} else {
			System.out.println("O produto não foi encontrado no carrinho.");
		}
	}
	private static void exibirValorTotalDoCarrinho(CarrinhoCompras carrinhoCompras) {
		BigDecimal valorTotal = carrinhoCompras.getValorTotal();
		System.out.println("\nValor total do carrinho: " + valorTotal);
	}


}
