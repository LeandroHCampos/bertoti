# Exerciocio 1
## Texto 1

>What precisely do we mean by software engineering? What distinguishes “software engineering” from “programming” or “computer science”? And why would Google have a unique >perspective to add to the corpus of previous software engineering literature written over the past 50 years?

>The terms “programming” and “software engineering” have been used
interchangeably for quite some time in our industry, although 
each term has a different emphasis and different implications. 
University students tend to study computer science and get jobs 
writing code as “programmers.”

>“Software engineering,” however, sounds more serious, as if it 
implies the application of some theoretical knowledge to build 
something real and precise. Mechanical engineers, civil engineers, 
aeronautical engineers, and those in other engineering disciplines
all practice engineering. They all work in the real world and use
the application of their theoretical knowledge to create something 
real. Software engineers also create “something real,” though it
is less tangible than the things other engineers create.

>Unlike those more established engineering professions, current 
software engineering theory or practice is not nearly as rigorous.
Aeronautical engineers must follow rigid guidelines and 
practices, because errors in their calculations can cause real 
damage; programming, on the whole, has traditionally not followed 
such rigorous practices. But, as software becomes more integrated 
into our lives, we must adopt and rely on more rigorous 
engineering methods. We hope this book helps others see a path 
toward more reliable software practices.```

Esse trecho mostra a diferença entre programar e fazer engenharia de software. Programar é basicamente escrever o código, enquanto a engenharia de software envolve planejar, aplicar conceitos teóricos e pensar em soluções mais completas, mesmo que a gente não veja essas soluções fisicamente. O autor compara com engenharias como a aeronáutica, onde um erro pode ser muito grave, e diz que na engenharia de software ainda falta esse mesmo nível de cuidado. Como o software está cada vez mais presente no nosso dia a dia, ele defende que é importante usar práticas mais confiáveis e bem definidas

# Exercicio 2

>Programming Over Time
>We propose that “software engineering” encompasses not just the act of writing code, but all of the tools and processes an organization uses to build and maintain that code over time. What practices can a software organization introduce that will best keep its code valuable over the long term? How can engineers make a codebase more sustainable and the software engineering discipline itself more rigorous? We don’t have fundamental answers to these questions, but we hope that Google’s collective experience over the past two decades illuminates possible paths toward finding those answers.
 
>One key insight we share in this book is that software engineering can be thought of as “programming integrated over time.” What practices can we introduce to our code to make it sustainable—able to react to necessary change—over its life cycle, from conception to introduction to maintenance to deprecation?
 
>The book emphasizes three fundamental principles that we feel software organizations should keep in mind when designing, architecting, and writing their code:
 
>Time and Change
>How code will need to adapt over the length of its life
 
>Scale and Growth
>How an organization will need to adapt as it evolves
 
>Trade-offs and Costs
>How an organization makes decisions, based on the lessons of Time and Change and Scale and Growth

Engenharia de software é como "programação ao longo do tempo", e não apenas escrever códigos, já que não adianta o código estar funcionando agora, ele tem que continuar fazendo sentido e sendo útil no futuro. Para isso, as empresas de software devem considerar: Time and Change(como o código vai se adaptar com o tempo), Scale and Growth(como o sistema e a equipe crescem juntos) e Trade-offs and Costs(as decisões feitas ao equilibrar prazos, recursos e qualidade)

# Exercicio 3
## Listar e explicar 3 exemplos de tradeoffs
### Documentar o Código vs. Entregar no Prazo
Escrever comentários e documentação ajuda na manutenção e facilita para o entendimento, mas consome tempo, que as vezes pode faltar.

### Ficar em Casa no Fim de Semana vs. Sair com os Amigos
Ficar em casa pode ser mais tranquilo e barato. Sair é divertido, mas envolve gastos e talvez cansaço.

### HD vs SSD
O HD é uma opção mais econômica quando se trata de custo por gigabyte, sendo indicado para quem precisa guardar muitos dados sem gastar muito. Já o SSD oferece velocidades muito superiores na leitura e gravação de arquivos, proporcionando melhor desempenho, porém com um preço mais elevado por gigabyte.

# Exercicio 4
![BertotiSlide](https://github.com/user-attachments/assets/63c2f3f8-e75a-4211-a91b-d95d42a9b901)
Entregar um produto que, mesmo ainda não sendo exatamente o que o cliente deseja, seja funcional e resolva parte do problema, é muito melhor do que entregar apenas uma parte do produto final desejado. Isso porque, mesmo sendo um pedaço do produto, o cliente não conseguirá utilizá-lo de forma útil.

# Exercicio 5
 Biblioteca de livros
### Classe Livro
```java
package com.mycompany.biblioteca;

public class Livro {
	
	private String titulo;
	private String isbn;
	private String autor;
	
	public Livro(String titulo, String isbn, String autor) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.autor = autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
} 
```
### Classe Biblioteca
```java
package com.mycompany.biblioteca;

import java.util.List;
import java.util.LinkedList;

public class Biblioteca {
	
	private List<Livro> livros = new LinkedList<Livro>();
	
	public void addLivro(Livro livro) {
		livros.add(livro);
	}
	
	public Livro buscarLivroIsbn(String isbn) {
		for(Livro livro: livros) {
			if(livro.getIsbn().equals(isbn)) return livro;
		}
		return null;
	}
	
	public List<Livro> buscarLivroTitulo(String titulo){
		List<Livro> encontrados = new LinkedList<Livro>();
		for(Livro livro: livros) {
			if(livro.getTitulo().equals(titulo)) encontrados.add(livro);
		}
		return encontrados;
	}
	
	public List<Livro> getLivros(){
		return livros;
	}
} 

```

### Classe Teste
```java
package com.mycompany.biblioteca;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {

    @Test
    void test() {
        
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.addLivro(new Livro("Dom Casmurro", "9788535902778", "Machado de Assis"));
        biblioteca.addLivro(new Livro("O Alienista", "9788535902779", "Machado de Assis"));
        
        assertEquals(biblioteca.getLivros().size(), 2);
        
        Livro livro = biblioteca.buscarLivroIsbn("9788535902778");
        
        assertEquals(livro.getTitulo(), "Dom Casmurro");
        assertEquals(livro.getAutor(), "Machado de Assis");
        
    }

} 
```
