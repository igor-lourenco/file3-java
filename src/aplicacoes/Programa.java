package aplicacoes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Produto;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Produto> lista = new ArrayList<>();

		System.out.println("Digite o caminho do arquivo: ");
		String arquivoFonteStr = sc.nextLine();

		File arquivoFonte = new File(arquivoFonteStr);
		String pastaOrigemStr = arquivoFonte.getParent();

		boolean sucesso = new File(pastaOrigemStr + "\\out").mkdir();

		String arquivoDestinoStr = pastaOrigemStr + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(arquivoFonteStr))) {

			String itemCsv = br.readLine();

			while (itemCsv != null) {

				String[] campos = itemCsv.split(",");
				String nome = campos[0];
				Double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);

				lista.add(new Produto(nome, preco, quantidade));

				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDestinoStr))) {

				for (Produto item : lista) {
					bw.write(item.getNome() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(arquivoDestinoStr + " CRIADO!!");
			} catch (IOException e) {
				System.out.println("Erro ao gravar Arquivo " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler Arquivo " + e.getMessage());
		}

		sc.close();

	}

}
