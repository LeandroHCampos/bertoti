# Exerciocio 1
## Texto 1

>What precisely do we mean by software engineering? What distinguishes “software engineering” from “programming” or “computer science”? And why would Google have a unique >perspective to add to the corpus of previous software engineering literature written over the past 50 years?

>The terms “programming” and “software engineering” have been used
interchangeably for quite some time in our industry, although 
each term has a different emphasis and different implications. 
University students tend to study computer science and get jobs 
writing code as “programmers.”

“Software engineering,” however, sounds more serious, as if it 
implies the application of some theoretical knowledge to build 
something real and precise. Mechanical engineers, civil engineers, 
aeronautical engineers, and those in other engineering disciplines
all practice engineering. They all work in the real world and use
the application of their theoretical knowledge to create something 
real. Software engineers also create “something real,” though it
is less tangible than the things other engineers create.

Unlike those more established engineering professions, current 
software engineering theory or practice is not nearly as rigorous.
Aeronautical engineers must follow rigid guidelines and 
practices, because errors in their calculations can cause real 
damage; programming, on the whole, has traditionally not followed 
such rigorous practices. But, as software becomes more integrated 
into our lives, we must adopt and rely on more rigorous 
engineering methods. We hope this book helps others see a path 
toward more reliable software practices.```


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
