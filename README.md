# SISTEMAS DISTRIBUIDOS

Nesta aula, vamos discutir sobre Objetos Distribuídos, Invocação Remota e Comunicação entre Objetos Distribuídos, com um foco especial em como realizar chamada de procedimento remoto usando a tecnologia Java RMI (Remote Method Invocation/Invocação de Metodo Remoto).

## **Introdução**

Objetos distribuídos são aqueles que existem em diferentes computadores em uma rede. A invocação remota é uma técnica usada para permitir que um objeto distribuído execute um método em outro objeto distribuído, localizado em uma máquina diferente. A comunicação entre objetos distribuídos é necessária para permitir que esses objetos compartilhem informações e coordenem suas ações.

O Java RMI é uma tecnologia que permite a chamada de procedimento remoto em objetos distribuídos em uma rede. Nesta aula, discutiremos como usar o Java RMI para invocar métodos em objetos distribuídos.

## **O que é Java RMI?**

Java RMI (Remote Method Invocation) é uma tecnologia de comunicação entre processos em Java que permite a chamada de procedimento remoto em objetos Java distribuídos em uma rede. Ele permite que um objeto Java em um computador chame métodos em outro objeto Java em outro computador, como se o objeto estivesse sendo executado localmente.

## Java RMI é uma API?

Em resumo, Java RMI e API são tecnologias diferentes com finalidades diferentes. Java RMI é usado para permitir a comunicação entre objetos Java em diferentes JVMs, enquanto uma API fornece uma interface para permitir que os desenvolvedores interajam com uma aplicação ou sistema.

## **Como funciona o Java RMI?**

O Java RMI funciona usando uma arquitetura cliente-servidor, onde o cliente faz uma chamada de método remoto para o servidor e o servidor executa o método e retorna o resultado para o cliente. O Java RMI usa o protocolo TCP/IP para comunicação em rede e usa o registro de objeto RMI para localizar objetos distribuídos.

## EXEMPLO PRATICO

1 - Definir a interface remota, criar arquivo `Ola.java`

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ola extends Remote {

	String diga0la() throws RemoteException;

}
```

2 - Definir a implementação da interface remota, criar novo arquivo `OlaImpl.java` ,

```java
import java.rmi.RemoteException;

public class Olalmpl implements Ola {
		
		@Override
		public String diga0la() throws RemoteException {
				return "0la, mundo!";
		}
}
```

2.1 - Estender UnicastRemoteObject

```java
import java.rmi.server.UnicastRemoteObject ;

public class Olalmpl extends UnicastRemoteObject implements 0la {

		private static final long serialVersionUID = 1L;
		// tornando um objeto passivel de ser serializado, por imcompatibilidade de arquitetura de hardware, etc.
```

2.2 - Criar construtor

```java
protected Olalmpl() throws RemoteException {
		super();
}
```

2.3 - Implementar métodos (todos devem lançar a RemoteException)

```java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject ;

public class Olalmpl extends UnicastRemoteObject implements 0la {

		protected Olalmpl() throws RemoteException {
				super();
		}

		private static final long serialVersionUID = IL;
		
		@Override
		public String diga0la() throws RemoteException {
				return "0la, mundo!";
		}
}
```

3 - Criar o servidor, criar arquivo `Server.java`

```java
public class Server {

		public static void main(String[] args){

		}
}
```

3.1 - Criar uma instância de Registry

```java
public static void main(String[] args) {

		try {
				Registry regsitry = LocateRegistry.createRegistry(1099);
		}
		catch (RemoteException e) {
				e. printStackTrace( ) ;
		}

}
```

3.2 - Vincular (rebind) a implementação do objeto remoto no Registry usando Naming

```java
try {
		Registry registry = LocateRegistry.createRegistry(1099);
		Naming.rebind("rmi://localhost:1099/Ola", new OlaImpl());

		System.out.println("Servidor Levantado");
}
catch (RemoteException | MalformedURLExeption e) {

		e. printStackTrace( ) ;
}
```

4 - Criar o cliente, criar o arquivo `Client.java`

```java
public class Client {

		public static void main(String[] args){

		}
}
```

4.1 - Procurar pelo objeto remoto no Registry e devolver o stub

```java
public static void main(String[] args){
		
		try {
				Ola stub = (Ola) Naming.lookup("rmi://localhost:1099/Ola");
		}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace( ) ;
		}
}
```

4.2 - Executar o método remoto

```java
try {
				Ola stub = (Ola) Naming.lookup("rmi://localhost:1099/Ola");
				System.out.println(stub.digaOla);
		}
```

5 - compilar o servidor na linha de comando e rodar

```powershell
// <dir> representa o diretorio que criamos com os arquivos .java

<dir> javac *java
// Compila os arquivos .java

<dir> java Server
// Servidor Levantado
```

6 - Compilar o cliente na linha de comando e rodar

```powershell
// <dir> representa o diretorio que criamos com os arquivos .java

<dir> java Client
// Ola, mundo!
```

## Como ficarão os arquivos

`Ola.java`

```jsx
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ola extends Remote {

    String digaOla() throws RemoteException;
    // subentende-se que sera "public"
}
```

`OlaImpl.java`

```jsx
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OlaImpl extends UnicastRemoteObject implements Ola {

    protected OlaImpl() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String digaOla() throws RemoteException {

        return "Ola, mundo!";
    }

}
```

`Server.java`

```jsx
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/Ola", new OlaImpl());

            System.out.println("Servidor Levantado");

        } catch (RemoteException | MalformedURLException e) {

            e.printStackTrace();
        }
    }
}
```

`Client.java`

```jsx
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {

        try {
            Ola stub = (Ola) Naming.lookup("rmi://localhost:1099/Ola");
            System.out.println(stub.digaOla());

        } catch (MalformedURLException | RemoteException | NotBoundException e) {

            e.printStackTrace();
        }

    }

}
```

## **Conclusão**

Nesta aula, discutimos sobre Objetos Distribuídos, Invocação Remota e Comunicação entre Objetos Distribuídos. Vimos como a tecnologia Java RMI pode ser usada para realizar chamada de procedimento remoto em objetos distribuídos em uma rede. Discutimos como criar e registrar objetos distribuídos em Java RMI e como chamar métodos remotos em Java RMI. Espero que esta aula tenha ajudado a entender melhor como trabalhar com objetos distribuídos em Java usando a tecnologia Java RMI.

## Arquivos da aula

[https://github.com/CarlosSpencer/ifsul-javarmi](https://github.com/CarlosSpencer/ifsul-javarmi)
