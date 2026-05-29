package com.thehecklers.sburrestdemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SburRestDemoApplication.class, args);
	}

}

@RestController
@RequestMapping("/coffees")
@CrossOrigin(origins = "http://localhost:5173") // ← adiciona essa linha
class RestApiDemoController {
	private final CoffeeRepository coffeeRepository;

    RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }


    @GetMapping
	Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeRepository.findById(UUID.fromString(id));
	}

	@PostMapping
	Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffee(@PathVariable String id,
									 @RequestBody Coffee coffee) {
        Coffee c = coffeeRepository.findById(UUID.fromString(id)).orElse(null);
        if (c != null) {
            c.setName(coffee.getName());
            return new ResponseEntity<>(coffeeRepository.save(c), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(UUID.fromString(id));
	}
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Coffee {
    @Id
    @GeneratedValue
    private UUID id;

	private String name;

    public Coffee(String name) {
        this.name = name;
    }
}
interface CoffeeRepository extends CrudRepository<Coffee, UUID> {
}