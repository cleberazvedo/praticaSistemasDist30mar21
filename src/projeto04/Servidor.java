package projeto04;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		ServerSocket s = new ServerSocket(2001);
		while (true) {

			System.out.print("Esperando conectar.......");
			Socket conexao = s.accept();
			System.out.println(" Conectou!");
			
			DataInputStream entrada = new DataInputStream(conexao.getInputStream());
			DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
	
			String linha = entrada.readUTF();
			
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			
			while (true) {

				System.out.print("> ");
				linha = teclado.readLine();
				
				saida.writeUTF("echo servidor " + linha);
				linha = entrada.readUTF();
				
				if (linha.equalsIgnoreCase("")) {
		
					System.out.println("Conexao encerrada!");
					break;
		
				}
				System.out.println(linha);
		
			}
			
			saida.writeUTF(linha);
			conexao.close();

		}

	}

}
